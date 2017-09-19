package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.services.ReportingService;

public class ReportingServiceTest extends CaliberTest{

	private static final Logger log = Logger.getLogger(ReportingServiceTest.class);
	
	private static final int TEST_BATCH_ID = 2150;
	private static final int TEST_ASSESSMENT_WEEK = 6;
	private static final int TEST_TRAINEE_ID = 5460;
	private static final double FLOATING_NUMBER_VARIANCE = .01;

	
	@Autowired
	ReportingService reportingService;
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	// REPORTING SERVICE
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getAllCurrentBatchesLineChartConcurrent()
	 */
	@Test
	public void testGetAllCurrentBatchesLineChartConcurrent() {
		log.info("Testing getAllCurrentBatchesLineChartConcurrent");
		
		final String key = "1702 Feb13 Java (AP)";
		Map<String, Map<Integer, Double>> batches = reportingService.getAllCurrentBatchesLineChartConcurrent();
		
		log.debug(batches);
		
		// check that expected batch is there
		assertTrue(batches.containsKey(key));
		// check that batch has all expected week averages
		assertEquals(7, batches.get(key).size());
		// check that each week grade averages are what is expected
		assertEquals(68.34, batches.get(key).get(1), FLOATING_NUMBER_VARIANCE);
		assertEquals(84.96, batches.get(key).get(2), FLOATING_NUMBER_VARIANCE);
		assertEquals(76.83, batches.get(key).get(3), FLOATING_NUMBER_VARIANCE);
		assertEquals(75.09, batches.get(key).get(4), FLOATING_NUMBER_VARIANCE);
		assertEquals(77.94, batches.get(key).get(5), FLOATING_NUMBER_VARIANCE);
		assertEquals(82.80, batches.get(key).get(6), FLOATING_NUMBER_VARIANCE);
		assertEquals(74.27, batches.get(key).get(7), FLOATING_NUMBER_VARIANCE);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeUpToWeekRadarChart(Integer, Integer)
	 */
	@Test
	public void testGetTraineeUpToWeekRadarChart() {
		log.info("Testing getTraineeUpToWeekRadarChart");
		
		Map<String, Double> traineeSkills = reportingService.getTraineeUpToWeekRadarChart(TEST_TRAINEE_ID, TEST_ASSESSMENT_WEEK);
		// check that trainee has all expected skills
		assertEquals(6, traineeSkills.size());
		// check that each expected skill is there and has expected average
		assertEquals(82.92, traineeSkills.get("Hibernate"), FLOATING_NUMBER_VARIANCE);
		assertEquals(80.40, traineeSkills.get("JSP"), FLOATING_NUMBER_VARIANCE);
		assertEquals(67.79, traineeSkills.get("Java"), FLOATING_NUMBER_VARIANCE);
		assertEquals(93.10, traineeSkills.get("JavaScript"), FLOATING_NUMBER_VARIANCE);
		assertEquals(91.55, traineeSkills.get("SQL"), FLOATING_NUMBER_VARIANCE);
		assertEquals(79.20, traineeSkills.get("Spring"), FLOATING_NUMBER_VARIANCE);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeOverallRadarChart(Integer)
	 */
	@Test
	public void testGetTraineeOverallRadarChart() {
		log.info("Testing getTraineeOverallRadarChart");
		
		Map<String, Double> traineeSkills = reportingService.getTraineeOverallRadarChart(TEST_TRAINEE_ID);
		// check that trainee has all expected skills
		assertEquals(7, traineeSkills.size());
		// check that each expected skill is there and has expected average
		assertEquals(82.92, traineeSkills.get("Hibernate"), FLOATING_NUMBER_VARIANCE);
		assertEquals(80.40, traineeSkills.get("JSP"), FLOATING_NUMBER_VARIANCE);
		assertEquals(67.79, traineeSkills.get("Java"), FLOATING_NUMBER_VARIANCE);
		assertEquals(93.10, traineeSkills.get("JavaScript"), FLOATING_NUMBER_VARIANCE);
		assertEquals(91.55, traineeSkills.get("SQL"), FLOATING_NUMBER_VARIANCE);
		assertEquals(79.20, traineeSkills.get("Spring"), FLOATING_NUMBER_VARIANCE);
		assertEquals(83.60, traineeSkills.get("REST"), FLOATING_NUMBER_VARIANCE);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeUpToWeekLineChart(int, int, int)
	 */
	@Test
	public void getTraineeUpToWeekLinechart(){
		
		/*
		 * Method description:
		 * input: batch, week, and trainee
		 * output: map of week and scores 
		 */
		
		//get chart data which should be a map of week and scores
		Map<Integer, Double[]> averageGrades =
				reportingService.getTraineeUpToWeekLineChart(
						TEST_BATCH_ID,
						TEST_ASSESSMENT_WEEK,
						TEST_TRAINEE_ID);
		
		//size should be 6 for week 6
		assertEquals(TEST_ASSESSMENT_WEEK, averageGrades.size());
		
		//equal to data from database
		assertEquals(72.74, averageGrades.get(1)[0], FLOATING_NUMBER_VARIANCE);
		assertEquals(75.04, averageGrades.get(6)[0], FLOATING_NUMBER_VARIANCE);
		
		//week 7 should not exist
		assertNull(averageGrades.get(7));
	}
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeOverallLineChart(int, int)
	 */
	@Test
	public void getTraineeOverallLineChart(){
		/*
		 * Method description:
		 * input: batch, and trainee
		 * output: map of week and double array for average with set size of 2
		 * [0: trainee, 1: batch]
		 */
		
		Map<Integer, Double[]> overallGrades =
				reportingService.getTraineeOverallLineChart(TEST_BATCH_ID, TEST_TRAINEE_ID);
		
		//batch had 7 weeks total
		assertEquals(7, overallGrades.size());
		
		
		//scores should only have 2 values
		Double[] weekAverage = overallGrades.get(7);
		assertEquals(2, weekAverage.length);
		
		//week 8 should not exist
		assertNull(overallGrades.get(8));
		
	}
}
