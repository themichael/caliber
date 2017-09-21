package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.services.ReportingService;

public class ReportingServiceTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(ReportingServiceTest.class);

	private static final int testBatchId = 2150;
	private static final int testBatchId2 = 2200;
	private static final int testAssessmentWeek = 6;
	private static final int testTraineeId = 5460;
	private static final double floatingNumberVariance = .01;

	@Autowired
	ReportingService reportingService;

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getAllCurrentBatchesLineChartConcurrent()
	 */
	@Test
	public void testGetAllCurrentBatchesLineChartConcurrent() {
		log.trace("Testing getAllCurrentBatchesLineChartConcurrent");

		final String key = "1702 Feb13 Java (AP)"; // name of batch
		
		// call service
		Map<String, Map<Integer, Double>> batches = reportingService.getAllCurrentBatchesLineChartConcurrent();

		log.debug(batches);

		// check that expected batch is there
		assertTrue(batches.containsKey(key));
		
		// check that batch has all expected week averages
		assertEquals(7, batches.get(key).size());
		
		// check that each week grade averages are what is expected
		assertEquals(68.34, batches.get(key).get(1), floatingNumberVariance);
		assertEquals(84.96, batches.get(key).get(2), floatingNumberVariance);
		assertEquals(76.83, batches.get(key).get(3), floatingNumberVariance);
		assertEquals(75.09, batches.get(key).get(4), floatingNumberVariance);
		assertEquals(77.94, batches.get(key).get(5), floatingNumberVariance);
		assertEquals(82.80, batches.get(key).get(6), floatingNumberVariance);
		assertEquals(74.27, batches.get(key).get(7), floatingNumberVariance);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeUpToWeekRadarChart(Integer,
	 *      Integer)
	 */
	@Test
	public void testGetTraineeUpToWeekRadarChart() {
		log.trace("Testing getTraineeUpToWeekRadarChart");

		// call service
		Map<String, Double> traineeSkills = reportingService.getTraineeUpToWeekRadarChart(testTraineeId, testAssessmentWeek);
		
		// check that trainee has all expected skills
		assertEquals(6, traineeSkills.size());
		
		// check that each expected skill is there and has expected average
		assertEquals(82.92, traineeSkills.get("Hibernate"), floatingNumberVariance);
		assertEquals(80.40, traineeSkills.get("JSP"), floatingNumberVariance);
		assertEquals(67.79, traineeSkills.get("Java"), floatingNumberVariance);
		assertEquals(93.10, traineeSkills.get("JavaScript"), floatingNumberVariance);
		assertEquals(91.55, traineeSkills.get("SQL"), floatingNumberVariance);
		assertEquals(79.20, traineeSkills.get("Spring"), floatingNumberVariance);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeOverallRadarChart(Integer)
	 */
	@Test
	public void testGetTraineeOverallRadarChart() {
		log.trace("Testing getTraineeOverallRadarChart");

		// call service
		Map<String, Double> traineeSkills = reportingService.getTraineeOverallRadarChart(testTraineeId);
		
		// check that trainee has all expected skills
		assertEquals(7, traineeSkills.size());
		
		// check that each expected skill is there and has expected average
		assertEquals(82.92, traineeSkills.get("Hibernate"), floatingNumberVariance);
		assertEquals(80.40, traineeSkills.get("JSP"), floatingNumberVariance);
		assertEquals(67.79, traineeSkills.get("Java"), floatingNumberVariance);
		assertEquals(93.10, traineeSkills.get("JavaScript"), floatingNumberVariance);
		assertEquals(91.55, traineeSkills.get("SQL"), floatingNumberVariance);
		assertEquals(79.20, traineeSkills.get("Spring"), floatingNumberVariance);
		assertEquals(83.60, traineeSkills.get("REST"), floatingNumberVariance);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeUpToWeekLineChart(int,
	 *      int, int)
	 */
	@Test
	public void getTraineeUpToWeekLinechart() {
		log.trace("getTraineeUpToWeekLinechart");
		
		/*
		 * Method description: input: batch, week, and trainee output: map of
		 * week and scores
		 */

		// get chart data which should be a map of week and scores
		Map<Integer, Double[]> averageGrades = reportingService.getTraineeUpToWeekLineChart(testBatchId, testAssessmentWeek, testTraineeId);

		// size should be 6 for week 6
		assertEquals(testAssessmentWeek, averageGrades.size());

		// equal to data from database
		assertEquals(72.74, averageGrades.get(1)[0], floatingNumberVariance);
		assertEquals(75.04, averageGrades.get(6)[0], floatingNumberVariance);

		// week 7 should not exist
		assertNull(averageGrades.get(7));
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTraineeOverallLineChart(int,
	 *      int)
	 */
	@Test
	public void getTraineeOverallLineChart() {
		log.trace("getTraineeOverallLineChart");
		
		/*
		 * Method description: input: batch, and trainee output: map of week and
		 * double array for average with set size of 2 [0: trainee, 1: batch]
		 */

		Map<Integer, Double[]> overallGrades = reportingService.getTraineeOverallLineChart(testBatchId, testTraineeId);

		// batch had 7 weeks total
		assertEquals(7, overallGrades.size());

		// scores should only have 2 values
		Double[] weekAverage = overallGrades.get(7);
		assertEquals(2, weekAverage.length);

		// week 8 should not exist
		assertNull(overallGrades.get(8));
	}

	/**
	 * Tests methods:getBatchOverallRadarChart testing batch 2150 and 2200 spot
	 * testing average of category for batch
	 * 
	 * @see com.revature.caliber.services.ReportingService#getBatchOverallRadarChart(List<Grade>
	 *      grades)
	 */
	@Test
	public void getBatchOverallRadarChart() {
		log.trace("getBatchOverallRadarChart");
		
		Map<String, Double> skills = reportingService.getBatchOverallRadarChart(testBatchId);
		assertEquals(7, skills.size());
		assertEquals(76.70, skills.get("Java"), floatingNumberVariance);
		assertEquals(89.74, skills.get("Hibernate"), floatingNumberVariance);

		skills = reportingService.getBatchOverallRadarChart(testBatchId2);
		assertEquals(10, skills.size());
		assertEquals(77.88, skills.get("JDBC"), floatingNumberVariance);
		assertEquals(89.52, skills.get("Spring"), floatingNumberVariance);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getBatchOverallLineChart(int)
	 */
	@Test
	public void testGetBatchOverallLineChart() {
		log.trace("testGetBatchOverallLineChart");
		
		/*
		 * Method description: input: batchId output: map of week and scores
		 */

		Map<Integer, Double> map = reportingService.getBatchOverallLineChart(testBatchId);

		// batch had 7 weeks total
		assertEquals(7, map.size());

		// grades are equal
		assertEquals(map.get(1), 80.26, floatingNumberVariance);
		assertEquals(map.get(2), 92.69, floatingNumberVariance);
		assertEquals(map.get(3), 86.66, floatingNumberVariance);
		assertEquals(map.get(4), 84.79, floatingNumberVariance);
		assertEquals(map.get(5), 87.84, floatingNumberVariance);
		assertEquals(map.get(6), 84.93, floatingNumberVariance);
		assertEquals(map.get(7), 83.27, floatingNumberVariance);

		// week 8 should not exist
		assertNull(map.get(8));
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getAllCurrentBatchesLineChart()
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testGetAllCurrentBatchesLineChart() {
		log.trace("testGetAllCurrentBatchesLineChart");
		
		/*
		 * Method description: output: map of current batch and its respective
		 * data of (address, label of start date & trainer, and list of grades)
		 */
		List<Object> results = reportingService.getAllCurrentBatchesLineChart();

		for (Object num : results) {
			Map<String, List<String>> data = (Map<String, List<String>>) num;

			// current batch keys should have address, label and grades
			assertTrue(data.containsKey("address"));
			assertTrue(data.containsKey("label"));
			assertTrue(data.containsKey("grades"));

		}
		// current batch data should have 3
		assertTrue(results.size() == 3);
	}

	/**
	 * Tests getBatchAllTraineesOverallRadarChart testing batch 2150 and 2200
	 * spot testing average students' category
	 * 
	 * @see com.revature.caliber.services.ReportingService#getBatchAllTraineesOverallRadarChart
	 */
	@Test
	public void getBatchAllTraineesOverallRadarChart() {
		log.trace("getBatchAllTraineesOverallRadarChart");
		
		Map<String, Map<String, Double>> skills = reportingService.getBatchAllTraineesOverallRadarChart(testBatchId);
		
		assertEquals(13, skills.size());
		assertEquals(91.55, skills.get("Erwin, Eric").get("SQL"), floatingNumberVariance);
		assertEquals(84.16, skills.get("Michels, Alex").get("Hibernate"), floatingNumberVariance);

		skills = reportingService.getBatchAllTraineesOverallRadarChart(testBatchId2);
		assertEquals(15, skills.size());
		assertEquals(84.95, skills.get("Lau, Samuel").get("SOAP"), floatingNumberVariance);
		assertEquals(78.17, skills.get("Sibrian, David").get("REST"), floatingNumberVariance);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getAvgBatchWeekValue(Integer,
	 *      Integer)
	 */
	@Test
	public void testGetAvgBatchWeekValue() {
		log.trace("testGetAvgBatchWeekValue");
		
		/*
		 * Method description: input: batchId, and week output: average batch
		 * week 6 value
		 */

		Double avgBatchWeek6Value = new Double(reportingService.getAvgBatchWeekValue(testBatchId, testAssessmentWeek));

		assertEquals(avgBatchWeek6Value, 84.93, floatingNumberVariance);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.services.ReportingService#getTechnologiesForTheWeek(Integer,
	 *      Integer)
	 */
	@Test
	public void testGetTechnologiesForTheWeek() {
		log.trace("testGetTechnologiesForTheWeek");
		
		/*
		 * Method description: input: batchId, and week output: List of
		 * technologies
		 */

		Set<String> technologies = reportingService.getTechnologiesForTheWeek(testBatchId, testAssessmentWeek);

		// One technologies in the set
		assertTrue(technologies.size() == 1);

		// Set of technologies should contain Spring
		assertTrue(technologies.contains("Spring"));

		// Set of technologies should not contain Java
		assertFalse(technologies.contains("Java"));
	}
}
