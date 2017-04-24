package com.revature.caliber;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.NoteDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.services.ReportingService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/beans.xml"})
public class ReportingServiceTest {

	private final static Logger log = Logger.getLogger(ReportingService.class);
	
	private GradeDAO gradeDAO;
	private BatchDAO batchDAO;
	private TraineeDAO traineeDAO;
	private NoteDAO noteDAO;
	
	ReportingService reportingService = new ReportingService();
	
	
	@Test
	@Autowired
	public void testSetGradeDAO() {
		try{
		log.info("Testing setGradeDAO()");
		reportingService.setGradeDAO(gradeDAO);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testSetBatchDAO() {
		try{
		log.info("Testing setBatchDAO()");
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void testSetTraineeDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetNoteDAO() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBatchWeekPieChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBatchWeekAvgBarChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBatchWeekSortedBarChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBatchOverallTraineeBarChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBatchOverallBarChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBatchWeekTraineeBarChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTraineeUpToWeekLineChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTraineeOverallLineChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBatchOverallLineChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTraineeUpToWeekRadarChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetTraineeOverallRadarChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetBatchOverallRadarChart() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilAvgTraineeWeekIntegerIntegerAssessmentType() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilAvgBatchWeekIntegerIntegerAssessmentType() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilAvgTraineeOverallIntegerAssessmentType() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilAvgBatchOverallIntegerAssessmentType() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilAvgTraineeWeekIntegerInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilAvgBatchWeekIntegerInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilAvgTraineeOverallInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilAvgBatchOverallInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilAvgSkills() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilReplaceCategoryWithSkillName() {
		fail("Not yet implemented");
	}

	@Test
	public void testUtilAvgBatchWeekValue() {
		fail("Not yet implemented");
	}

}
