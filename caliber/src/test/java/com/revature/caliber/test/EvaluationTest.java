package com.revature.caliber.test;

//commented out these packages because their variables are not currently in use
import java.util.List;
import java.util.Map;

//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
//import com.revature.caliber.controllers.EvaluationController;

public class EvaluationTest extends CaliberTest {

//	commented out unused variable
//	private static final Logger log = Logger.getLogger(EvaluationTest.class);

//	@Autowired
//	private EvaluationController evaluationController;
//
//	private static final String GRADE_COUNT = "select count(grade_id) from caliber_grade";
//	private static final String NOTE_COUNT = "select count(note_id) from caliber_note";

	private static final String notYetImplmented="Not yet implemented";
	//////////////////////////////////////////////////////////////////////////////////////////////////
	// EVALUATION API
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#createGrade(Grade)
	 */
	public Grade createGrade() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#updateGrade(Grade)
	 */
	public void updateGrade() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAll()
	 */
	public List<Grade> findAll() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByAssessment(Long)
	 */
	public List<Grade> findByAssessment() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainee(Integer)
	 */
	public List<Grade> findByTrainee() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByBatch(Integer)
	 */
	public List<Grade> findByBatch() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByCategory(Integer)
	 */
	public List<Grade> findByCategory() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByWeek(Integer, Integer)
	 */
	public Map<Integer, List<Grade>> findByWeek() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findByTrainer(Integer)
	 */
	public List<Grade> findByTrainer() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#createNote(Note)
	 */
	public Integer createNote() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#updateNote(Note)
	 */
	public Note updateNote() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findBatchNotes(Integer, Integer)
	 */
	public List<Note> findBatchNotes() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findIndividualNotes(Integer, Integer)
	 */
	public List<Note> findIndividualNotes() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findTraineeNote(Integer, Integer)
	 */
	public Note findTraineeNote() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findQCTraineeNote(Integer, Integer)
	 */
	public Note findQCTraineeNote() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findQCBatchNotes(Integer, Integer)
	 */
	public Note findQCBatchNotes() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#getAllQCTraineeNotes(Integer, Integer)
	 */
	public List<Note> getAllQCTraineeNotes() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#getAllQCTraineeOverallNotes(Integer)
	 */
	public List<Note> getAllQCTraineeOverallNotes() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllBatchNotes(Integer, Integer)
	 */
	public List<Note> findAllBatchNotes() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllIndividualNotes(Integer, Integer)
	 */
	public List<Note> findAllIndividualNotes() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.EvaluationController#findAllTraineeNotes(Integer)
	 */
	public List<Note> findAllTraineeNotes() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

}
