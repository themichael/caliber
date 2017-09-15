package com.revature.caliber.test.unit;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Assessment;

import com.revature.caliber.data.AssessmentDAO;

public class AssessmentDAOTest extends CaliberTest{
	
	@Autowired
	private AssessmentDAO assessmentdao;
	
	private Assessment assessment;
	
	/*
	 * Tests methods:
	 * com.revature.caliber.data.AssessmentDAO.save()
	 */
	@Test
	public void saveAssessmentTest() {
		
		List<Assessment> assessments1 = assessmentdao.findAll();
		
		assessment = assessmentdao.findAll().get(1);
		assessmentdao.save(assessment);
		
		List<Assessment> assessments2 = assessmentdao.findAll();
		
		assertEquals(assessments1.size() + 1, assessments2.size());
		
	}
	
	
	/*
	 * Tests methods:
	 * com.revature.caliber.data.AssessmentDAO.findOne()
	 */
	@Test
	public void findOneAssessmentTest() {
		
		int assessmentId = 2074;
		assessmentdao.findOne(assessmentId);
		
		assertEquals(assessmentId, assessmentdao.findOne(assessmentId).getAssessmentId());
		
	}
	
	
	/*
	 * Tests methods:
	 * com.revature.caliber.data.AssessmentDAO.findAll()
	 */
	@Test
	public void findAllAssessmentTest() {
		
		assertNotNull(assessmentdao.findAll());
		
	}
	
	
	/*
	 * Tests methods:
	 * com.revature.caliber.data.AssessmentDAO.findByWeek(batchId, weekNumber)
	 */
	@Test
	public void findAssessmentsByWeekTest() {
		
		int batchId = 2150;
		int weekNumber = 3;
		
		assertNotNull(assessmentdao.findByWeek(batchId, weekNumber));
		
	}
	
	

}

