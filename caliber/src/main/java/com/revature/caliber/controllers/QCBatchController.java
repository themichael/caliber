package com.revature.caliber.controllers;

import com.revature.caliber.beans.*;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.service.AssessmentService;
import com.revature.caliber.service.BatchService;
import com.revature.caliber.service.GradeService;
import com.revature.caliber.service.NoteService;
import com.revature.caliber.service.TraineeService;
import com.revature.caliber.service.TrainerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The type Qc batch controller.
 */
@RestController
@RequestMapping("/qc")
public class QCBatchController {

	private AssessmentService assessmentService;
	private BatchService batchService;
	private GradeService gradeService;
	private NoteService noteService;
	private TrainerService trainerService;
	private TraineeService traineeService;

    /**
     * Gets all batches.
     *
     * @return the all batches
     */
    @RequestMapping(value = "/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Batch>> getAllBatches() {
		List<Batch> batches = new ArrayList<>(batchService.getAllBatch());
		return new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);
    }

    /**
     * Create grade response entity.
     *
     * @param grade the grade
     * @return the response entity
     */
    @RequestMapping(value = "/grade/create",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createGrade(@RequestBody Grade grade) {
		long id = gradeService.insertGrade(grade);
		return new ResponseEntity<Long>(id, HttpStatus.CREATED);
    }

    /**
     * Update grade response entity.
     *
     * @param grade the grade
     * @return the response entity
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/grade/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateGrade(@RequestBody Grade grade) {
		gradeService.updateGrade(grade);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Create assessment response entity.
     *
     * @param assessment the assessment
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/create",
                    method = RequestMethod.PUT,
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createAssessment(@RequestBody Assessment assessment) {
    	long id = assessmentService.insert(assessment);
		return new ResponseEntity<Long>(id, HttpStatus.CREATED);
    }

    /**
     * Delete assessment response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "assessment/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAssessment(@PathVariable int id) {
		Assessment assessment = assessmentService.getById(id);
    	assessmentService.delete(assessment);
    	return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

   /**
     * Update assessment response entity.
     *
     * @param assessment the assessment
     * @return the response entity
     */
    @SuppressWarnings("rawtypes")
	@RequestMapping(value = "/assessment/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAssessment(@RequestBody Assessment assessment) {
		assessmentService.update(assessment);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Gets all assessments.
     *
     * @return the all assessments
     */
    @RequestMapping(value = "/assessment/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Assessment>> getAllAssessments() {
		List<Assessment> assessments = 
				new ArrayList<Assessment>(assessmentService.getAll());
		return new ResponseEntity<List<Assessment>>(assessments, HttpStatus.OK);
    }

    /**
     * Create or update assessment for a TRAINEE on a given week.
     * Service must validate that batchId IS NULL. Reject 400 if otherwise
     *
     * @param note the note
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/note", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity editAssessmentNote(@RequestBody Note note) {
		//TODO Implement me
		throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     *  Create or update assessment for a BATCH on a given week.
     *  Service must validate that traineeId IS NULL. Reject 400 if otherwise
     *
     * @param batchNote the batch note
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/batch/note", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBatchNoteForAssessment(@RequestBody Note batchNote) {
 		//TODO Implement me
 		throw new UnsupportedOperationException("Not yet implemented");
    }

    @Autowired
    public void setAssessmentService(AssessmentService assessmentService) {
		this.assessmentService = assessmentService;
	}
    @Autowired
	public void setBatchService(BatchService batchService) {
		this.batchService = batchService;
	}
    @Autowired
	public void setGradeService(GradeService gradeService) {
		this.gradeService = gradeService;
	}
    @Autowired
	public void setNoteService(NoteService noteService) {
		this.noteService = noteService;
	}
    @Autowired
	public void setTrainerService(TrainerService trainerService) {
		this.trainerService = trainerService;
	}
    @Autowired
	public void setTraineeService(TraineeService traineeService) {
		this.traineeService = traineeService;
	}

}
