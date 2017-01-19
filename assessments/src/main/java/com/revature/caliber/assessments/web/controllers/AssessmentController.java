package com.revature.caliber.assessments.web.controllers;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.service.BusinessDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController    // infers @ResponseBody on all methods && @Controller
public class AssessmentController {

    private BusinessDelegate delegate;

    //Spring setter based DI
    @Autowired
    public void setDelegate(BusinessDelegate delegate) {
        this.delegate = delegate;
    }

//TODO get methods

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
