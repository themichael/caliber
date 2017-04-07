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
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.services.ReportingService;

/**
 * Exclusively used to generate data for charts
 * 
 * @author Patrick Walsh
 *
 */
@RestController
public class ReportingController {

	@SuppressWarnings("unused")
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
	public ResponseEntity<Map<QCStatus, Integer>> getBatchWeekPieChart(@PathVariable Integer batchId, @PathVariable Integer weekId) {
		return new ResponseEntity<Map<QCStatus, Integer>>(reportingService.getBatchWeekPieChart(batchId, weekId), HttpStatus.OK);
	}

	/*
	 *******************************************************
	 * Bar Charts
	 *******************************************************
	 */
	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/bar-batch-week-avg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> getBatchWeekAvgBarChart(@PathVariable int batchId, @PathVariable int week) {
		return new ResponseEntity<Map<String, Double[]>>(reportingService.getBatchWeekAvgBarChart(batchId, week), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/bar-batch-weekly-sorted", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getBatchWeekSortedBarChart(@PathVariable int batchId, @PathVariable int week) {
		Map<String, Double> result = reportingService.getBatchWeekSortedBarChart(batchId, week);
		return new ResponseEntity<Map<String, Double>>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/trainee/{traineeId}/bar-batch-overall-trainee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> getBatchOverallTraineeBarChart(@PathVariable Integer batchId, @PathVariable Integer traineeId) {
		return new ResponseEntity<Map<String, Double[]>>(reportingService.getBatchOverallTraineeBarChart(batchId, traineeId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/bar-batch-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Trainee, Double>> getBatchOverallBarChart(@PathVariable Integer batchId) {
		return new ResponseEntity<Map<Trainee, Double>>(reportingService.getBatchOverallBarChart(batchId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{weekId}/trainee/{traineeId}/bar-batch-week-trainee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> getBatchWeekTraineeBarChart(@PathVariable Integer batchId, @PathVariable Integer weekId, @PathVariable Integer traineeId) {
		return new ResponseEntity<Map<String, Double[]>>(reportingService.getBatchWeekTraineeBarChart(batchId, traineeId,weekId), HttpStatus.OK);
	}
	
	/*
	 *******************************************************
	 * Line Charts
	 *******************************************************
	 */
	@RequestMapping(value = "/all/reports/week/{week}/trainee/{traineeId}/line-trainee-up-to-week", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double>> getTraineeUpToWeekLineChart(@PathVariable int week, @PathVariable int traineeId) {
		return new ResponseEntity<Map<Integer, Double>>(reportingService.getTraineeUpToWeekLineChart(week, traineeId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/trainee/{traineeId}/line-trainee-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double[]>> getTraineeOverallLineChart(@PathVariable Integer batchId, @PathVariable Integer traineeId) {
		return new ResponseEntity<Map<Integer, Double[]>>(reportingService.getTraineeOverallLineChart(batchId, traineeId), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/line-batch-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double>> getBatchOverallLineChart(@PathVariable int batchId){
		return new ResponseEntity<Map<Integer, Double>>(reportingService.getBatchOverallLineChart(batchId), HttpStatus.OK);
	}
	
	/*
	 *******************************************************
	 * Radar Charts
	 *******************************************************
	 */

	@RequestMapping(value = "/all/reports/week/{week}/trainee/{traineeId}/radar-trainee-up-to-week", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getTraineeUpToWeekRadarChart(@PathVariable Integer traineeId, @PathVariable Integer week) {
		return new ResponseEntity<Map<String, Double>>(reportingService.getTraineeUpToWeekRadarChart(traineeId, week), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/trainee/{traineeId}/radar-trainee-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getTraineeOverallRadarChart(@PathVariable Integer traineeId) {
		return new ResponseEntity<Map<String, Double>>(reportingService.getTraineeOverallRadarChart(traineeId), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/radar-batch-overall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getBatchOverallRadarChart(@PathVariable Integer batchId) {
		return new ResponseEntity<Map<String, Double>>(reportingService.getBatchOverallRadarChart(batchId), HttpStatus.OK);
	}
}
