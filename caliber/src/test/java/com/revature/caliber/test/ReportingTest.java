package com.revature.caliber.test;

//commented out these packages because their variables are not currently in use
import java.util.Date;
import java.util.Map;
import java.util.Set;

//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.QCStatus;
//import com.revature.caliber.controllers.ReportingController;

public class ReportingTest extends CaliberTest {

//	commented out unused variable
//	private static final Logger log = Logger.getLogger(ReportingTest.class);

//	@Autowired
//	private ReportingController reportingController;

	private static final String notYetImplmented="Not yet implemented";
	//////////////////////////////////////////////////////////////////////////////////////////////////
	// REPORTING API
	//////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchComparisonAvg(String,
	 *      String, Date)
	 */
	public Double getBatchComparisonAvg() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchWeekPieChart(Integer,
	 *      Integer)
	 */
	public Map<QCStatus, Integer> getBatchWeekPieChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getPieChartCurrentWeekQCStatus(Integer)
	 */
	public Map<QCStatus, Integer> getPieChartCurrentWeekQCStatus() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getAllBatchesCurrentWeekQCStackedBarChart()
	 */
	public Map<String, Map<QCStatus, Integer>> getAllBatchesCurrentWeekQCStackedBarChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchWeekAvgBarChart(int,
	 *      int)
	 */
	public Map<String, Double[]> getBatchWeekAvgBarChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchWeekSortedBarChart(int,
	 *      int)
	 */
	public Map<String, Double> getBatchWeekSortedBarChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchOverallTraineeBarChart(Integer,
	 *      Integer)
	 */
	public Map<String, Double[]> getBatchOverallTraineeBarChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchOverallBarChart(Integer)
	 */
	public Map<String, Double> getBatchOverallBarChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchWeekTraineeBarChart(Integer,
	 *      Integer, Integer)
	 */
	public Map<String, Double[]> getBatchWeekTraineeBarChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getTraineeUpToWeekLineChart(int,
	 *      int, int)
	 */
	public Map<Integer, Double[]> getTraineeUpToWeekLineChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getTraineeOverallLineChart(Integer,
	 *      Integer)
	 */
	public Map<Integer, Double[]> getTraineeOverallLineChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchOverallLineChart(int)
	 */
	public Map<Integer, Double> getBatchOverallLineChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getCurrentBatchesLineChart()
	 */
	public Map<String, Map<Integer, Double>> getCurrentBatchesLineChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getTraineeUpToWeekRadarChart(Integer,
	 *      Integer)
	 */
	public Map<String, Double> getTraineeUpToWeekRadarChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getTraineeOverallRadarChart(Integer)
	 */
	public Map<String, Double> getTraineeOverallRadarChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchOverallRadarChart(Integer)
	 */
	public Map<String, Double> getBatchOverallRadarChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchAllTraineesRadarChart(Integer)
	 */
	public Map<String, Map<String, Double>> getBatchAllTraineesRadarChart() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getBatchWeekAverageValue(Integer,
	 *      Integer)
	 */
	public Double getBatchWeekAverageValue() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

	/**
	 * Tests methods:
	 * 
	 * @see com.revature.caliber.controllers.ReportingController#getTechnologiesForTheWeek(Integer,
	 *      Integer)
	 */
	public Set<String> getTechnologiesForTheWeek() {
		throw new UnsupportedOperationException(notYetImplmented);
	}

}
