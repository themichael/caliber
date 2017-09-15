package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.data.AssessmentDAO;



public class AssessmentDAOTest extends CaliberTest{
	
	private static final Logger log = Logger.getLogger(AssessmentDAOTest.class);
	

	private Assessment assessment;
	@Autowired
	private AssessmentDAO assessmentDao;
	private static final String ASSESSMENT_COUNT = "select count(assessment_id) from caliber_assessment";
	
	
	
	/**
	 * Tests method:
	 * com.revature.caliber.data.AssessmentDAO.findByBatchId
	 */
	@Test
	public void findByBatchIdAssessmentDAOTest() {
		log.info("FIND BY BATCH ID ASSESSMENT DAO");
		assessment =assessmentDao.findAll().get(0);
		List<Assessment> assesments = assessmentDao.findByBatchId(2050);
		assertEquals(assessment, assesments.get(0));
	}
	
	/**
	 * Tests method:
	 * com.revature.caliber.data.AssessmentDAO.update
	 */
	@Test
	public void updateAssessmentDAOTest() {
		log.info("UPDATE ASSESSMENT DAO");
		int rawScore = 2099;
		assessment =assessmentDao.findAll().get(1);
		int oldValue =assessment.getRawScore();
		assertNotEquals(rawScore, oldValue);
		
		assessment.setRawScore(rawScore);
		assessmentDao.update(assessment);
		
		log.fatal(assessment.getRawScore());
		assessment =assessmentDao.findAll().get(1);
		log.fatal(assessment.getRawScore());

		assertEquals(rawScore, assessment.getRawScore());
	
	}
	
	/**
	 * Tests method:
	 * com.revature.caliber.data.AssessmentDAO.delete
	 */
	@Test
	public void deleteAssessmentDAOTest() {
		log.info("DELETE ASSESSMENT DAO");
		Long beforeTest = jdbcTemplate.queryForObject(ASSESSMENT_COUNT, Long.class);
		assessmentDao.delete(assessmentDao.findAll().get(1));
		Long afterTest = jdbcTemplate.queryForObject(ASSESSMENT_COUNT, Long.class);
		assertEquals(--beforeTest, afterTest);
		
	}
	
}
