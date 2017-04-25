package com.revature.caliber.controllers;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.services.ReportingService;

/**
 * Exclusively used to generate data for charts
 * 
 * @author Patrick Walsh
 *
 */
@RestController
public class ReportingController {

	private final static Logger log = Logger.getLogger(ReportingController.class);
	private ReportingService reportingService;

	@Autowired
	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}

	/*
	 *******************************************************
	 * Doughnut / Pie Charts
	 *******************************************************
	 */
	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{weekId}/pie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<QCStatus, Integer>> getBatchWeekPieChart(@PathVariable Integer batchId,
			@PathVariable Integer weekId) {
		log.info("getBatchWeekPieChart   ===>   /all/reports/batch/{batchId}/week/{weekId}/pie");
		return new ResponseEntity<Map<QCStatus, Integer>>(reportingService.getBatchWeekPieChart(batchId, weekId),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/week/pie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<QCStatus, Integer>> getPieChartCurrentWeekQCStatus(
			@PathVariable Integer batchId) {
		log.info("getPieChartCurrentWeekQCStatus ===> /all/reports/batch/{batchId}/week/pie");
		return new ResponseEntity<Map<QCStatus, Integer>>(
				reportingService.pieChartCurrentWeekQCStatus(batchId), HttpStatus.OK);
	}

	/*
	 *******************************************************
	 * Stacked Bar Charts
	 *******************************************************
	 */
	@RequestMapping(value = "/all/reports/batch/week/stacked-bar-current-week", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Map<QCStatus, Integer>>> getAllBatchesCurrentWeekQCStackedBarChart() {
		log.info("getAllBatchesCurrentWeekQCStats   ===>   /all/reports/batch/week/stacked-bar-current-week");
		return new ResponseEntity<Map<String, Map<QCStatus, Integer>>>(
				reportingService.getAllBatchesCurrentWeekQCStackedBarChart(), HttpStatus.OK);
	}

	/*
	 *******************************************************
	 * Bar Charts
	 *******************************************************
	 */
	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/bar-batch-week-avg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> getBatchWeekAvgBarChart(@PathVariable int batchId,
			@PathVariable int week) {
		log.info("getBatchWeekAvgBarChart   ===>   /all/reports/batch/{batchId}/week/{week}/bar-batch-week-avg");
		return new ResponseEntity<Map<String, Double[]>>(reportingService.getBatchWeekAvgBarChart(batchId, week),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/bar-batch-weekly-sorted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getBatchWeekSortedBarChart(@PathVariable int batchId,
			@PathVariable int week) {
		log.info("getBatchWeekAvgBarChart   ===>   /all/reports/batch/{batchId}/week/{week}/bar-batch-week-avg");
		return new ResponseEntity<Map<String, Double>>(reportingService.getBatchWeekSortedBarChart(batchId, week),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/trainee/{traineeId}/bar-batch-overall-trainee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> getBatchOverallTraineeBarChart(@PathVariable Integer batchId,
			@PathVariable Integer traineeId) {
		log.info(
				"getBatchOverallTraineeBarChart   ===>   /all/reports/batch/{batchId}/overall/trainee/{traineeId}/bar-batch-overall-trainee");
		return new ResponseEntity<Map<String, Double[]>>(
				reportingService.getBatchOverallTraineeBarChart(batchId, traineeId), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/bar-batch-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getBatchOverallBarChart(@PathVariable Integer batchId) {
		log.info("getBatchOverallBarChart   ===>   /all/reports/batch/{batchId}/overall/bar-batch-overall");
		return new ResponseEntity<Map<String, Double>>(reportingService.getBatchOverallBarChart(batchId),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{weekId}/trainee/{traineeId}/bar-batch-week-trainee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> getBatchWeekTraineeBarChart(@PathVariable Integer batchId,
			@PathVariable Integer weekId, @PathVariable Integer traineeId) {
		log.info(
				"getBatchWeekTraineeBarChart   ===>   /all/reports/batch/{batchId}/week/{weekId}/trainee/{traineeId}/bar-batch-week-trainee");
		return new ResponseEntity<Map<String, Double[]>>(
				reportingService.getBatchWeekTraineeBarChart(batchId, traineeId, weekId), HttpStatus.OK);
	}

	/*
	 *******************************************************
	 * Line Charts
	 *******************************************************
	 */
	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/trainee/{traineeId}/line-trainee-up-to-week", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double[]>> getTraineeUpToWeekLineChart(@PathVariable int batchId,
			@PathVariable int week, @PathVariable int traineeId) {
		log.info(
				"getTraineeUpToWeekLineChart   ===>   /all/reports/week/{week}/trainee/{traineeId}/line-trainee-up-to-week");
		return new ResponseEntity<Map<Integer, Double[]>>(
				reportingService.getTraineeUpToWeekLineChart(batchId, week, traineeId), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/trainee/{traineeId}/line-trainee-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double[]>> getTraineeOverallLineChart(@PathVariable Integer batchId,
			@PathVariable Integer traineeId) {
		log.info(
				"getTraineeOverallLineChart   ===>   /all/reports/batch/{batchId}/overall/trainee/{traineeId}/line-trainee-overall");
		return new ResponseEntity<Map<Integer, Double[]>>(
				reportingService.getTraineeOverallLineChart(batchId, traineeId), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/line-batch-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double>> getBatchOverallLineChart(@PathVariable int batchId) {
		log.info("getBatchOverallLineChart   ===>   /all/reports/batch/{batchId}/overall/line-batch-overall");
		return new ResponseEntity<Map<Integer, Double>>(reportingService.getBatchOverallLineChart(batchId),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/vp/reports/dashboard", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Map<Integer, Double>>> getCurrentBatchesLineChart() {
		log.info("getCurrentBatchesLineChart   ===>  /qc/reports/dashboard");
		return new ResponseEntity<Map<String, Map<Integer, Double>>>(reportingService.getAllCurrentBatchesLineChart(),
				HttpStatus.OK);
	}

	/*
	 *******************************************************
	 * Radar Charts
	 *******************************************************
	 */

	@RequestMapping(value = "/all/reports/week/{week}/trainee/{traineeId}/radar-trainee-up-to-week", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getTraineeUpToWeekRadarChart(@PathVariable Integer traineeId,
			@PathVariable Integer week) {
		log.info(
				"getTraineeUpToWeekRadarChart   ===>   /all/reports/week/{week}/trainee/{traineeId}/radar-trainee-up-to-week");
		return new ResponseEntity<Map<String, Double>>(reportingService.getTraineeUpToWeekRadarChart(traineeId, week),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/trainee/{traineeId}/radar-trainee-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getTraineeOverallRadarChart(@PathVariable Integer traineeId) {
		log.info("getTraineeOverallRadarChart   ===>   /all/reports/trainee/{traineeId}/radar-trainee-overall");
		return new ResponseEntity<Map<String, Double>>(reportingService.getTraineeOverallRadarChart(traineeId),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/radar-batch-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getBatchOverallRadarChart(@PathVariable Integer batchId) {
		log.info("getBatchOverallRadarChart   ===>   /all/reports/batch/{batchId}/overall/radar-batch-overall");
		return new ResponseEntity<Map<String, Double>>(reportingService.getBatchOverallRadarChart(batchId),
				HttpStatus.OK);
	}

	
	@RequestMapping(value = "/vp/reports/batch/{batchId}/radar-batch-all-trainees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Map<String, Double>>> getBatchAllTraineesRadarChart(@PathVariable Integer batchId) {
		log.info("getBatchOverallRadarChart   ===>   /all/reports/batch/{batchId}/overall/radar-batch-overall");
		return new ResponseEntity<Map<String, Map<String, Double>>>(reportingService.getBatchAllTraineesOverallRadarChart(batchId), HttpStatus.OK);
	}
	
	/*
	 *******************************************************
	 * Misc.
	 *******************************************************
	 */
	@RequestMapping(value = "/all/assessments/average/{batchId}/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> getBatchWeekAverageValue(@PathVariable Integer batchId, @PathVariable Integer week) {
		log.info("getBatchWeekAverageValue   ===>   /all/reports/batch/{batchId}/overall/line-batch-overall");
		return new ResponseEntity<Double>(reportingService.getAvgBatchWeekValue(batchId, week), HttpStatus.OK);
	}

}
