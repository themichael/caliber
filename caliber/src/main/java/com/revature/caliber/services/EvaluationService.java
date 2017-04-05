package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.NoteDAO;

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

	@Autowired
	public void setGradeDAO(GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
	}

	@Autowired
	public void setNoteDAO(NoteDAO noteDAO) {
		this.noteDAO = noteDAO;
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
	 * FIND ALL GRADES
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<Grade> findAllGrades() {
		log.debug("Finding all grades");
		return gradeDAO.findAll();
	}

	/**
	 * FIND GRADES BY ASSESSMENT
	 * 
	 * @param assessmentId
	 * @return
	 */
	public List<Grade> findGradesByAssessment(Long assessmentId) {
		log.debug("Finding grades for assessment: " + assessmentId);
		return gradeDAO.findByAssessment(assessmentId);
	}

	/**
	 * FIND GRADES BY TRAINEE
	 * 
	 * @param traineeId
	 * @return
	 */
	public List<Grade> findGradesByTrainee(Integer traineeId) {
		log.debug("Finding all grades for trainee: " + traineeId);
		return gradeDAO.findByTrainee(traineeId);
	}

	/**
	 *	FIND GRADES BY BATCH
	 * 
	 * @param batchId
	 * @return
	 */
	public List<Grade> findGradesByBatch(Integer batchId) {
		log.debug("Finding all grades for batch: " + batchId);
		return gradeDAO.findByBatch(batchId);
	}

	/**
	 * FIND GRADES BY CATEGORY
	 * 
	 * @param batchId
	 * @return
	 */
	public List<Grade> findGradesByCategory(Integer categoryId) {
		log.debug("Finding all grades for category: " + categoryId);
		return gradeDAO.findByCategory(categoryId);
	}

	/**
	 * FIND GRADES BY WEEK
	 * 
	 * @param batchId
	 * @param week
	 * @return
	 */
	public List<Grade> findGradesByWeek(Integer batchId, Integer week) {
		log.debug("Finding week " + week + " grades for batch: " + batchId);
		return gradeDAO.findByWeek(batchId, week);
	}

	/**
	 *	FIND GRADES BY TRAINER
	 * 
	 * @param trainerId
	 * @return
	 */
	public List<Grade> findGradesByTrainer(Integer trainerId) {
		log.debug("Finding all grades for trainer: " + trainerId);
		return gradeDAO.findByTrainer(trainerId);
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
	public void save(Note note) {
		log.debug("Saving note: " + note);
		noteDAO.save(note);
	}

	/**
	 * UPDATE NOTE
	 * 
	 * @param note
	 */
	public void update(Note note) {
		log.debug("Updating note: " + note);
		noteDAO.update(note);
	}

	/**
	 * FIND WEEKLY BATCH NOTES (TRAINER/PUBLIC)
	 * 
	 * @param batch
	 * @param week
	 * @return
	 */
	public List<Note> findBatchNotes(Integer batchId, Integer week) {
		log.debug("Finding week " + week + " batch notes for batch: " + batchId);
		return noteDAO.findBatchNotes(batchId, week);
	}

	/**
	 * FIND WEEKLY INDIVIDUAL NOTES (TRAINER/PUBLIC)
	 * 
	 * @param trainee
	 * @param week
	 * @return
	 */
	public List<Note> findIndividualNotes(Integer traineeId, Integer week) {
		log.debug("Finding week " + week + " individual notes for trainee: " + traineeId);
		return noteDAO.findIndividualNotes(traineeId, week);
	}

	/**
	 * FIND WEEKLY QC BATCH NOTES (NOT FOR TRAINERS)
	 * 
	 * @param batch
	 * @param week
	 * @return
	 */
	public Note findQCBatchNotes(Integer batchId, Integer week) {
		log.debug("Finding week " + week + " QC batch notes for batch: " + batchId);
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
		log.debug("Finding week " + week + " QC individual notes for trainee: " + traineeId);
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
		log.debug("Finding week " + week + " batch notes for batch: " + batchId);
		return noteDAO.findAllBatchNotes(batchId, week);
	}

	/**
	 * FIND ALL WEEKLY INDIVIDUAL NOTES (VP ONLY)
	 * 
	 * @param trainee
	 * @param week
	 * @return
	 */
	public List<Note> findAllIndividualNotes(Integer traineeId, Integer week) {
		log.debug("Finding all week " + week + " individual notes for trainee: " + traineeId);
		return noteDAO.findAllIndividualNotes(traineeId, week);
	}

	public List<Note> findAllQCNotes() {
        log.debug("Find All QC notes");
        return noteDAO.findAllQCNotes();
    }
}