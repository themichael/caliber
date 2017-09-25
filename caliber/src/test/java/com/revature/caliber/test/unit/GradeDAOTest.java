package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

@Transactional
public class GradeDAOTest extends CaliberTest {
	
	private static final Logger log = Logger.getLogger(GradeDAOTest.class);
	
	private static final int testAssessmentId = 2063;
	private static final double testNewScore = 27.66;
	private static final double floatingNumberVarience = .01;
	private static final int testTraineeId = 5529;
	private static final int testBatchId = 2150;
	private static final int testCategoryId = 12;
	private static final int testTrainerId = 1;
	private static final int testAssessmentWeek = 7;

	@Autowired
	private GradeDAO gradeDAO;
	@Autowired
	private TraineeDAO traineeDAO;
	@Autowired
	private BatchDAO batchDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	@Autowired
	private AssessmentDAO assessmentDAO;
	@Autowired
	private TrainerDAO trainerDAO;
	
	/**
	 * Test methods:
	 * Positive tests for creating grades
	 * @see com.revature.caliber.data.GradeDAO#save(Grade)
	 */
	@Test
	public void saveValidGrade(){
		log.trace("CREATING VALID GRADES");
		
		//dependencies
		Assessment assessment = assessmentDAO.findOne(testAssessmentId);
		Trainee trainee = traineeDAO.findOne(testTraineeId);
		
		
		/*
		 * Positive tests. Change according to change in business rules
		 * Test edge cases such as 1 and 100. 
		 * If business rules allow 0, test 0. If not, test 0.01
		 * If business rules allow above 100, change tests to match.
		 * 
		 */
		
		Grade lowGrade = new Grade(assessment, trainee, new Date(), 1);
		gradeDAO.save(lowGrade);
		Grade highGrade = new Grade(assessment, trainee, new Date(), 100);
		gradeDAO.save(highGrade);

	}
	
	/**
	 * Test methods:
	 * Negative tests for creating grades
	 * Currently ignored because there are no dao-level validations
	 * @see com.revature.caliber.data.GradeDAO#save(Grade)
	 */
	@Test
	@Ignore
	public void saveInvalidGrades(){
		log.trace("CREATING INVALID GRADES");
		
		//dependencies
		Trainee trainee = traineeDAO.findOne(testTraineeId);
		
		/*
		 * Negative tests. Test anything that isn't allowed by business rules
		 * 
		 */
		try{
			Grade noConstraintGrade = new Grade(null, null, null, -0.10);
			gradeDAO.save(noConstraintGrade);
			fail();
		} catch(ConstraintViolationException e){
			log.trace(e);
		}
		try{
			Grade noAssessmentGrade = new Grade(null, trainee, new Date(), 353291.3);
			gradeDAO.save(noAssessmentGrade);
			fail();
		} catch(ConstraintViolationException e){
			log.trace(e);
		}
	}
	
	/**
	 * Test methods:
	 * @see com.revature.caliber.data.GradeDAO#updateGrade(Grade)
	 */
	@Test
	public void updateGrade(){
		log.trace("UPDATING GRADES");
		
		//get grade
		Grade grade = gradeDAO.findAll().get(0);
		
		//make sure old and new are not the same
		assertNotEquals(testNewScore, grade.getScore());
		
		//update
		grade.setScore(testNewScore);
		gradeDAO.update(grade);
		
		//assert change persisted
		assertEquals(testNewScore,grade.getScore(), floatingNumberVarience);
		
	}
	
	/**
	 * Test methods:
	 * @see com.revature.caliber.data.GradeDAO#findAll()
	 */
	@Test
	public void findAll(){
		log.trace("GETTING ALL GRADES");
		List<Grade> grades = gradeDAO.findAll();
		assertTrue(grades.size() > 0);
	}
	
	/**
	 * Test methods:
	 * @see com.revature.caliber.data.GradeDAO#findByAssessment(Long)
	 */
	@Test
	public void findByAssessment(){
		log.trace("GETTING GRADES FOR ASSESSMENT");
		
		//get assessment
		Assessment assessment = assessmentDAO.findOne(testAssessmentId);
		//find all grades for assessment
		List<Grade> grades = gradeDAO.findByAssessment(assessment.getAssessmentId());
		
		//assert each grade is of assesssment
		for(Grade grade: grades){
			assertEquals(assessment, grade.getAssessment());
		}
	}
	
	/**
	 * Test methods:
	 * @see com.revature.caliber.data.GradeDAO#findByTrainee(Integer)
	 */
	@Test
	public void findByTrainee(){
		log.trace("GETTING GRADES FOR TRAINEE");
		
		//get trainee 
		Trainee trainee = traineeDAO.findOne(testTraineeId);
		
		//find all grades for that trainee
		List<Grade> grades = gradeDAO.findByTrainee(trainee.getTraineeId());
		
		//loop through and assert that each grade belongs to trainee
		for(Grade grade: grades){
			assertEquals(trainee, grade.getTrainee());
		}
	}
	
	/**
	 * Test methods:
	 * @see com.revature.caliber.data.GradeDAO#findByBatch(Integer)
	 */
	@Test
	public void findByBatch(){
		log.trace("GETTING GRADES FOR BATCH");
		
		//get batch
		Batch batch = batchDAO.findOne(testBatchId);
		
		//find all grades for batch
		List<Grade> grades = gradeDAO.findByBatch(batch.getBatchId());
		
		//assert that each grade belongs to batch

		for(Grade grade : grades){
			assertEquals(batch, grade.getTrainee().getBatch());
		}
	
	}
	
	/**
	 * Test methods:
	 * @see com.revature.caliber.data.GradeDAO#findByCategory(Integer)
	 */
	@Test
	public void findByCategory(){
		log.trace("GETTING GRADES FOR CATEGORY");
		
		//get category
		Category category = categoryDAO.findOne(testCategoryId);
		
		//get all grades of category
		List<Grade> grades = gradeDAO.findByCategory(category.getCategoryId());
		
		//assert that each grade belongs to category
		for(Grade grade: grades){
			assertEquals(category, grade.getAssessment().getCategory());
		}

	}
	
	/**
	 * Test methods:
	 * @see com.revature.caliber.data.GradeDAO#findByWeek(Integer, Integer)
	 */
	@Test
	public void findByWeek(){	
		log.trace("GETTING GRADES FOR WEEK AND BATCH");
		
		//get list
		List<Grade> grades = gradeDAO.findByWeek(testBatchId, testAssessmentWeek);
		
		//assert that grades were for an assessment created for week and for batch
		for(Grade grade: grades){
			assertEquals(testAssessmentWeek, grade.getAssessment().getWeek());
			assertEquals(testBatchId, grade.getAssessment().getBatch().getBatchId());
		}
	}
	
	/**
	 * Test methods:
	 * @see com.revature.caliber.data.GradeDAO#findByTrainer(Integer)
	 */
	@Test 
	public void findByTrainer(){
		log.trace("GETTING GRADES FOR TRAINER");
		
		//get trainer
		Trainer trainer = trainerDAO.findOne(testTrainerId);
		//get grades for trainer
		List<Grade> grades = gradeDAO.findByTrainer(trainer.getTrainerId());
		
		//assert grade belongs to trainer
		for(Grade grade: grades){
			assertEquals(trainer, grade.getTrainee().getBatch().getTrainer());
		}

		
	}
	
}
