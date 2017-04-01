package com.revature.caliber.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.services.EvaluationService;

/**
 * Used to add grades for assessments and input notes 
 * @author Patrick Walsh
 *
 */
@RestController
public class EvaluationController {

	private final static Logger log = Logger.getLogger(EvaluationController.class);
	private EvaluationService evaluationService;
	
	@Autowired
	public void setEvaluationService(EvaluationService evaluationService) {this.evaluationService = evaluationService;}

	
	
}
