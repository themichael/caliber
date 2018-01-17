package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.NoteType;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.TraineeFlag;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.data.TraineeDAO;

/**
 * Used to add grades for assessments and input notes Application logic has no
 * business being in a DAO nor in a Controller. This is the ideal place for
 * calculations
 * 
 * @author Patrick Walsh
 *
 */
@Service
public class EvaluationService {

	private static final Logger log = Logger.getLogger(EvaluationService.class);
	private GradeDAO gradeDAO;
	private NoteDAO noteDAO;
	private TraineeDAO traineeDAO;
	private static final String FINDING_WEEK = "Finding week ";

	@Autowired
	public void setGradeDAO(GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
	}

	@Autowired
	public void setNoteDAO(NoteDAO noteDAO) {
		this.noteDAO = noteDAO;
	}

	@Autowired
	public void setTraineeDAO(TraineeDAO traineeDAO) {
		this.traineeDAO = traineeDAO;
	}

	/*
	 *******************************************************
	 * GRADING SERVICES
	 *
	 *******************************************************
	 */

	/**
	 * SAVE GRADE
	 * 
	 * @param grade
	 */
	public void save(Grade grade) {
		log.debug("Saving grade: " + grade);
		gradeDAO.save(grade);
	}

	/**
	 * UPDATE GRADE
	 * 
	 * @param grade
	 */
	public void update(Grade grade) {
		log.debug("Updating grade: " + grade);
		gradeDAO.update(grade);
	}

	/**
	 * FIND GRADES BY WEEK
	 * 
	 * @param batchId
	 * @param week
	 * @return
	 */
	public Map<Integer, List<Grade>> findGradesByWeek(Integer batchId, Integer week) {
		log.debug(FINDING_WEEK + week + " grades for batch: " + batchId);
		List<Grade> grades = gradeDAO.findByWeek(batchId, week);
		Map<Integer, List<Grade>> table = new HashMap<>();
		for (Grade grade : grades) {
			Integer key = grade.getTrainee().getTraineeId();
			if (table.containsKey(grade.getTrainee().getTraineeId())) {
				// eliminate nested records first
				grade.getAssessment().setBatch(null);
				// get the trainee's assessments and add the new assessment
				table.get(key).add(grade);
			} else {
				// eliminate nested records first
				grade.getAssessment().setBatch(null);
				// add the first assessment
				List<Grade> assessments = new ArrayList<>();
				assessments.add(grade);
				table.put(key, assessments);
			}
		}
		return table;
	}

	/*
	 *******************************************************
	 * NOTE SERVICES
	 *
	 *******************************************************
	 */

	/**
	 * SAVE NOTE
	 * 
	 * @param note
	 */
	public int save(Note note) {
		log.debug("Saving note: " + note);
		int key = noteDAO.save(note);
		checkIfTraineeShouldBeFlagged(note);
		return key;
	}

	/**
	 * UPDATE NOTE
	 * 
	 * @param note
	 */
	public void update(Note note) {
		log.debug("Updating note: " + note);
		noteDAO.update(note);
		checkIfTraineeShouldBeFlagged(note);
	}

	/**
	 * Trainees can be flagged automatically if they have: 2 Red or 2 consecutive
	 * yellow QCs
	 * 
	 * @param note
	 */
	public void checkIfTraineeShouldBeFlagged(Note note) {
		if (note != null && note.getType().equals(NoteType.QC_TRAINEE)) {
			// trainee should not be flagged on week 1 itself
			if (note.getWeek() < 2)
				return;

			// get a list of all notes in week ASC order
			List<Note> notes = noteDAO.findQCIndividualNotes(note.getTrainee().getTraineeId());

			// loop over the notes to find if they have 2 or more consecutive yellow or red
			// QCs
			boolean flagged = false;
			// current week
			flagged = (notes.get(note.getWeek() - 1).getQcStatus().equals(QCStatus.Poor)
					|| notes.get(note.getWeek() - 1).getQcStatus().equals(QCStatus.Average))
					// previous week
					&& (notes.get(note.getWeek() - 2).getQcStatus().equals(QCStatus.Poor)
							|| notes.get(note.getWeek() - 2).getQcStatus().equals(QCStatus.Average)) ? true : false;
			if (flagged) {
				// save the flag status in database
				Trainee trainee = traineeDAO.findOne(note.getTrainee().getTraineeId());
				trainee.setFlagStatus(TraineeFlag.RED);
				// concat a generated flag message at the end of the current trainee notes
				if (trainee.getFlagNotes() != null && !trainee.getFlagNotes().contains("Trainee was automatically flagged by Caliber"))
					trainee.setFlagNotes("Trainee was automatically flagged by Caliber. " + trainee.getFlagNotes());
				else
					trainee.setFlagNotes("Trainee was automatically flagged by Caliber. ");
				
				traineeDAO.update(trainee);
			}
		}
	}

	/**
	 * FIND WEEKLY BATCH NOTES (TRAINER/PUBLIC)
	 * 
	 * @param batch
	 * @param week
	 * @return
	 */
	public List<Note> findBatchNotes(Integer batchId, Integer week) {
		log.debug(FINDING_WEEK + week + " batch notes for batch: " + batchId);
		return noteDAO.findBatchNotes(batchId, week);
	}

	/**
	 * FIND WEEKLY INDIVIDUAL NOTES (TRAINER/PUBLIC)
	 * 
	 * @param trainee
	 * @param week
	 * @return
	 */
	public List<Note> findIndividualNotes(Integer batchId, Integer week) {
		log.debug(FINDING_WEEK + week + " individual notes for batch: " + batchId);
		List<Note> notesTemp = noteDAO.findIndividualNotes(batchId, week);
		List<Note> notes = new ArrayList<>();
		if (notesTemp != null) {
			for (Note n : notesTemp) {
				n.setBatch(null);
				notes.add(n);
			}
		}
		return notes;
	}

	/**
	 * FIND TRAINEE NOTE FOR THE WEEK
	 * 
	 * @param trainee
	 * @param week
	 * @return
	 */
	public Note findTraineeNote(Integer traineeId, Integer week) {
		Note note = noteDAO.findTraineeNote(traineeId, week);

		note.setBatch(null);

		return note;
	}

	/**
	 * FIND QCTRAINEE NOTE FOR THE WEEK(Michael)
	 * 
	 * @param trainee
	 * @param week
	 * @return
	 */
	public Note findQCTraineeNote(Integer traineeId, Integer week) {
		Note note = noteDAO.findQCTraineeNote(traineeId, week);

		note.setBatch(null);

		return note;
	}

	/**
	 * FIND WEEKLY QC BATCH NOTES (NOT FOR TRAINERS)
	 * 
	 * @param batch
	 * @param week
	 * @return
	 */
	public Note findQCBatchNotes(Integer batchId, Integer week) {
		log.debug(FINDING_WEEK + week + " QC batch notes for batch: " + batchId);
		return noteDAO.findQCBatchNotes(batchId, week);
	}

	/**
	 * FIND WEEKLY QC INDIVIDUAL NOTES (NOT FOR TRAINERS)
	 * 
	 * @param trainee
	 * @param week
	 * @return
	 */
	public List<Note> findQCIndividualNotes(Integer traineeId, Integer week) {
		log.debug(FINDING_WEEK + week + " QC individual notes for trainee: " + traineeId);
		return noteDAO.findQCIndividualNotes(traineeId, week);
	}

	/**
	 * FIND ALL WEEKLY BATCH NOTES (VP ONLY)
	 * 
	 * @param batch
	 * @param week
	 * @return
	 */
	public List<Note> findAllBatchNotes(Integer batchId, Integer week) {
		log.debug(FINDING_WEEK + week + " batch notes for batch: " + batchId);
		return noteDAO.findAllBatchNotes(batchId, week);
	}

	/**
	 * Find all qc trainee notes
	 * 
	 * @return
	 */
	public List<Note> findAllQCTraineeNotes(Integer batchId, Integer week) {
		log.debug("Find All QC Trainee Notes");
		return noteDAO.findAllQCTraineeNotes(batchId, week);
	}

	public List<Note> findAllIndividualNotesOverall(Integer traineeId) {
		log.debug("Find Overall notes for trainee " + traineeId);
		return noteDAO.findAllPublicIndividualNotes(traineeId);
	}

	/**
	 * Find all qc trainee notes
	 * 
	 * @return
	 */
	public List<Note> findAllQCTraineeOverallNotes(Integer traineeId) {
		log.debug("Find All QC Trainee Notes for that trainee");
		return noteDAO.findAllQCTraineeOverallNotes(traineeId);
	}

}