package com.revature.caliber.controllers;

import javax.validation.Valid;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.services.AssessmentService;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "http://ec2-54-163-132-124.compute-1.amazonaws.com")
public class AssessmentDTOController {
    private static final Logger log = Logger.getLogger(AssessmentDTOController.class);
    //private AssessmentService assessmentService;
    
    /**
	 * Assessment created through DTO
	 * 
	 * @param assessment
	 *            the assessment
	 * @return the response entity
	 */
	@RequestMapping(value = "/dto/assessment/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createAssessmentByDTO(@Valid @RequestBody Assessment assessment) {
		log.info("Creating assessment: " + assessment);
		//assessmentService.save(assessment);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}