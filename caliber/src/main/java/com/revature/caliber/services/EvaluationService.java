package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.Trainee;
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
	private static final String FINDING_WEEK = "Finding week ";
	private static final String WEEK_STATIC_FACTOR = "0";

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
		for(Grade grade : grades){
			Integer key = grade.getTrainee().getTraineeId();
			if(table.containsKey(grade.getTrainee().getTraineeId())){
				// eliminate nested records first
				grade.getAssessment().setBatch(null);
				// get the trainee's assessments and add the new assessment
				table.get(key).add(grade);
			}else{
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
		return noteDAO.save(note);
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
		List <Note> notesTemp = noteDAO.findIndividualNotes(batchId,week);
		List <Note> notes = new ArrayList<>();
		if(notesTemp !=null){
			for(Note n : notesTemp){
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
		Note note = noteDAO.findTraineeNote(traineeId,week);
		
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
		Note note = noteDAO.findQCTraineeNote(traineeId,week);
		
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
	 * @return
	 */
	public List<Note> findAllQCTraineeNotes(Integer batchId, Integer week) {
		log.debug("Find All QC Trainee Notes");
		return noteDAO.findAllQCTraineeNotes(batchId, week);
	}
	public List<Note> findAllIndividualNotesOverall(Integer traineeId){
		log.debug("Find Overall notes for trainee " + traineeId);
		return noteDAO.findAllPublicIndividualNotes(traineeId);
	}
	/**
	 * Find all qc trainee notes
	 * @return
	 */
	public List<Note> findAllQCTraineeOverallNotes(Integer traineeId) {
		log.debug("Find All QC Trainee Notes for that trainee");
		return noteDAO.findAllQCTraineeOverallNotes(traineeId);
	}
	
	/**
     * Find all qc trainee notes for all weeks
     * @return
     */
    public List<List<Note>> findAllQCTraineeNotesForAllWeeks(Integer batchId) {
        log.debug("Find All QC Trainee Notes");
        List<Note> notes = noteDAO.findAllQCTraineeNotesForAllWeeks(batchId);
        ArrayList<List<Note>> noteFormatted2d = new ArrayList<>();
        notes = notes.stream().collect(Collectors.collectingAndThen(
                Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparing(note -> {
                            String weekAsString = Short.toString(note.getWeek());
                            return note.getTrainee().getName() +
                                    ((weekAsString.length() == 1) ?
                                    		WEEK_STATIC_FACTOR + weekAsString : weekAsString) +
                                    note.getTrainee().getTraineeId();
                        }))
                ), ArrayList::new));
        if (notes == null || notes.size() < 1) {
            return new ArrayList<>();
        }

        Trainee currentTrainee = null;
        List<Note> traineeNotes = new ArrayList<>();
        for (Note note : notes) {
            Trainee aTrainee = note.getTrainee();
            //trainee HAS changed
            if (!aTrainee.equals(currentTrainee) || currentTrainee == null) {
            	//if this is not the first iteration
            	if (currentTrainee != null) {
            		noteFormatted2d.add(traineeNotes);
            	}
                currentTrainee = aTrainee;
                traineeNotes = new ArrayList<>(Collections.singletonList(note));
            } else {//trainee HAS NOT changed
            	traineeNotes.add(note);
            }
        }
        noteFormatted2d.add(traineeNotes);
        return formatJaggedArray(noteFormatted2d);
    }
    
    /**
     * Formats the jagged "2d" ArrayList of notes.  Inserts empty Notes into the missing spots so that the
     * front end batch QC reports table will display correctly. 
     * Will be called by findAllQCTraineeNotesForAllWeeks() method 
     * 
     * @return the Lists of Lists ("2d" ArrayList) formatted so that number of columns and rows are even 
     */
    public List<List<Note>> formatJaggedArray(List<List<Note>> jaggedArray){
            int maxWeeks = 0;
            for(List<Note> row : jaggedArray) {
                for(Note column : row) {
                    short week = 0;
                    week = column.getWeek();
                    if(week > maxWeeks) {
                        maxWeeks = week;
                    }
                }
            }
           
            System.out.println("Max weeks: " + maxWeeks);
            for(int row = 0; row < jaggedArray.size(); row++) {
                for(short column = 0; column < maxWeeks ; column++ ) {                           
                    if( jaggedArray.get(row).size() <= column || jaggedArray.get(row).get(column).getWeek() != column +1) {
                        Note emptyNote = new Note();
                        emptyNote.setNoteId(row + column); 	//testing
                        emptyNote.setWeek( (short) (column + 1 ));
                        jaggedArray.get(row).add( column , emptyNote );
                    }
                }
            }
            System.out.println("JaggedArray: " + jaggedArray);
            return jaggedArray;
    }
    
    /**
     * noteDAO.findAllQCBatchNotes
     * @param Integer batchId: the id of the batch 
     * @return A list of QC batch notes, in ascending order by week 
     */
    public List<Note> findAllQCBatchNotes(Integer batchId){
        log.debug("Find All QC Batch Notes in ascending order by week");
        return noteDAO.findAllQCBatchNotes(batchId);
    }
	
}
