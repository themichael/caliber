package com.revature.test;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.data.AssessmentDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/beans.xml" })
public class AssessmentDAOTest {
	
	private AssessmentDAO aDAO;
	private int batchId;
	private int id;
	private int week;
	private Assessment assessment;

	@Before
	public void setup() {
		/*
		 * this assessment and ID is hard-coded straight out of the database,
		 * change this as needed. 
		 */
		
		aDAO = new AssessmentDAO();
		assessment = new Assessment();
		id=1050;
		week=1;
		
		assessment.setAssessmentId(id);
		assessment.setRawScore(100);
		assessment.setTitle("Java Test");
		assessment.setType(AssessmentType.Exam);
		assessment.setWeek((short)week);
		
		

	}

	@Test
	public void testSetSessionFactory() {
		
		//fail("Not yet implemented");
	}

	@Test
	public void testSave() {
		aDAO.save(assessment);
		
		//fail("Not yet implemented"); 
	}

	@Test
	public void testFindOne() {
		aDAO.findOne(batchId);
		
		//fail("Not yet implemented"); 
	}

	@Test
	public void testFindAll() {
		aDAO.findAll();
		//fail("Not yet implemented"); 
	}

	@Test
	public void testFindByWeek() {
		aDAO.findByWeek(batchId, week);
		//fail("Not yet implemented"); 
	}

	@Test
	public void testFindByBatchId() {
		aDAO.findByBatchId(batchId);
		//fail("Not yet implemented"); 
	}

	@Test
	public void testUpdate() {
		aDAO.update(assessment);
		//fail("Not yet implemented"); 
	}

	@Test
	public void testDelete() {
		aDAO.delete(assessment);
		//fail("Not yet implemented"); 
	}

}
