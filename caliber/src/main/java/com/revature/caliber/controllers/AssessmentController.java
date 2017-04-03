package com.revature.caliber.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.services.AssessmentService;

/**
 * Used for assessment CRUD operations. Includes both Trainer and QC assessments
 * 
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

	/*
	 *******************************************************
	 * TODO ASSESSMENT SERVICES
	 *
	 *******************************************************
	 */
	
	/**
	 * Create assessment response entity.
	 *
	 * @param assessment
	 *            the assessment
	 * @return the response entity
	 */
	@RequestMapping(value = "/all/assessment/create", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> createAssessment(@RequestBody Assessment assessment) {
		log.info("Creating assessment: " + assessment);
		assessmentService.save(assessment);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Delete assessment response entity.
	 *
	 * @param id
	 *            the id
	 * @return the response entity
	 */
	@RequestMapping(value = "/all/assessment/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> deleteAssessment(@PathVariable Long id) {
		log.info("Deleting assessment: " + id);
		Assessment assessment = new Assessment();
		assessment.setAssessmentId(id);
		assessmentService.delete(assessment);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Update assessment response entity.
	 *
	 * @param assessment
	 *            the assessment
	 * @return the response entity
	 */
	@RequestMapping(value = "/all/assessment/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> updateAssessment(@RequestBody Assessment assessment) {
		log.info("Updating assessment: " + assessment);
		assessmentService.update(assessment);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Get assessment types for dropdown selection.
	 *
	 * @param assessment
	 *            the assessment
	 * @return the response entity
	 */
	@RequestMapping(value = "/assessment/type/all", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<List<String>> allAssessmentTypes() {
		log.info("Fetching assessment types");
		List<String> types = Stream.of(AssessmentType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
		return new ResponseEntity<List<String>>(types, HttpStatus.OK);
	}

}
