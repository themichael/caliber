package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.junit.Ignore;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Batch;
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
		
		assertTrue(batches.containsKey(key));
		assertTrue(batches.get(key).containsValue(68.34475));
		assertTrue(batches.get(key).containsValue(84.9646875));
		assertTrue(batches.get(key).containsValue(76.82675000000002));
		assertTrue(batches.get(key).containsValue(75.09325));
		assertTrue(batches.get(key).containsValue(77.94375000000001));
		assertTrue(batches.get(key).containsValue(82.80416666666666));
		assertTrue(batches.get(key).containsValue(74.265));
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeUpToWeekRadarChart(Integer, Integer)
	 */
	@Test
	public void testGetTraineeUpToWeekRadarChart() {
		log.info("Testing getTraineeUpToWeekRadarChart");
		
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeOverallRadarChart(Integer)
	 */
	@Test
	public void testGetTraineeOverallRadarChart() {
		log.info("Testing getTraineeOverallRadarChart");
		
		throw new UnsupportedOperationException("Not yet implemented");
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
	@Ignore
	public void getBatchOverallLineChart() {
		
		/*
		 * Method description:
		 * input: batchid
		 * output: map of week and scores 
		 */
		
		final double actualWeek1Score = 80.25723076923077; 
		
		Map<Integer,Double> map = reportingService.getBatchOverallLineChart(TEST_BATCH_ID); 
		
		//batch had 7 weeks total 
		assertEquals(7,map.size()); 
		
		//scores of week 1 should be 80.25723076923077
		
		double score = map.get(1);
		assertEquals(score, actualWeek1Score, .00000001);

		//week 8 should not exist 
		assertNull(map.get(8));

	}
	
	@Test
	public void getAllCurrentBatchesLineChart() {
		/*
		 * Method description:
		 * output: map of week and scores 
		 */
		
		List<Object> results =  reportingService.getAllCurrentBatchesLineChart(); 
		
		System.out.println(results);
		
		System.out.println(results.size());
		
		System.out.println("Is empty: " + results.isEmpty());
		
		
	}
}
