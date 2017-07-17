package com.revature.caliber.test;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.controllers.EvaluationController;

public class EvaluationTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(EvaluationTest.class);

	@Autowired
	private EvaluationController evaluationController;

	private static final String GRADE_COUNT = "select count(grade_id) from caliber_grade";
	private static final String NOTE_COUNT = "select count(note_id) from caliber_note";

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// EVALUATION API
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#createGrade(Grade)
	 */
	public Grade createGrade(Grade grade) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#updateGrade(Grade)
	 */
	public void updateGrade(Grade grade) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAll()
	 */
	public List<Grade> findAll() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByAssessment(Long)
	 */
	public List<Grade> findByAssessment(Long assessmentId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainee(Integer)
	 */
	public List<Grade> findByTrainee(Integer traineeId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByBatch(Integer)
	 */
	public List<Grade> findByBatch(Integer batchId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByCategory(Integer)
	 */
	public List<Grade> findByCategory(Integer categoryId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByWeek(Integer, Integer)
	 */
	public Map<Integer, List<Grade>> findByWeek(Integer batchId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainer(Integer)
	 */
	public List<Grade> findByTrainer(Integer trainerId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#createNote(Note)
	 */
	public Integer createNote(Note note) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#updateNote(Note)
	 */
	public Note updateNote(Note note) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findBatchNotes(Integer, Integer)
	 */
	public List<Note> findBatchNotes(Integer batchId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findIndividualNotes(Integer, Integer)
	 */
	public List<Note> findIndividualNotes(Integer batchId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findTraineeNote(Integer, Integer)
	 */
	public Note findTraineeNote(Integer traineeId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findQCTraineeNote(Integer, Integer)
	 */
	public Note findQCTraineeNote(Integer traineeId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findQCBatchNotes(Integer, Integer)
	 */
	public Note findQCBatchNotes(Integer batchId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#getAllQCTraineeNotes(Integer, Integer)
	 */
	public List<Note> getAllQCTraineeNotes(Integer batchId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#getAllQCTraineeOverallNotes(Integer)
	 */
	public List<Note> getAllQCTraineeOverallNotes(Integer traineeId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllBatchNotes(Integer, Integer)
	 */
	public List<Note> findAllBatchNotes(Integer batchId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllIndividualNotes(Integer, Integer)
	 */
	public List<Note> findAllIndividualNotes(Integer traineeId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllTraineeNotes(Integer)
	 */
	public List<Note> findAllTraineeNotes(Integer traineeId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
