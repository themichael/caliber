package com.revature.caliber.controllers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
public class TrainingController {

	private final static Logger log = Logger.getLogger(TrainingController.class);
	private TrainingService trainingService;
	
	@Autowired
	public void setTrainingService(TrainingService trainingService) {
		this.trainingService = trainingService;
	}

	@RequestMapping(value="/training/trainer/byemail/{email}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Trainer> getByEmail(@PathVariable String email){
		log.info("Find trainer by email " + email);
		Trainer trainer = trainingService.getByEmail(email);
		return new ResponseEntity<Trainer>(trainer, HttpStatus.OK);
	}
	
	/**
	 * TODO :: read me:: 
	 * 	Access user details through SecurityContext by injecting Authentication into Controller method.
	 * 	Use @PreAuthorize with Spring Expression Language (SpEL) to send 403 forbidden 
	 * 		http://docs.spring.io/spring-security/site/docs/current/reference/html/el-access.html
	 * @param auth
	 * @return
	 */
	@RequestMapping(value="/training/trainer/batch/all", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasRole('TRAINER')")
	public ResponseEntity<List<Batch>> getAllTrainerBatches(Authentication auth){
		SalesforceUser userPrincipal = (SalesforceUser) auth.getPrincipal();
		log.info("Getting all batches for trainerid:" + userPrincipal.getCaliberId() + 
				" with email " + userPrincipal.getEmail() + " and role " + userPrincipal.getRole());
		return new ResponseEntity<List<Batch>>(new ArrayList<Batch>(), HttpStatus.OK);
	}
}
