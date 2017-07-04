package com.revature.caliber.controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.AssessmentType;
import com.revature.caliber.beans.NoteType;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.beans.SkillType;
import com.revature.caliber.beans.TrainerRole;
import com.revature.caliber.beans.TrainingStatus;
import com.revature.caliber.beans.TrainingType;

/**
 * Provides enumerated types to the UI
 * 
 * @author Patrick Walsh
 * @author Stanley Chouloute
 *
 */
@RestController
@RequestMapping(value = "types", produces = MediaType.APPLICATION_JSON_VALUE)
public class TypeController {

	private static final Logger log = Logger.getLogger(TypeController.class);

	/*
	 *******************************************************
	 * TODO TYPE SERVICES
	 *
	 *******************************************************
	 */

	/**
	 * Get Skill types to select appropriate type on UI
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/skill/all", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<List<String>> allSkillTypes() {
		log.info("Fetching skill types");
		List<String> types = Stream.of(SkillType.values()).map(Enum::toString).collect(Collectors.toList());
		return new ResponseEntity<List<String>>(types, HttpStatus.OK);
	}

	/**
	 * Get Training types to select appropriate type on UI
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/training/all", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<List<String>> allTrainingTypes() {
		log.info("Fetching training types");
		List<String> types = Stream.of(TrainingType.values()).map(Enum::name).collect(Collectors.toList());
		return new ResponseEntity<List<String>>(types, HttpStatus.OK);
	}

	/**
	 * Get Training Status types to select appropriate type on UI
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/trainingstatus/all", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<List<String>> allTrainingStatusTypes() {
		log.info("Fetching training status types");
		List<String> types = Stream.of(TrainingStatus.values()).map(Enum::name).collect(Collectors.toList());
		return new ResponseEntity<List<String>>(types, HttpStatus.OK);
	}

	/**
	 * Get note types to select appropriate type on UI
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/note/all", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<List<String>> allNoteTypes() {
		log.info("Fetching note types");
		List<String> types = Stream.of(NoteType.values()).map(Enum::name).collect(Collectors.toList());
		return new ResponseEntity<List<String>>(types, HttpStatus.OK);
	}

	/**
	 * Get QC Status types to select appropriate type on UI
	 *
	 * @return the response entity
	 */
	@RequestMapping(value = "/qcstatus/all", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<List<String>> allQCStatusTypes() {
		log.info("Fetching QC status types");
		List<String> types = Stream.of(QCStatus.values()).map(Enum::name).collect(Collectors.toList());
		return new ResponseEntity<List<String>>(types, HttpStatus.OK);
	}

	/**
	 * Get assessment types for dropdown selection on the UI
	 *
	 * @param assessment
	 *            the assessment
	 * @return the response entity
	 */
	@RequestMapping(value = "/assessment/all", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<List<String>> allAssessmentTypes() {
		log.info("Fetching assessment types");
		List<String> types = Stream.of(AssessmentType.values()).map(Enum::name).collect(Collectors.toList());
		return new ResponseEntity<List<String>>(types, HttpStatus.OK);
	}
	
	/**
	 * Get Trainer Tier for dropdown selection on the UI
	 *
	 * @param Role
	 *            the Tier
	 * @return the response entity
	 */
	@RequestMapping(value = "/trainer/role/all", method = RequestMethod.GET)
	// @PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<List<String>> allTrainerRoles() {
		log.info("Fetching Trainer Roles");
		List<String> types = Stream.of(TrainerRole.values()).map(Enum::name).collect(Collectors.toList());     //Used toString to Display the roles without the underscore
		return new ResponseEntity<List<String>>(types, HttpStatus.OK);
	}
}
