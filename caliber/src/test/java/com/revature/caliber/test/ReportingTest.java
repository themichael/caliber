package com.revature.caliber.test;

import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.controllers.ReportingController;

public class ReportingTest extends CaliberTest {

	private static final Logger log = Logger.getLogger(ReportingTest.class);

	@Autowired
	private ReportingController reportingController;

	//////////////////////////////////////////////////////////////////////////////////////////////////
	// REPORTING API
	//////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchComparisonAvg(String,
	 *      String, Date)
	 */
	public Double getBatchComparisonAvg(String skill, String training, Date startDate) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchWeekPieChart(Integer,
	 *      Integer)
	 */
	public Map<QCStatus, Integer> getBatchWeekPieChart(Integer batchId, Integer weekId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getPieChartCurrentWeekQCStatus(Integer)
	 */
	public Map<QCStatus, Integer> getPieChartCurrentWeekQCStatus(Integer batchId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getAllBatchesCurrentWeekQCStackedBarChart()
	 */
	public Map<String, Map<QCStatus, Integer>> getAllBatchesCurrentWeekQCStackedBarChart() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchWeekAvgBarChart(int,
	 *      int)
	 */
	public Map<String, Double[]> getBatchWeekAvgBarChart(int batchId, int week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchWeekSortedBarChart(int,
	 *      int)
	 */
	public Map<String, Double> getBatchWeekSortedBarChart(int batchId, int week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchOverallTraineeBarChart(Integer,
	 *      Integer)
	 */
	public Map<String, Double[]> getBatchOverallTraineeBarChart(Integer batchId, Integer traineeId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchOverallBarChart(Integer)
	 */
	public Map<String, Double> getBatchOverallBarChart(Integer batchId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchWeekTraineeBarChart(Integer,
	 *      Integer, Integer)
	 */
	public Map<String, Double[]> getBatchWeekTraineeBarChart(Integer batchId, Integer weekId, Integer traineeId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getTraineeUpToWeekLineChart(int,
	 *      int, int)
	 */
	public Map<Integer, Double[]> getTraineeUpToWeekLineChart(int batchId, int week, int traineeId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getTraineeOverallLineChart(Integer,
	 *      Integer)
	 */
	public Map<Integer, Double[]> getTraineeOverallLineChart(Integer batchId, Integer traineeId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchOverallLineChart(int)
	 */
	public Map<Integer, Double> getBatchOverallLineChart(int batchId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getCurrentBatchesLineChart()
	 */
	public Map<String, Map<Integer, Double>> getCurrentBatchesLineChart() {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getTraineeUpToWeekRadarChart(Integer,
	 *      Integer)
	 */
	public Map<String, Double> getTraineeUpToWeekRadarChart(Integer traineeId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getTraineeOverallRadarChart(Integer)
	 */
	public Map<String, Double> getTraineeOverallRadarChart(Integer traineeId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchOverallRadarChart(Integer)
	 */
	public Map<String, Double> getBatchOverallRadarChart(Integer batchId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchAllTraineesRadarChart(Integer)
	 */
	public Map<String, Map<String, Double>> getBatchAllTraineesRadarChart(Integer batchId) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchWeekAverageValue(Integer,
	 *      Integer)
	 */
	public Double getBatchWeekAverageValue(Integer batchId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getTechnologiesForTheWeek(Integer,
	 *      Integer)
	 */
	public Set<String> getTechnologiesForTheWeek(Integer batchId, Integer week) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
