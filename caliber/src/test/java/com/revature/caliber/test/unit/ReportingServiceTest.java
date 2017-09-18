package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.services.ReportingService;

public class ReportingServiceTest extends CaliberTest{

	private static final int TEST_BATCH_ID = 2150;
	private static final int TEST_ASSESSMENT_WEEK = 7;
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
	 * @see com.revature.caliber.services.ReportingService#getTraineeUpToWeekLineChart(int, int, int)
	 */
	@Test
	public void getTraineeUpToWeekLinechart(){
		/*
		 * input: batch, week, and trainee
		 * output: map of week and scores 
		 */
		
		//get chart data which should be a map of week and scores
		Map<Integer, Double[]> averageGrades =
				reportingService.getTraineeUpToWeekLineChart(
						TEST_BATCH_ID,
						TEST_ASSESSMENT_WEEK,
						TEST_TRAINEE_ID);
		
		//size should be 7 for week 7
		assertEquals(7, averageGrades.size());
		
		//equal to data from database
		assertEquals(72.74, averageGrades.get(1)[0], FLOATING_NUMBER_VARIANCE);
		
		assertEquals(83.32, averageGrades.get(7)[0], FLOATING_NUMBER_VARIANCE);
		
		//week 8 should not exist
		assertNull(averageGrades.get(8));
	}
}
