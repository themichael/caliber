package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Ignore;
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
	@Ignore
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
	@Ignore
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
	@Ignore
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
	@Ignore
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
	@Ignore
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
	
	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getBatchOverallLineChart(int)
	 */
	
	@Test
	public void testGetBatchOverallLineChart() {
		
		/*
		 * Method description:
		 * input: batchId
		 * output: map of week and scores 
		 */
		
		final double actualWeek1Score=80.26; 
		final double actualWeek2Score=92.69;
		final double actualWeek3Score=86.66;
		final double actualWeek4Score=84.79;
		final double actualWeek5Score=87.84;
		final double actualWeek6Score=84.93;
		final double actualWeek7Score=83.27;
		
		Map<Integer,Double> map = reportingService.getBatchOverallLineChart(TEST_BATCH_ID); 
		
		//batch had 7 weeks total 
		assertEquals(7,map.size()); 
		
		//grades are equal
		assertEquals(map.get(1), actualWeek1Score, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(2), actualWeek2Score, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(3), actualWeek3Score, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(4), actualWeek4Score, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(5), actualWeek5Score, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(6), actualWeek6Score, FLOATING_NUMBER_VARIANCE);
		assertEquals(map.get(7), actualWeek7Score, FLOATING_NUMBER_VARIANCE);
		
		//week 8 should not exist 
		assertNull(map.get(8));

	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllCurrentBatchesLineChart() {
		/*
		 * Method description:
		 * output: map of current batch and its respective data of 
		 * (address, label of start date & trainer, and list of grades) 
		 */
		
		List<Object> results =  reportingService.getAllCurrentBatchesLineChart(); 
		
		for(Object num : results) {
			
			Map<String,List<String>> data = (Map<String, List<String>>) num;   
			
			//current batch keys should have address, label and grades
			assertTrue(data.containsKey("address"));
			assertTrue(data.containsKey("label"));
			assertTrue(data.containsKey("grades"));
			
		}
		//current batch data should have 3 
		assertTrue(results.size() == 3); 
		
		
	}
	
	@Test
	public void testGetAvgBatchWeekValue() {
		/*
		 * Method description:
		 * input: batchId, and week
		 * output: average batch week 6 value 
		 */
		
		Double avgBatchWeek6Value = new Double(reportingService.getAvgBatchWeekValue(TEST_BATCH_ID,TEST_ASSESSMENT_WEEK));
		
		assertEquals(avgBatchWeek6Value, 84.93, FLOATING_NUMBER_VARIANCE);
		
	}
	
	@Test
	public void testGetTechnologiesForTheWeek() {
		/*
		 * Method description:
		 * input: batchId, and week
		 * output: List of technologies 
		 */
		
		Set<String> technologies = reportingService.getTechnologiesForTheWeek(TEST_BATCH_ID, TEST_ASSESSMENT_WEEK); 
		
		//One technologies in the set
		assertTrue(technologies.size() == 1); 
		
		//Set of technologies should contain Spring
		assertTrue(technologies.contains("Spring")); 
		
		//Set of technologies should not contain Java
		assertFalse(technologies.contains("Java"));  
		
	}
}
