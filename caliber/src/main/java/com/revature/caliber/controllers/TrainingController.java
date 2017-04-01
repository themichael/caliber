package com.revature.caliber.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Trainer;
import com.revature.caliber.services.TrainingService;

/**
 * Services requests for Trainer, Trainee, and Batch information
 * 
 * @author Patrick Walsh
 *
 */
@RestController
@RequestMapping(produces=MediaType.APPLICATION_JSON_VALUE)
public class TrainingController {

	private static Logger log = Logger.getLogger(TrainingController.class);
	private TrainingService trainingService;
	
	@Autowired
	public void setTrainingService(TrainingService trainingService) {
		this.trainingService = trainingService;
	}

	@RequestMapping(value="training/trainer/byemail/{email}", method=RequestMethod.GET)
	public ResponseEntity<Trainer> getByEmail(@PathVariable String email){
		log.info("Find trainer by email " + email);
		Trainer trainer = trainingService.getByEmail(email);
		return new ResponseEntity<Trainer>(trainer, HttpStatus.OK);
	}
	
	@RequestMapping(value="caliber/trainer/batch/all", method=RequestMethod.GET)
	public ResponseEntity<Trainer> get(){
		log.info("Testing SecurityContext " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return new ResponseEntity<Trainer>(new Trainer(), HttpStatus.OK);
	}
}
