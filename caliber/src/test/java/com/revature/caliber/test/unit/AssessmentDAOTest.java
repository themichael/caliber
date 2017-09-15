package com.revature.caliber.test.unit;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.data.AssessmentDAO;

public class AssessmentDAOTest extends CaliberTest{
	
	private static final Logger log = Logger.getLogger(AssessmentDAOTest.class);
	

	@Autowired
	private AssessmentDAO assessmentDao;
	private static final String ASSESSMENT_COUNT = "select count(assessment_id) from caliber_assessment";
	
	/*
	 * Tests methods:
	 * com.revature.caliber.data.AssessmentDAO.save()
	 */
	@Test
	public void saveAssessmentTest() {
		Assessment assessment = assessmentDao.findAll().get(1);
		assessmentDao.save(assessment);
		assertEquals(assessment, assessmentDao.findAll().get(1));
		}
	
	
	/*
	 * Tests methods:
	 * com.revature.caliber.data.AssessmentDAO.findOne()
	 */
	@Test
	public void findOneAssessmentTest() {
		int assessmentId = 2074;
		assessmentDao.findOne(assessmentId);
		assertEquals(assessmentId, assessmentDao.findOne(assessmentId).getAssessmentId());
		}
	
	
	/*
	 * Tests methods:
	 * com.revature.caliber.data.AssessmentDAO.findAll()
	 */
	@Test
	public void findAllAssessmentTest() {
		assertNotNull(assessmentDao.findAll());
		}
	
	
	/*
	 * Tests methods:
	 * com.revature.caliber.data.AssessmentDAO.findByWeek(batchId, weekNumber)
	 */
	@Test
	public void findAssessmentsByWeekTest() {
		int batchId = 2150;
		int weekNumber = 3;
		assertNotNull(assessmentDao.findByWeek(batchId, weekNumber));
		}
	
	
	
	/**
	 * Tests method:
	 * com.revature.caliber.data.AssessmentDAO.findByBatchId(Integer batchId)
	 */
	@Test
	public void findByBatchIdAssessmentDAOTest() {
		log.info("FIND BY BATCH ID ASSESSMENT DAO");
		Assessment assessment =assessmentDao.findAll().get(0);
		List<Assessment> assesments = assessmentDao.findByBatchId(2050);
		assertEquals(assessment, assesments.get(0));
	}
	
	/**
	 * Tests method:
	 * com.revature.caliber.data.AssessmentDAO.update(Assessment assessment)
	 */
	@Test
	public void updateAssessmentDAOTest() {
		log.info("UPDATE ASSESSMENT DAO");
		Assessment assessment =assessmentDao.findOne(2058);

		assertNotEquals(2099, assessment.getRawScore());
		
		
		log.info("Before Update "+ assessment);
		assessment.setRawScore(2099);
		assessmentDao.update(assessment);
		log.info("After Update "+ assessment);
		
		assessment =assessmentDao.findOne(2058);
		log.info("After getting again "+ assessment);


		assertEquals(2099, assessment.getRawScore());
	
	}
	
	/**
	 * Tests method:
	 * com.revature.caliber.data.AssessmentDAO.delete(Assessment assessment)
	 */
	
}

