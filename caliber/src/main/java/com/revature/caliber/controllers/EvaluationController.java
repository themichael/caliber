package com.revature.caliber.controllers;

import java.util.List;

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

import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.services.EvaluationService;

/**
 * Used to add grades for assessments and input notes
 * 
 * @author Patrick Walsh
 *
 */
@RestController
public class EvaluationController {

	private final static Logger log = Logger.getLogger(EvaluationController.class);
	private EvaluationService evaluationService;

	@Autowired
	public void setEvaluationService(EvaluationService evaluationService) {
		this.evaluationService = evaluationService;
	}

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
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> createGrade(@RequestBody Grade grade) {
		log.info("Saving grade: " + grade);
		evaluationService.save(grade);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Update grade 
	 *
	 * @param grade
	 * @return 
	 */
	@RequestMapping(value = "/trainer/grade/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> updateGrade(@RequestBody Grade grade) {
		log.info("Updating grade: " + grade);
		evaluationService.update(grade);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	/**
	 * Returns absolutely all grades for only the most coarsely-grained
	 * reporting. Useful for feeding data into application for statistical
	 * analyses, such as regression analysis, calculating mean, and finding
	 * average ;)
	 * 
	 * @param traineeId
	 * @return
	 */
	@RequestMapping(value = "/vp/grade/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('VP')")
	public List<Grade> findAll() {
		log.info("Finding all grades");
		return evaluationService.findAllGrades();
	}

	/**
	 * Returns grades for all trainees that took a particular assignment. Great
	 * for finding average/median/highest/lowest grades for a test
	 * 
	 * @param assessmentId
	 * @return
	 */
	@RequestMapping(value = "/all/grades/assessment/{assessmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public List<Grade> findByAssessment(@PathVariable Long assessmentId) {
		log.info("Finding grades for assessment: " + assessmentId);
		return evaluationService.findGradesByAssessment(assessmentId);
	}

	/**
	 * Returns all grades for a trainee. Useful for generating a full-view of
	 * individual trainee performance.
	 * 
	 * @param traineeId
	 * @return
	 */
	@RequestMapping(value = "/all/grade/trainee/{traineeId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public List<Grade> findByTrainee(@PathVariable Integer traineeId) {
		log.info("Finding all grades for trainee: " + traineeId);
		return evaluationService.findGradesByTrainee(traineeId);
	}

	/**
	 * Returns all grades for a batch. Useful for calculating coarsely-grained
	 * data for reporting.
	 * 
	 * @param batchId
	 * @return
	 */
	@RequestMapping(value = "/all/grade/batch/{batchId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public List<Grade> findByBatch(@PathVariable Integer batchId) {
		log.info("Finding all grades for batch: " + batchId);
		return evaluationService.findGradesByBatch(batchId);
	}

	/**
	 * Returns all grades for a category. Useful for improving performance time
	 * of company-wide reporting
	 * 
	 * @param batchId
	 * @return
	 */
	@RequestMapping(value = "/all/grade/category/{categoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public List<Grade> findByCategory(@PathVariable Integer categoryId) {
		log.info("Finding all grades for category: " + categoryId);
		return evaluationService.findGradesByCategory(categoryId);
	}

	/**
	 * Returns grades for all trainees in the batch on a given week. Used to
	 * load grade data onto the input spreadsheet, as well as tabular/chart
	 * reporting.
	 * 
	 * @param batchId
	 * @param week
	 * @return
	 */
	@RequestMapping(value = "/all/grades/batch/{batchId}/week/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public List<Grade> findByWeek(@PathVariable Integer batchId, @PathVariable Integer week) {
		log.info("Finding week " + week + " grades for batch: " + batchId);
		return evaluationService.findGradesByWeek(batchId, week);
	}

	/**
	 * Returns all grades issued as acting trainer or cotrainer to a batch.
	 * Useful for calculating coarsely-grained data for reporting. Potential
	 * refactor here.. this queries database twice where we could find way to
	 * simply join.
	 * 
	 * @param trainerId
	 * @return
	 */
	@RequestMapping(value = "/all/grade/trainer/{trainerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public List<Grade> findByTrainer(@PathVariable Integer trainerId) {
		log.info("Finding all grades for trainer: " + trainerId);
		return evaluationService.findGradesByTrainer(trainerId);
	}

	/*
	 *******************************************************
	 * TODO ALL NOTE SERVICES
	 *
	 *******************************************************
	 */

	/**
	 * Create note
	 *
	 * @param note
	 * @return
	 */
	@RequestMapping(value = "/note/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> createNote(@RequestBody Note note) {
		log.info("Creating note: " + note);
		evaluationService.save(note);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	/**
	 * Update note
	 * 
	 * @param note
	 * @return
	 */
	@RequestMapping(value = "/note/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<Void> updateNote(@RequestBody Note note) {
		log.info("Updating note: " + note);
		evaluationService.update(note);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/*
	 *******************************************************
	 * TODO TRAINER NOTE SERVICES
	 *
	 *******************************************************
	 */
	/**
	 * FIND WEEKLY BATCH NOTES (TRAINER/PUBLIC)
	 * 
	 * @param batch
	 * @param week
	 * @return
	 */
	@RequestMapping(value = "/trainer/note/batch/{batchId}/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<List<Note>> findBatchNotes(@PathVariable Integer batchId, @PathVariable Integer week) {
		log.info("Finding week " + week + " batch notes for batch: " + batchId);
		return new ResponseEntity<List<Note>>(evaluationService.findBatchNotes(batchId, week), HttpStatus.OK);
	}

	/**
	 * FIND WEEKLY INDIVIDUAL NOTES (TRAINER/PUBLIC)
	 * 
	 * @param trainee
	 * @param week
	 * @return
	 */
	@RequestMapping(value = "/trainer/note/trainee/{traineeId}/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('TRAINER, QC, VP')")
	public ResponseEntity<List<Note>> findIndividualNotes(@PathVariable Integer traineeId, @PathVariable Integer week) {
		log.info("Finding week " + week + " individual notes for trainee: " + traineeId);
		return new ResponseEntity<List<Note>>(evaluationService.findIndividualNotes(traineeId, week), HttpStatus.OK);
	}

	/*
	 *******************************************************
	 * TODO QC NOTE SERVICES
	 *
	 *******************************************************
	 */
	/**
	 * FIND WEEKLY QC BATCH NOTES (NOT FOR TRAINERS)
	 * 
	 * @param batch
	 * @param week
	 * @return
	 */
	@RequestMapping(value = "/qc/note/batch/{batchId}/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('QC, VP')")
	public ResponseEntity<Note> findQCBatchNotes(@PathVariable Integer batchId, @PathVariable Integer week) {
		log.info("Finding week " + week + " QC batch notes for batch: " + batchId);
		return new ResponseEntity<Note>(evaluationService.findQCBatchNotes(batchId, week), HttpStatus.OK);
	}

	/*
	 * FIND WEEKLY QC INDIVIDUAL NOTES (NOT FOR TRAINERS)
	 * 
	 * @param trainee
	 * @param week
	 * @return
	 */
	@RequestMapping(value = "/qc/note/trainee/{traineeId}/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasAnyRole('QC, VP')")
	public ResponseEntity<List<Note>> findQCIndividualNotes(@PathVariable Integer traineeId, @PathVariable Integer week) {
		log.info("Finding week " + week + " QC individual notes for trainee: " + traineeId);
		return new ResponseEntity<List<Note>>(evaluationService.findQCIndividualNotes(traineeId, week), HttpStatus.OK);
	}

	/*
	 *******************************************************
	 * TODO VP NOTE SERVICES
	 *
	 *******************************************************
	 */
	
	/**
	 * FIND ALL WEEKLY BATCH NOTES (VP ONLY)
	 * 
	 * @param batch
	 * @param week
	 * @return
	 */
	@RequestMapping(value = "/vp/note/batch/{batchId}/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('VP')")
	public ResponseEntity<List<Note>> findAllBatchNotes(@PathVariable Integer batchId, @PathVariable Integer week) {
		log.info("Finding week " + week + " batch notes for batch: " + batchId);
		return new ResponseEntity<List<Note>>(evaluationService.findAllBatchNotes(batchId, week), HttpStatus.OK);
	}

	/**
	 * FIND ALL WEEKLY INDIVIDUAL NOTES (VP ONLY)
	 * 
	 * @param trainee
	 * @param week
	 * @return
	 */
	@RequestMapping(value = "/vp/note/trainee/{traineeId}/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	//@PreAuthorize("hasRole('VP')")
	public ResponseEntity<List<Note>> findAllIndividualNotes(@PathVariable Integer traineeId, @PathVariable Integer week) {
		log.info("Finding all week " + week + " individual notes for trainee: " + traineeId);
		return new ResponseEntity<List<Note>>(evaluationService.findAllIndividualNotes(traineeId, week), HttpStatus.OK);
	}
	
	/**
	 * Find all qc trainee notes
	 * @return
	 */
	@RequestMapping(value = "/qc/trainee/note/{batchId}/{week}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Note>> getAllQCTraineeNotes(@PathVariable Integer batchId, @PathVariable Integer week) {
        log.info("Getting all trainee notes by QC");
        return new ResponseEntity<List<Note>>(evaluationService.findAllQCTraineeNotes(batchId, week), HttpStatus.OK);
    }
}
