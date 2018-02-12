package com.revature.caliber.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.model.Grade;
import com.revature.caliber.service.GradeCompositionService;

@RestController
//@PreAuthorize("isAuthenticated()")
@CrossOrigin
public class GradeController {
	private static final Logger log = Logger.getLogger(GradeController.class);
	
	@Autowired
	private GradeCompositionService gradeService;
	
	private static final String FINDING_WEEK = "Finding week ";
	
	/*
	 *******************************************************
	 * TODO GRADE SERVICES
	 *
	 *******************************************************
	 */

	/**
	 * Create grade
	 *
	 * @param grade
	 * @return
	 */
	@RequestMapping(value = "/trainer/grade/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER','PANEL')")
	public ResponseEntity<Grade> createGrade(@Valid @RequestBody Grade grade) {
		log.info("Saving grade: " + grade);
		gradeService.save(grade);
		return new ResponseEntity<>(grade, HttpStatus.CREATED);
	}

	/**
	 * Update grade
	 *
	 * @param grade
	 * @return
	 */
	@RequestMapping(value = "/trainer/grade/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER','PANEL')")
	public ResponseEntity<Void> updateGrade(@Valid @RequestBody Grade grade) {
		log.info("Updating grade: " + grade);
		gradeService.update(grade);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Returns grades for all trainees in the batch on a given week. Used to load
	 * grade data onto the input spreadsheet, as well as tabular/chart reporting.
	 * 
	 * @param batchId
	 * @param week
	 * @return
	 */
	@RequestMapping(value = "/all/grades/batch/{batchId}/week/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING','PANEL')")
	public ResponseEntity<Map<Integer, List<Grade>>> findByWeek(@PathVariable Integer batchId,
			@PathVariable Integer week) {
		log.info(FINDING_WEEK + week + " grades for batch: " + batchId);
		Map<Integer, List<Grade>> table = gradeService.findGradesByWeek(batchId, week);
		return new ResponseEntity<>(table, HttpStatus.OK);
	}
}
