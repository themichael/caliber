package com.revature.caliber.test.integration;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.services.EvaluationService;

public class EvaluationTest extends CaliberTest {


	private static final String NOT_YET_IMPLEMENTED="Not yet implemented";
	private static final int TEST_BATCH_ID = 2150;
	private static final int TEST_ASSESSMENT_WEEK = 7;


	
	@Autowired
	EvaluationService evaluationService;
	@Autowired
	BatchDAO batchDAO;
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	// EVALUATION API
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#createGrade(Grade)
	 */
	public Grade createGrade() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#updateGrade(Grade)
	 */
	public void updateGrade() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAll()
	 */
	public List<Grade> findAll() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByAssessment(Long)
	 */
	public List<Grade> findByAssessment() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainee(Integer)
	 */
	public List<Grade> findByTrainee() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByBatch(Integer)
	 */
	public List<Grade> findByBatch() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByCategory(Integer)
	 */
	public List<Grade> findByCategory() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByWeek(Integer, Integer)
	 */
	public Map<Integer, List<Grade>> findByWeek() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainer(Integer)
	 */
	public List<Grade> findByTrainer() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#createNote(Note)
	 */
	public Integer createNote() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#updateNote(Note)
	 */
	public Note updateNote() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findBatchNotes(Integer, Integer)
	 */
	public List<Note> findBatchNotes() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findIndividualNotes(Integer, Integer)
	 */
	public List<Note> findIndividualNotes() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findTraineeNote(Integer, Integer)
	 */
	public Note findTraineeNote() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findQCTraineeNote(Integer, Integer)
	 */
	public Note findQCTraineeNote() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findQCBatchNotes(Integer, Integer)
	 */
	public Note findQCBatchNotes() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#getAllQCTraineeNotes(Integer, Integer)
	 */
	public List<Note> getAllQCTraineeNotes() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#getAllQCTraineeOverallNotes(Integer)
	 */
	public List<Note> getAllQCTraineeOverallNotes() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllBatchNotes(Integer, Integer)
	 */
	public List<Note> findAllBatchNotes() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllIndividualNotes(Integer, Integer)
	 */
	public List<Note> findAllIndividualNotes() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllTraineeNotes(Integer)
	 */
	public List<Note> findAllTraineeNotes() {
		throw new UnsupportedOperationException(NOT_YET_IMPLEMENTED);
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	// EVALUATION SERVICE
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Test methods:
	 * 
	 * @see com.revature.caliber.services.EvaluationService#findGradesByWeek(Integer, Integer)
	 * 
	 */
	@Test
	public void findGradesByWeek(){
		//get test batch
		Batch batch = batchDAO.findOne(TEST_BATCH_ID);
		
		//get grades
		Map<Integer,List<Grade>> grades = 
				evaluationService.findGradesByWeek(batch.getBatchId(), TEST_ASSESSMENT_WEEK);
		
		//assert size of result as expected in test data
		assertEquals(13, grades.size());
		
		//iterate through map entries and assert it is of test week
		for (Map.Entry<Integer, List<Grade>> entry : grades.entrySet())
		{
		    List<Grade> weekGrades = entry.getValue();
		    for(Grade grade: weekGrades){
		    	assertEquals(TEST_ASSESSMENT_WEEK, grade.getAssessment().getWeek());
		    }
		}
		
		
	}

}
