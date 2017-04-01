package com.revature.caliber.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.services.AssessmentService;

/**
 * Used for assessment CRUD operations. 
 * Includes both Trainer and QC assessments
 * @author Patrick Walsh
 *
 */
@RestController
public class AssessmentController {

	private final static Logger log = Logger.getLogger(AssessmentController.class);
	private AssessmentService assessmentService;
	
	@Autowired
	public void setAssessmentService(AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}
	
	
	
}
