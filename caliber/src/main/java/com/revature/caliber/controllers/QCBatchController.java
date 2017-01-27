package com.revature.caliber.controllers;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.gateway.impl.ApiGatewayImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * The type Qc batch controller.
 */
@RestController
//@RequestMapping("/qc")
public class QCBatchController {
    /**
     * Gets all batches.
     *
     * @return the all batches
     */
    @RequestMapping(value = "/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Batch>> getAllBatches() {
        return new ResponseEntity<>(new ApiGatewayImpl().getAllBatches(), HttpStatus.OK);
    }

    /**
     * Create grade response entity.
     *
     * @param grade the grade
     * @return the response entity
     */
    @RequestMapping(value = "/grade/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createGrade(@RequestBody Grade grade) {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        apiGateway.createGrade(grade);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Update grade response entity.
     *
     * @param grade the grade
     * @return the response entity
     */
    @RequestMapping(value = "/grade/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateGrade(@RequestBody Grade grade) {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
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
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        apiGateway.createAssessment(assessment);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Delete assessment response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "assessment/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteAssessment(@PathVariable int id) {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
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
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        apiGateway.updateAssessment(assessment);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Gets all assessments.
     *
     * @return the all assessments
     */
    @RequestMapping(value = "/assessment/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Assessment>> getAllAssessments() {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        return new ResponseEntity<>(apiGateway.getAllAssessments(), HttpStatus.OK);
    }
}
