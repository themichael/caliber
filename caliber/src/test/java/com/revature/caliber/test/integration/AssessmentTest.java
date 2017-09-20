package com.revature.caliber.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
import com.revature.caliber.controllers.AssessmentController;
import com.revature.caliber.controllers.CategoryController;
import com.revature.caliber.controllers.TrainingController;

public class AssessmentTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(AssessmentTest.class);

	@Autowired
	private TrainingController trainingController;
	@Autowired
	private AssessmentController assessmentController;
	@Autowired
	private CategoryController categoryController;

	private static final String ASSESSMENT_COUNT = "select count(assessment_id) from caliber_assessment";

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// ASSESSMENT API
	//////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.AssessmentController#createAssessment(Assessment)
	 */
	@Test
	public void createAssessment() {
		// positive testing
		log.info("CREATE ASSESSMENT");
		Batch batch = trainingController.getAllBatches().getBody().get(0);
		Category category = categoryController.findCategoryById(1).getBody();
		Assessment assessment = new Assessment("", batch, 9999, AssessmentType.Project, 3, category);
		Long before = jdbcTemplate.queryForObject(ASSESSMENT_COUNT, Long.class);
		assessmentController.createAssessment(assessment);

		// fail validations
		try {
			Assessment nullBatch = new Assessment("", null, 9999, AssessmentType.Project, 3, category);
			assessmentController.createAssessment(nullBatch);
			fail();
		} catch (ConstraintViolationException e) {
			log.debug(e);
		}
		try {
			Assessment negativeRawScore = new Assessment("", batch, -1, AssessmentType.Project, 3, category);
			assessmentController.createAssessment(negativeRawScore);
			fail();
		} catch (ConstraintViolationException e) {
			log.debug(e);
		}
		try {
			Assessment nullType = new Assessment("", batch, 9999, null, 3, category);
			assessmentController.createAssessment(nullType);
			fail();
		} catch (ConstraintViolationException e) {
			log.debug(e);
		}
		try {
			Assessment invalidWeek = new Assessment("", batch, 9999, AssessmentType.Project, -5, category);
			assessmentController.createAssessment(invalidWeek);
			fail();
		} catch (ConstraintViolationException e) {
			log.debug(e);
		}
		try {
			Assessment nullCategory = new Assessment("", batch, 9999, AssessmentType.Project, 3, null);
			assessmentController.createAssessment(nullCategory);
			fail();
		} catch (ConstraintViolationException e) {
			log.debug(e);
		}

		Long after = jdbcTemplate.queryForObject(ASSESSMENT_COUNT, Long.class);
		assertEquals(++before, after);
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.AssessmentController#deleteAssessment(Long)
	 */
	@Test
	public void deleteAssessment() {
		log.info("DELETE ASSESSMENT");
		Long before = jdbcTemplate.queryForObject(ASSESSMENT_COUNT, Long.class);
		assessmentController.deleteAssessment(2056L);
		Long after = jdbcTemplate.queryForObject(ASSESSMENT_COUNT, Long.class);
		assertEquals(--before, after);
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.AssessmentController#updateAssessment(Assessment)
	 */
	@Test
	public void updateAssessment() {
		log.info("UPDATE ASSESSMENT");
		int rawScore = 9999;
		Assessment assessment = assessmentController.findAssessmentByWeek(2200, 3).getBody().get(0);
		assertNotEquals(rawScore, assessment.getRawScore());
		assessment.setRawScore(rawScore);
		assessmentController.updateAssessment(assessment);
		assertEquals(rawScore, assessment.getRawScore());
	}

	/**
	 * Tests methods:
	 * @see com.revature.caliber.controllers.AssessmentController#findAssessmentByWeek(Integer, Integer)
	 */
	@Test
	public void findAssessmentByWeek() {
		log.info("FIND ASSESSMENT BY WEEK");
		assertNotNull(assessmentController.findAssessmentByWeek(2200, 3));
	}

}
