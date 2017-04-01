package com.revature.caliber.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Batch;
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
@RequestMapping(value="training", produces=MediaType.APPLICATION_JSON_VALUE)
public class TrainingController {

	private static Logger log = Logger.getLogger(TrainingController.class);
	private TrainingService trainingService;
	
	@Autowired
	public void setTrainingService(TrainingService trainingService) {
		this.trainingService = trainingService;
	}

	@RequestMapping(value="trainer/byemail/{email}", method=RequestMethod.GET)
	public ResponseEntity<Trainer> getByEmail(@PathVariable String email){
		log.info("Find trainer by email " + email);
		Trainer trainer = trainingService.getByEmail(email);
		return new ResponseEntity<Trainer>(trainer, HttpStatus.OK);
	}
	
	@RequestMapping(value="trainer/batch/all", method=RequestMethod.GET)
	public ResponseEntity<List<Batch>> getAllTrainerBatches(Authentication auth){
		// access user details through SecurityContext 
		SalesforceUser userPrincipal = (SalesforceUser) auth.getPrincipal();
		log.info("Getting all batches for trainerid:" + userPrincipal.getCaliberId() + 
				" with email " + userPrincipal.getEmail() + " and role " + userPrincipal.getRole());
		return new ResponseEntity<List<Batch>>(new ArrayList<Batch>(), HttpStatus.OK);
	}
}
