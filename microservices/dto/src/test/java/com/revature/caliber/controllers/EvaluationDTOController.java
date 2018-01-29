package com.revature.caliber.controllers;

import javax.validation.Valid;

import com.revature.caliber.beans.Grade;
import com.revature.caliber.services.EvaluationService;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("isAuthenticated()")
@CrossOrigin(origins = "http://ec2-54-163-132-124.compute-1.amazonaws.com")
public class EvaluationDTOController {
    private static final Logger log = Logger.getLogger(EvaluationDTOController.class);
    private EvaluationService evaluationService;
    
    /**
	 * Create grade with DTO
	 *
	 * @param grade
	 * @return
	 */
	@RequestMapping(value = "/dto/grade", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grade> createGradeByDTO(@Valid @RequestBody Grade grade) {
		log.info("Saving grade: " + grade);
		evaluationService.save(grade);
		return new ResponseEntity<>(grade, HttpStatus.CREATED);
    }
    
    /**
	 * Update grade with DTO
	 *
	 * @param grade
	 * @return
	 */
	@RequestMapping(value = "/dto/grade/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateGradeByDTO(@Valid @RequestBody Grade grade) {
		log.info("Updating grade: " + grade);
		//evaluationService.update(grade);
		// TODO: supporting implementation does not currently exist
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}