package com.revature.caliber.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.data.AssessmentDAO;

/**
 * Used for assessment CRUD operations. 
 * Includes both Trainer and QC assessments.
 * Application logic has no business being in a DAO
 * nor in a Controller. This is the ideal place for calculations 
 * 
 * @author Patrick Walsh
 *
 */
@Service
public class AssessmentService {

	private final static Logger log = Logger.getLogger(AssessmentService.class);
	private AssessmentDAO assessmentDAO;
	
	@Autowired
	public void setAssessmentDAO(AssessmentDAO assessmentDAO) {this.assessmentDAO = assessmentDAO;}
	
	
}
