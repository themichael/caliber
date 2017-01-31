package com.revature.caliber.controllers;

import com.revature.caliber.beans.*;
import com.revature.caliber.gateway.ApiGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * The type Trainer batch controller.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/trainer")
public class TrainerBatchController {

    private ApiGateway apiGateway;

    /**
     * Sets api gateway.
     *
     * @param apiGateway the api gateway
     */
    @Autowired
    public void setApiGateway(ApiGateway apiGateway) {
        this.apiGateway = apiGateway;
    }

    /**
     * getAllBatches - REST API method, retrieves all batches belonging to the trainer
     *
     * @return in JSON, a set of batch objects
     */
    @RequestMapping(value = "/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Batch>> getAllBatches() {
        return new ResponseEntity<>(apiGateway.getAllBatches(), HttpStatus.OK);
    }

    /**
     * getCurrentBatch - REST API method, retrieves the current batch
     *
     * @return - in JSON, a batch object
     */
    @RequestMapping(value = "/batch/current", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> getCurrentBatch() {
        return new ResponseEntity<>(apiGateway.getCurrentBatch(), HttpStatus.OK);
    }

    /**
     * Create new week response entity.
     *
     * @param week the week
     * @return the response entity
     */
    @RequestMapping(value = "/week/new", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewWeek(@RequestBody Week week) {
        apiGateway.createNewWeek(week);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Create grade response entity.
     *
     * @param grade the grade
     * @return the response entity
     */
    @RequestMapping(value = "/grade/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createGrade(@RequestBody Grade grade) {
        apiGateway.createGrade(grade);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Update grade response entity.
     *
     * @param grade the grade
     * @return the response entity
     */
    @RequestMapping(value = "/grade/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateGrade(@RequestBody Grade grade) {
        apiGateway.updateGrade(grade);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Create assessment response entity.
     *
     * @param assessment the assessment
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAssessment(@RequestBody Assessment assessment) {
        apiGateway.createAssessment(assessment);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Delete assessment response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAssessment(@PathVariable int id) {
        Assessment assessment = new Assessment();
        assessment.setAssessmentId(id);
        apiGateway.deleteAssessment(assessment);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Update assessment response entity.
     *
     * @param assessment the assessment
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAssessment(@RequestBody Assessment assessment) {
        apiGateway.updateAssessment(assessment);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Update assessment response entity.
     *
     * @param assessment the assessment
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Assessment>> getAllAssessments(@RequestBody Assessment assessment) {
        Set<Assessment> set = apiGateway.getAllAssessments();
        return new ResponseEntity(set, HttpStatus.OK);
    }

    /**
     * Update assessment note response entity.
     *
     * @param note the note
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/note/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAssessmentNote(@RequestBody Note note) {
        apiGateway.updateAssessmentNote(note);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Create assessment note response entity.
     *
     * @param note the note
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/note/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAssessmentNote(@RequestBody Note note) {
        apiGateway.createAssessmentNote(note);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Create batch note for assessment response entity.
     *
     * @param batchNote the batch note
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/batch/note/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createBatchNoteForAssessment(@RequestBody BatchNote batchNote) {
        apiGateway.createBatchNote(batchNote);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Update batch note for assessment response entity.
     *
     * @param batchNote the batch note
     * @return the response entity
     */
    @RequestMapping(value = "/assessment/batch/note/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateBatchNoteForAssessment(@RequestBody BatchNote batchNote) {
        apiGateway.updateBatchNote(batchNote);
        return new ResponseEntity(HttpStatus.OK);
    }

}
