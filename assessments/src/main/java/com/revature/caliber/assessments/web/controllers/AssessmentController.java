package com.revature.caliber.assessments.web.controllers;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.service.BusinessDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController    // infers @ResponseBody on all methods && @Controller
@CrossOrigin(origins = {"*"},
        methods = { RequestMethod.GET,
                    RequestMethod.POST,
                    RequestMethod.PUT,
                    RequestMethod.DELETE},
        allowedHeaders = {"X-PINGOTHER", "Content-Type"},
        maxAge = 10)
public class AssessmentController {

    private BusinessDelegate delegate;

    //Spring setter based DI
    @Autowired
    public void setDelegate(BusinessDelegate delegate) {
        this.delegate = delegate;
    }

//    Get
    // getAllAssessments
    @RequestMapping(
            value = "/assessment/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Assessment>> getAll() {
        Set<Assessment> assessments = delegate.getAllAssessments();
        if (assessments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

    // getAssessmentById
    @RequestMapping(
            value = "/assessment/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Assessment> getById(@PathVariable("id") int id) {
        Assessment assessment = delegate.getAssessmentById(id);
        if (assessment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assessment, HttpStatus.OK);
    }

    // getAssessmentsByTrainerId
    @RequestMapping(
            value = "/assessment/trainer{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Assessment>> getByTrainerId(@PathVariable("id") int id) {
        Set<Assessment> assessments = delegate.getAssessmentsByTrainerId(id);
        if (assessments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

    // getAssessmentsByWeekId
    @RequestMapping(
            value = "/assessment/week{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Assessment>> getByWeekId(@PathVariable("id") int id) {
        Set<Assessment> assessments = delegate.getAssessmentsByWeekId(id);
        if (assessments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

    //  getAssessmentsByBatchId
    @RequestMapping(
            value = "/assessment/batch{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Assessment>> getByBatchId(@PathVariable("id") int id) {
        Set<Assessment> assessments = delegate.getAssessmentsByBatchId(id);
        if (assessments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(assessments, HttpStatus.OK);
    }

//    Create
    //POST is used over PUT since we are not specifying specific assessment url
    @RequestMapping(
            value = "/assessment/new",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createAssessment(@RequestBody Assessment assessment) {
        delegate.insertAssessment(assessment);
        return new ResponseEntity(HttpStatus.CREATED);
    }

//    Update
    @RequestMapping(
            value = "/assessment/update",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAssessment(@RequestBody Assessment assessment) {
        delegate.updateAssessment(assessment);
        return new ResponseEntity(HttpStatus.OK);
    }

//    Delete
    @RequestMapping(
            value = "/assessment/delete",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAssessment(@RequestBody Assessment assessment) {
        delegate.deleteAssessment(assessment);
        return new ResponseEntity(HttpStatus.OK);
    }
}
