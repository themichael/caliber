package com.revature.caliber.controllers;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.gateway.impl.ApiGatewayImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * The type All controller.
 */
@RestController
@RequestMapping("/all")
public class AllController {
    /**
     * Create batch response entity.
     *
     * @param batch the batch
     * @return the response entity
     */
    @RequestMapping(value = "/batch/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity createBatch(@RequestBody Batch batch) {
        new ApiGatewayImpl().createBatch(batch);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Update batch response entity.
     *
     * @param batch the batch
     * @return the response entity
     */
    @RequestMapping(value = "/batch/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity updateBatch(@RequestBody Batch batch) {
        new ApiGatewayImpl().updateBatch(batch);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Delete batch response entity.
     *
     * @param id the id of the batch to delete
     * @return the response entity
     */
    @RequestMapping(value = "batch/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity deleteBatch(@PathVariable int id) {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        Batch batch = new Batch();
        batch.setBatchId(id);
        apiGateway.deleteBatch(batch);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Create trainee response entity.
     *
     * @param trainee the trainee
     * @return the response entity
     */
    @RequestMapping(value = "/trainee/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity createTrainee(@RequestBody Trainee trainee) {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        apiGateway.createTrainee(trainee);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Update trainee response entity.
     *
     * @param trainee the trainee
     * @return the response entity
     */
    @RequestMapping(value = "/trainee/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity updateTrainee(@RequestBody Trainee trainee) {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        apiGateway.updateTrainee(trainee);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/trainee/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity deleteTrainee(@PathVariable int id) {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        Trainee trainee = new Trainee();
        trainee.setTraineeId(id);
        apiGateway.deleteTrainee(trainee);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/grades/assessment/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Set<Grade>> getAssessmentGradesById(@PathVariable int id) {
        return new ResponseEntity<>(new ApiGatewayImpl().getAssessmentGradesById(id), HttpStatus.OK);
    }

}