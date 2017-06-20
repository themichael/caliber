package com.revature.caliber.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.security.models.SalesforceUser;
import com.revature.caliber.services.TrainingService;

/**
 * Services requests for Trainer, Trainee, and Batch information
 * 
 * @author Patrick Walsh
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class TrainingController {

	private final static Logger log = Logger.getLogger(TrainingController.class);
	private TrainingService trainingService;

	@Autowired
	public void setTrainingService(TrainingService trainingService) {
		this.trainingService = trainingService;
	}

	/*
	 *******************************************************
	 * TODO TRAINER SERVICES
	 *
	 *******************************************************
	 */

	/**
	 * Finds a trainer by email. Used for logging in a user with the Salesforce
	 * controller `
	 * 
	 * @param email
	 * @return
	 */
	@RequestMapping(value = "/training/trainer/byemail/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trainer> findTrainer(@PathVariable String email) {
		log.info("Find trainer by email " + email);
		Trainer trainer = trainingService.findTrainer(email);
		return new ResponseEntity<Trainer>(trainer, HttpStatus.OK);
	}

	/**
	 * Returns all trainers from the database `
	 * 
	 * @return
	 */
	@RequestMapping(value = "/all/trainer/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<List<Trainer>> getAllTrainers() {
		log.info("Fetching all trainers");
		List<Trainer> trainers = trainingService.findAllTrainers();
		return new ResponseEntity<>(trainers, HttpStatus.OK);
	}

	/*
	 *******************************************************
	 * TODO BATCH SERVICES
	 *
	 *******************************************************
	 */

	/**
	 * Find all batches for the currently logged in trainer
	 * 
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/trainer/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasRole('TRAINER')")
	public ResponseEntity<List<Batch>> findAllBatchesByTrainer(Authentication auth) {
		Trainer userPrincipal = getPrincipal(auth);
		log.info("Getting all batches for trainer: " + userPrincipal);
		List<Batch> batches = trainingService.findAllBatches(userPrincipal.getTrainerId());
		return new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);
	}

	/**
	 * Create batch
	 *
	 * @param batch
	 *            the batch
	 * @return the response entity
	 */
	@RequestMapping(value = "/all/batch/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Batch> createBatch(@Valid @RequestBody Batch batch, Authentication auth) {
		log.info("Saving batch: " + batch);
		trainingService.save(batch);
		return new ResponseEntity<Batch>(batch, HttpStatus.CREATED);
	}

	/**
	 * Update batch
	 *
	 * @param batch
	 *            the batch
	 * @return the response entity
	 */
	@RequestMapping(value = "/all/batch/update", method = RequestMethod.PUT, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> updateBatch(@Valid @RequestBody Batch batch, Authentication auth) {
		// batch.setTrainer(getPrincipal(auth));
		log.info("Updating batch: " + batch);
		trainingService.update(batch);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete batch
	 *
	 * @param id
	 *            the id of the batch to delete
	 * @return the response entity
	 */
	@RequestMapping(value = "/all/batch/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> deleteBatch(@PathVariable int id) {
		Batch batch = new Batch();
		batch.setBatchId(id);
		log.info("Deleting batch: " + id);
		trainingService.delete(batch);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Gets all current batches
	 *
	 * @return the all batches
	 */
	@RequestMapping(value = {"/vp/batch/all/current" }, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('QC, VP')")
	public ResponseEntity<List<Batch>> getAllCurrentBatches() {
		log.info("Fetching all current batches");
		 List<Batch> batches = trainingService.findAllCurrentBatches();
		// List<Batch> batches = trainingService.findAllBatches();
		return new ResponseEntity<>(batches, HttpStatus.OK);

	}

	/**
	 * Gets all batches
	 *
	 * @return the all batches
	 */
	@RequestMapping(value ={"/qc/batch/all", "/vp/batch/all"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<List<Batch>> getAllBatches() {
		log.info("Fetching all batches");
		List<Batch> batches = trainingService.findAllBatches();
		return new ResponseEntity<>(batches, HttpStatus.OK);

	}

	/**
	 * Adds a new week to the batch. Increments counter of total_weeks in
	 * database
	 * 
	 * @param batchId
	 * @return
	 */
	@RequestMapping(value = "/trainer/week/new/{batchId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> createWeek(@PathVariable int batchId) {
		log.info("Adding week to batch: " + batchId);
		trainingService.addWeek(batchId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/*
	 *******************************************************
	 * TODO TRAINEE SERVICES
	 *
	 *******************************************************
	 */
	@RequestMapping(value = "/all/trainee", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trainee>> findAllByBatch(@RequestParam(required = true) Integer batch) {
		log.info("Finding trainees for batch: " + batch);
		List<Trainee> trainees = trainingService.findAllTraineesByBatch(batch);
		return new ResponseEntity<>(trainees, HttpStatus.OK);
	}

	@RequestMapping(value = "/all/trainee/dropped", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Trainee>> findAllDroppedByBatch(@RequestParam(required = true) Integer batch) {
		log.info("Finding dropped trainees for batch: " + batch);
		List<Trainee> trainees = trainingService.findAllDroppedTraineesByBatch(batch);
		return new ResponseEntity<>(trainees, HttpStatus.OK);
	}

	/**
	 * Create trainee
	 *
	 * @param trainee
	 *            the trainee
	 * @return the response entity
	 */
	@RequestMapping(value = "/all/trainee/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Trainee> createTrainee(@Valid @RequestBody Trainee trainee) {
		log.info("Saving trainee: " + trainee);
		trainingService.save(trainee);
		return new ResponseEntity<Trainee>(trainee, HttpStatus.CREATED);
	}

	/**
	 * Create trainees
	 *
	 * 
	 * Uneeded. just do multiple calls to createTrainee
	 * 
	 * @param trainees
	 *            the trainee
	 * @return the response entity
	 * 
	 */
	@Deprecated
	@RequestMapping(value = "/all/trainees/create", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> createTrainees(@RequestBody Trainee[] trainees) {
		// TODO quick and dirty. We should have @Transactional services to
		// create rollback for failed batchUpdates
		log.info("Saving trainees: " + trainees);
		if (trainees != null)
			for (Trainee trainee : trainees)
				trainingService.save(trainee);
		else
			throw new IllegalArgumentException("Trainees required.");
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Update trainee
	 *
	 * @param trainee
	 *            the trainee
	 * @return the response entity
	 */
	@RequestMapping(value = "/all/trainee/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> updateTrainee(@Valid @RequestBody Trainee trainee) {
		log.info("Updating trainee: " + trainee);
		trainingService.update(trainee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete trainee
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@RequestMapping(value = "/all/trainee/delete/{id}", method = RequestMethod.DELETE)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> deleteTrainee(@PathVariable int id) {
		Trainee trainee = new Trainee();
		trainee.setTraineeId(id);
		log.info("Deleting trainee: " + id);
		trainingService.delete(trainee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/all/trainee/getByEmail/{traineeEmail}", method = RequestMethod.GET)
	public ResponseEntity<Trainee> retreiveTraineeByEmail(@PathVariable String traineeEmail){
		Trainee trainee = new Trainee();
		trainee = trainingService.findTraineeByEmail(traineeEmail);
	
		return new ResponseEntity<Trainee>(trainee, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all/locations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> findCommonLocations() {
		log.info("Fetching common training locations");
		return new ResponseEntity<>(trainingService.findCommonLocations(), HttpStatus.OK);
	}

	/**
	 * Convenience method for accessing the Trainer information from the User
	 * Principal.
	 * 
	 * TODO :: read me:: Access user details through SecurityContext by
	 * injecting Authentication into Controller method. Use @PreAuthorize with
	 * Spring Expression Language (SpEL) to send 403 forbidden if not authorized
	 * <<<<<<< HEAD
	 * http://docs.spring.io/spring-security/site/docs/current/reference/html/el
	 * -access.html =======
	 * http://docs.spring.io/spring-security/site/docs/current/reference/html/el
	 * -access.html >>>>>>> 5aedf4196dfe4b91cac204fa623c7755fec4a5df
	 * 
	 * @param auth
	 * @return
	 */
	private Trainer getPrincipal(Authentication auth) {
		return ((SalesforceUser) auth.getPrincipal()).getCaliberUser();
	}
}