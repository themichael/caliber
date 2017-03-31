package com.revature.caliber.controllers;

import com.revature.caliber.beans.*;
import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Note;
import com.revature.caliber.models.SalesforceUser;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * The type Trainer batch controller.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/trainer")
public class TrainerBatchController {

	private AssessmentService assessmentService;
	private BatchService batchService;
	private GradeService gradeService;
	private NoteService noteService;
	private TrainerService trainerService;
	private TraineeService traineeService;

    /**
     * getAllBatches - REST API method, retrieves all batches belonging to the trainer
     *
     * @return in JSON, a set of batch objects
     */
    @RequestMapping(value = "/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Batch>> getAllBatches(Authentication authentication) {
    	SalesforceUser salesforceUser = (SalesforceUser) authentication.getPrincipal();
        Trainer trainer = trainerService.getTrainer(salesforceUser.getEmail());
        Set<Batch> batches = batchService.getTrainerBatch(trainer.getTrainerId());
		return new ResponseEntity<Set<Batch>>(batches, HttpStatus.OK);
    }

    /**
     * getCurrentBatch - REST API method, retrieves the current batch
     *
     * @return - in JSON, a batch object
     */
    @RequestMapping(value = "/batch/current", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Batch>> getCurrentBatch() {
        SalesforceUser salesforceUser = (SalesforceUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Trainer trainer = trainerService.getTrainer(salesforceUser.getEmail());
		List<Batch> batches = batchService.getCurrentBatch(trainer.getTrainerId());
		return new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);
	}

    /**
     * Create new week response entity.
     *
     * @param week the week
     * @return the response entity
     */
    @RequestMapping(value = "/week/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewWeek(@RequestBody Batch batch) {
		short weeks = batch.getWeeks();
		batch.setWeeks(++weeks);
		batchService.updateBatch(batch);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Create grade response entity.
     *
     * @param grade the grade
     * @return the response entity
     */
    @RequestMapping(value = "/grade/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createGrade(@RequestBody Grade grade) {
		//TODO Implement me
		throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Update grade response entity.
     *
     * @param grade the grade
     * @return the response entity
     */
    @RequestMapping(value = "/grade/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateGrade(@RequestBody Grade grade) {
		//TODO Implement me
		throw new UnsupportedOperationException("Not yet implemented");
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
		//TODO Implement me
		throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Delete assessment response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/delete/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAssessment(@PathVariable int id) {
		//TODO Implement me
		throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Update assessment response entity.
     *
     * @param assessment the assessment
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAssessment(@RequestBody Assessment assessment) {
		//TODO Implement me
		throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Update assessment response entity.
     *
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Assessment>> getAllAssessments() {
		//TODO Implement me
		throw new UnsupportedOperationException("Not yet implemented");
    }

    @RequestMapping(value = "/assessment/week/{batchId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Assessment>> getAssessmentsByWeek(@PathVariable long batchId){
		//TODO Implement me
		throw new UnsupportedOperationException("Not yet implemented");
    }
    
    @RequestMapping(value = "/byemail/{email}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Trainer> getTrainerByEmail(@PathVariable String email){
    	return new ResponseEntity<Trainer>(trainerService.getTrainer(email), HttpStatus.OK);
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
