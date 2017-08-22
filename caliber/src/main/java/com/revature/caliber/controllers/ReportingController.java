package com.revature.caliber.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.services.ReportingService;

/**
 * Exclusively used to generate data for charts
 * 
 * @author Patrick Walsh
 * 
 *         Team !Uncharted
 * @author Pier Yos
 * @author Hossain Yahya
 * @author Yanilda Peralta
 * @author Igor Gluskin
 * @author Ateeb Khawaja
 * 
 *
 */
@RestController
public class ReportingController {

	private static final Logger log = Logger.getLogger(ReportingController.class);
	private ReportingService reportingService;

	@Autowired
	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}

	/**************************************************************************
	 * Batch Average Comparison
	 *************************************************************************
	 */

	@RequestMapping(value = "/all/reports/compare/skill/{skill}/training/{training}/date/{startDate}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> getBatchComparisonAvg(@PathVariable String skill, @PathVariable String training,
			@PathVariable Date startDate) {
		log.info("YAYAYAYAYAYAYYAYAYAYAYAYAYAYAYAYATEZXRDCYTFUVGBJHLNKJSFSD " + startDate + skill + training);
		log.info(" getBatchComparisonAvg ===> " + reportingService.getBatchComparisonAvg(skill, training, startDate));
		return new ResponseEntity<>(reportingService.getBatchComparisonAvg(skill, training, startDate), HttpStatus.OK);
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
		return new ResponseEntity<>(reportingService.getBatchWeekPieChart(batchId, weekId), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/pie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<QCStatus, Integer>> getPieChartCurrentWeekQCStatus(@PathVariable Integer batchId) {
		log.info("getPieChartCurrentWeekQCStatus ===> /all/reports/batch/{batchId}/week/pie");
		try {
			Map<QCStatus, Integer> results = reportingService.pieChartCurrentWeekQCStatus(batchId);
			return new ResponseEntity<>(results, HttpStatus.OK);
		} catch (Exception e) {
			log.debug(e);
			return new ResponseEntity<>(new HashMap<>(), HttpStatus.NO_CONTENT);
		}

	}

	/*
	 *******************************************************
	 * Stacked Bar Charts
	 *******************************************************
	 */
	@RequestMapping(value = "/all/reports/batch/week/stacked-bar-current-week", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> getAllBatchesCurrentWeekQCStackedBarChart() {
		log.info("getAllBatchesCurrentWeekQCStats   ===>   /all/reports/batch/week/stacked-bar-current-week");
		return new ResponseEntity<>(reportingService.getAllBatchesCurrentWeekQCStackedBarChart(), HttpStatus.OK);
	}

	/*
	 *******************************************************
	 * Bar Charts
	 *******************************************************
	 */
	
	//Mapping QC Overall status to getBatchWeekQcOverallBarChart function
	@RequestMapping(value = "/all/reports/batch/week/overallqcstatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Note> getBatchWeekQcOverallBarChart(@PathVariable int batchId,
			@PathVariable int week) {
		log.info("getAllBatchesCurrentWeekQCStats   ===>   /all/reports/batch/week/stacked-bar-current-week");
		return new ResponseEntity<>(reportingService.getBatchWeekQcOverallBarChart(batchId, week), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/bar-batch-week-avg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> getBatchWeekAvgBarChart(@PathVariable int batchId,
			@PathVariable int week) {
		log.info("getBatchWeekAvgBarChart   ===>   /all/reports/batch/{batchId}/week/{week}/bar-batch-week-avg");
		return new ResponseEntity<>(reportingService.getBatchWeekAvgBarChart(batchId, week), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/bar-batch-weekly-sorted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getBatchWeekSortedBarChart(@PathVariable int batchId,
			@PathVariable int week) {
		log.info("getBatchWeekAvgBarChart   ===>   /all/reports/batch/{batchId}/week/{week}/bar-batch-week-avg");
		return new ResponseEntity<>(reportingService.getBatchWeekSortedBarChart(batchId, week), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/trainee/{traineeId}/bar-batch-overall-trainee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> getBatchOverallTraineeBarChart(@PathVariable Integer batchId,
			@PathVariable Integer traineeId) {
		log.info(
				"getBatchOverallTraineeBarChart   ===>   /all/reports/batch/{batchId}/overall/trainee/{traineeId}/bar-batch-overall-trainee");
		return new ResponseEntity<>(reportingService.getBatchOverallTraineeBarChart(batchId, traineeId), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/bar-batch-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getBatchOverallBarChart(@PathVariable Integer batchId) {
		log.info("getBatchOverallBarChart   ===>   /all/reports/batch/{batchId}/overall/bar-batch-overall");
		return new ResponseEntity<>(reportingService.getBatchOverallBarChart(batchId), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{weekId}/trainee/{traineeId}/bar-batch-week-trainee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> getBatchWeekTraineeBarChart(@PathVariable Integer batchId,
			@PathVariable Integer weekId, @PathVariable Integer traineeId) {
		log.info(
				"getBatchWeekTraineeBarChart   ===>   /all/reports/batch/{batchId}/week/{weekId}/trainee/{traineeId}/bar-batch-week-trainee");
		return new ResponseEntity<>(reportingService.getBatchWeekTraineeBarChart(batchId, traineeId, weekId),
				HttpStatus.OK);
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
		return new ResponseEntity<>(reportingService.getTraineeUpToWeekLineChart(batchId, week, traineeId),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/trainee/{traineeId}/line-trainee-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double[]>> getTraineeOverallLineChart(@PathVariable Integer batchId,
			@PathVariable Integer traineeId) {
		log.info(
				"getTraineeOverallLineChart   ===>   /all/reports/batch/{batchId}/overall/trainee/{traineeId}/line-trainee-overall");
		return new ResponseEntity<>(reportingService.getTraineeOverallLineChart(batchId, traineeId), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/line-batch-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double>> getBatchOverallLineChart(@PathVariable int batchId) {
		log.info("getBatchOverallLineChart   ===>   /all/reports/batch/{batchId}/overall/line-batch-overall");
		return new ResponseEntity<>(reportingService.getBatchOverallLineChart(batchId), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/dashboard", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Map<Integer, Double>>> getCurrentBatchesLineChart() {
		log.info("getCurrentBatchesLineChart   ===>  /all/reports/dashboard");
		return new ResponseEntity<>(reportingService.getAllCurrentBatchesLineChart(), HttpStatus.OK);
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
		return new ResponseEntity<>(reportingService.getTraineeUpToWeekRadarChart(traineeId, week), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/trainee/{traineeId}/radar-trainee-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getTraineeOverallRadarChart(@PathVariable Integer traineeId) {
		log.info("getTraineeOverallRadarChart   ===>   /all/reports/trainee/{traineeId}/radar-trainee-overall");
		return new ResponseEntity<>(reportingService.getTraineeOverallRadarChart(traineeId), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/radar-batch-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getBatchOverallRadarChart(@PathVariable Integer batchId) {
		log.info("getBatchOverallRadarChart   ===>   /all/reports/batch/{batchId}/overall/radar-batch-overall");
		return new ResponseEntity<>(reportingService.getBatchOverallRadarChart(batchId), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/radar-batch-all-trainees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Map<String, Double>>> getBatchAllTraineesRadarChart(
			@PathVariable Integer batchId) {
		log.info("getBatchOverallRadarChart   ===>   /all/reports/batch/{batchId}/overall/radar-batch-overall");
		return new ResponseEntity<>(reportingService.getBatchAllTraineesOverallRadarChart(batchId), HttpStatus.OK);
	}

	/*
	 *******************************************************
	 * Misc.
	 *******************************************************
	 */
	@RequestMapping(value = "/all/assessments/average/{batchId}/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> getBatchWeekAverageValue(@PathVariable Integer batchId, @PathVariable Integer week) {
		log.info("getBatchWeekAverageValue   ===>   /all/reports/batch/{batchId}/overall/line-batch-overall");
		return new ResponseEntity<>(reportingService.getAvgBatchWeekValue(batchId, week), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/assessments/categories/batch/{batchId}/week/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<String>> getTechnologiesForTheWeek(@PathVariable Integer batchId,
			@PathVariable Integer week) {
		log.info("getBatchWeekAverageValue   ===>   /all/reports/batch/{batchId}/overall/line-batch-overall");
		return new ResponseEntity<>(reportingService.getTechnologiesForTheWeek(batchId, week), HttpStatus.OK);
	}
}
