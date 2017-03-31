package com.revature.caliber.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.service.AssessmentService;
import com.revature.caliber.service.BatchService;
import com.revature.caliber.service.GradeService;
import com.revature.caliber.service.NoteService;
import com.revature.caliber.service.ReportingService;
import com.revature.caliber.service.TraineeService;
import com.revature.caliber.service.TrainerService;

/**
 * The type All controller.
 */
@RestController
@RequestMapping("/all")
public class AllController {

	private AssessmentService assessmentService;
	private BatchService batchService;
	private GradeService gradeService;
	private NoteService noteService;
	private TrainerService trainerService;
	private TraineeService traineeService;
	private ReportingService reportingService;
	
	/**
	 * Create batch response entity.
	 *
	 * @param batch
	 *            the batch
	 * @return the response entity
	 */

	@RequestMapping(value = "/batch/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Long> createBatch(@RequestBody Batch batch) {
		long id = batchService.createBatch(batch);
		return new ResponseEntity<Long>(id, HttpStatus.CREATED);
	}

	/**
	 * Update batch response entity.
	 *
	 * @param batch
	 *            the batch
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/batch/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity updateBatch(@RequestBody Batch batch) {
		batchService.updateBatch(batch);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete batch response entity.
	 *
	 * @param id
	 *            the id of the batch to delete
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "batch/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity deleteBatch(@PathVariable int id) {
		Batch batch = batchService.getBatch(id);
		batchService.deleteBatch(batch);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	/**
	 * Create trainee response entity.
	 *
	 * @param trainee
	 *            the trainee
	 * @return the response entity
	 */
	@RequestMapping(value = "/trainee/create", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Long> createTrainee(@RequestBody Trainee trainee) {
		long id = traineeService.createTrainee(trainee);
		return new ResponseEntity<Long>(id, HttpStatus.CREATED);
	}

	/**
	 * Create trainees response entity.
	 *
	 * @param trainees
	 *            the trainee
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/trainees/create", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity createTrainees(@RequestBody Trainee[] trainees) {
		for(Trainee trainee : trainees)
			traineeService.createTrainee(trainee);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	/**
	 * Update trainee response entity.
	 *
	 * @param trainee
	 *            the trainee
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/trainee/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity updateTrainee(@RequestBody Trainee trainee) {
		traineeService.updateTrainee(trainee);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete trainee response entity.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/trainee/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity deleteTrainee(@PathVariable int id) {
		Trainee trainee = traineeService.getTrainee(id);
		traineeService.deleteTrainee(trainee);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

	/**
	 * Gets assessment grades by id.
	 *
	 * @param id
	 *            the id
	 * @return the assessment grades by id
	 */
	@RequestMapping(value = "/grades/assessment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<List<Grade>> getAssessmentGradesById(@PathVariable int id) {
		List<Grade> grades = gradeService.getGradesByAssessment(id);
		return new ResponseEntity<List<Grade>>(grades, HttpStatus.OK);
	}

	/**
	 * Aggregate function - get values for trainee by tech
	 *
	 * @param traineeId
	 * @return
	 */
	@RequestMapping(value = "/agg/tech/trainee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Double[]>> aggregateTechTrainee(@PathVariable("id") int traineeId) {
		// TODO implement me
		HashMap<String, Double[]> overallPerformanceTraineeByTech = 
				reportingService.getWeekGradeDataForTrainee(traineeId);	
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@RequestMapping(value = "/agg/week/trainee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Double[]>> aggregateWeekTrainee(@PathVariable("id") int traineeId) {
		//TODO Implement me
		HashMap<String, Double[]> overallPerformanceTraineeEachWeek;
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@RequestMapping(value = "/agg/tech/batch/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Double[]>> aggregateTechBatch(@PathVariable("id") int batchId) {
		//TODO Implement me
		HashMap<String, Double[]> overallPerformanceBatchByTech;
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@RequestMapping(value = "/agg/week/batch/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Double[]>> aggregateWeekBatch(@PathVariable("id") int batchId) {
		//TODO Implement me
		HashMap<String, Double[]> overallPerformanceBatchEachWeek;
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@RequestMapping(value = "/agg/batch/trainer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, Double[]>> aggregateTraineesTrainer(@PathVariable("id") int trainerId) {
		//TODO Implement me
		HashMap<String, Double[]> overallPerformanceAllTraineesForTrainer;
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@RequestMapping(value = "/trainer/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trainer>> getAllTrainers() {
		List<Trainer> trainers = new ArrayList<>(trainerService.getAllTrainers());
		return new ResponseEntity<List<Trainer>>(trainers, HttpStatus.OK);
	}

    @Autowired
    public void setAssessmentService(AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}
    @Autowired
	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}
    @Autowired
	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}
    @Autowired
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}
    @Autowired
	public void setTrainerService(TrainerService trainerService) {
		this.trainerService = trainerService;
	}
    @Autowired
	public void setTraineeService(TraineeService traineeService) {
		this.traineeService = traineeService;
	}
    @Autowired
	public void setReportingService(ReportingService reportingService) {
		this.reportingService = reportingService;
	}

}
