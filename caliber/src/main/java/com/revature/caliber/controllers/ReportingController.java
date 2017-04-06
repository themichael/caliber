package com.revature.caliber.controllers;

import java.util.HashMap;
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

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Category;
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

	private final static Logger log = Logger.getLogger(ReportingController.class);
	private ReportingService reportingService;

	@Autowired
	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}

	/*
	 *******************************************************
	 * TODO ORGANIZE SERVICES
	 *
	 *******************************************************
	 */
	/**
	 * For Displaying Bar Chart of all trainees (One Batch) and Weekly Avg score
	 * 
	 * @Author Pier Yos
	 * @param batchId
	 * @param week
	 * @return JSON result of Map<Trainee's name, Double Avg Score>
	 */
	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/bar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getBarChartBatchWeeklyAvg(@PathVariable int batchId,
			@PathVariable int week) {
		Map<String, Double> result = reportingService.getBarChartBatchWeeklyAvg(batchId, week);
		return new ResponseEntity<Map<String, Double>>(result, HttpStatus.OK);
	}

	/**
	 * For Displaying Line Chart of all trainees (One Batch) and Avg score
	 * 
	 * @Author Pier Yos
	 * @param batchId
	 * @return JSON result of Map<Week#, Double Avg Score>
	 */
	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/line", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double>> getLineChartBatchOverallAvg(@PathVariable int batchId){
		Map<Integer, Double> result = reportingService.getLineChartBatchOverallAvg(batchId);
		return new ResponseEntity<Map<Integer, Double>>(result, HttpStatus.OK);
	}

	/**
	 * Get aggregated grades by Category for a Trainee
	 *
	 * @param traineeId
	 * @return
	 */
	@RequestMapping(value = "/agg/tech/trainee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<Trainee, Double[]>> aggregateTechTrainee(@PathVariable("id") int traineeId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Get aggregated grades by Week for a Trainee
	 *
	 * @param traineeId
	 * @return
	 */
	@RequestMapping(value = "/agg/week/trainee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<Trainee, Double[]>> aggregateWeekTrainee(@PathVariable("id") int traineeId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Get aggregated grades by Category for a Batch
	 *
	 * @param batchId
	 * @return
	 */
	@RequestMapping(value = "/agg/tech/batch/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<Batch, Double[]>> aggregateTechBatch(@PathVariable("id") int batchId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Get aggregated grades by Category for a Batch
	 *
	 * @param traineeId
	 * @return
	 */
	@RequestMapping(value = "/agg/week/batch/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<Batch, Double[]>> aggregateWeekBatch(@PathVariable("id") int batchId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Get aggregated grades for all Trainees by Trainer
	 *
	 * @param traineeId
	 * @return
	 */
	@RequestMapping(value = "/agg/batch/trainer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> aggregateTraineesTrainer(@PathVariable("id") int trainerId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public ResponseEntity<Map<Trainee, Double>> findAvgGradesForEachTrainee() {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public ResponseEntity<Map<Assessment, Double>> findAvgGradesForEachAssessment() {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public ResponseEntity<Map<Category, Double>> findAvgGradeByCategory(int traineeId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Yanilda
	 * 
	 * @param batchId
	 * @param week
	 * @param traineeId
	 * @return
	 */

	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/trainee/{traineeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double>> lineCharAVG(@PathVariable int batchId, @PathVariable int week,
			@PathVariable int traineeId) {
		return new ResponseEntity<Map<Integer, Double>>(reportingService.lineChartAvg(week, traineeId), HttpStatus.OK);
	}

	/**
	 * Yanilda
	 * 
	 * @param batchId
	 * @param week
	 * @return
	 */
	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/barAssesment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> barChartPerAssessments(@PathVariable int batchId,
			@PathVariable int week) {
		return new ResponseEntity<Map<String, Double[]>>(reportingService.barChartPerAssessments(batchId, week),
				HttpStatus.OK);
	}

	/**
	 * 
	 * @param traineeId
	 * @return
	 */
	@RequestMapping(value = "/all/reports/batch/trainee/{traineeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double>> findAvgGradeByWeek(@PathVariable int traineeId) {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@RequestMapping(value = "/agg/tech/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<Category, HashMap<Batch, Double[]>>> aggregateTechForAllBatches() {
		// TODO implement me
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{weekId}/pie", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<QCStatus, Integer>> aggregateQCPieChart(@PathVariable Integer batchId,
			@PathVariable Integer weekId) {

		HashMap<QCStatus, Integer> results = (HashMap<QCStatus, Integer>) reportingService.batchWeekPieChart(batchId,
				weekId);

		return new ResponseEntity<HashMap<QCStatus, Integer>>(results, HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{weekId}/trainee/{traineeId}/bar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Double[]>> aggregateQCPieChart(@PathVariable Integer batchId,
			@PathVariable Integer weekId, @PathVariable Integer traineeId) {

		HashMap<String, Double[]> results = (HashMap<String, Double[]>) reportingService.getBarChartBatchWeekTrainee(batchId, traineeId,weekId);

		return new ResponseEntity<HashMap<String, Double[]>>(results, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/reports/batch/{batchId}/overall/trainee/{traineeId}/bar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Double[]>> getBarChartBatchOverallTrainee(@PathVariable Integer batchId, @PathVariable Integer traineeId) {

		HashMap<String, Double[]> results = (HashMap<String, Double[]>) reportingService.getBarChartBatchOverallTrainee(batchId, traineeId);

		return new ResponseEntity<HashMap<String, Double[]>>(results, HttpStatus.OK);
	}

	// assessmentType is case sensitive so call with uppercase first letter
	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/assessment/{assessmentType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Trainee, Double[]>> getAvgBatchWeek(@PathVariable Integer batchId,
			@PathVariable Integer week, @PathVariable AssessmentType assessmentType) {
		return new ResponseEntity<Map<Trainee, Double[]>>(
				reportingService.getAvgBatchWeek(batchId, week, assessmentType), HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/week/{week}/trainee/{traineeId}/radar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getRadarChartForTraineeWeek(@PathVariable Integer traineeId,
			@PathVariable Integer week) {
		return new ResponseEntity<Map<String, Double>>(reportingService.getRadarChartForTraineeWeek(traineeId, week),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/trainee/{traineeId}/radar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getRadarChartForTraineeOverall(@PathVariable Integer traineeId) {
		return new ResponseEntity<Map<String, Double>>(reportingService.getRadarChartForTraineeOverall(traineeId),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/week/{week}/radar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getRadarChartForBatchWeek(@PathVariable Integer batchId,
			@PathVariable Integer week) {
		return new ResponseEntity<Map<String, Double>>(reportingService.getRadarChartForBatchWeek(batchId, week),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/all/reports/batch/{batchId}/radar", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double>> getRadarChartForBatchOverall(@PathVariable Integer batchId) {
		return new ResponseEntity<Map<String, Double>>(reportingService.getRadarChartForBatchOverall(batchId),
				HttpStatus.OK);
	}

	/**
	 * @author Hossain
	 */
	@RequestMapping(value = "/all/reports/batch/{batchId}/trainee/{traineeId}/line", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Integer, Double[]>> getLineChartTraineeoverAll(@PathVariable Integer batchId,
			@PathVariable Integer traineeId) {
		return new ResponseEntity<Map<Integer, Double[]>>(
				reportingService.getLineChartTraineeOverall(batchId, traineeId), HttpStatus.OK);
	}

	/**
	 * @author Hossain
	 */
	@RequestMapping(value = "/all/reports/batch/overall/{batchId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Trainee, Double>> getBarChartOverAll(@PathVariable Integer batchId) {
		return new ResponseEntity<Map<Trainee, Double>>(reportingService.getBarChartOverAll(batchId), HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/test/{batchId}/{week}/{traineeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<Trainee, Double>> test(@PathVariable Integer batchId, @PathVariable Integer week, @PathVariable Integer traineeId) {
		Double[] avgTraineeWeek = reportingService.getAvgTraineeWeek(traineeId, week, AssessmentType.Exam);
		ResponseEntity<Double[]> avgTraineeOverallResponse = new ResponseEntity<>(avgTraineeWeek, HttpStatus.OK);
		
		Map<Integer, Double[]> avgTraineeOverall = reportingService.getAvgTraineeOverall(traineeId, AssessmentType.Exam);
		ResponseEntity<Map<Integer, Double[]>> avgTraineeOverallReponse = new ResponseEntity<>(avgTraineeOverall, HttpStatus.OK);
		
		Map<Trainee, Double[]> avgBatchWeek = reportingService.getAvgBatchWeek(batchId, week, AssessmentType.Exam);
		ResponseEntity<Map<Trainee, Double[]>> avgBatchWeekReponse = new ResponseEntity<>(avgBatchWeek, HttpStatus.OK);
		
		Map<Integer, Double[]> avgBatchOverall = reportingService.getAvgBatchOverall(batchId, AssessmentType.Exam);
		ResponseEntity<Map<Integer, Double[]>> avgBatchOverallReponse = new ResponseEntity<>(avgBatchOverall, HttpStatus.OK);
		
		return new ResponseEntity<Map<Trainee, Double>>(reportingService.getBarChartOverAll(batchId), HttpStatus.OK);
	}
	
	

}
