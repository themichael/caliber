package com.revature.caliber.controllers;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.gateway.impl.ApiGatewayImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        new ApiGatewayImpl().createBatch(batch);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Delete batch by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/batch/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity deleteBatchById(@PathVariable int id) {
        Batch batch  = new Batch();
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        batch.setBatchId(id);
        apiGateway.deleteBatch(batch);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/trainee/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity createTrainee(@RequestBody Trainee trainee) {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        apiGateway.createTrainee(trainee);
        return new ResponseEntity(HttpStatus.OK);
    }

}
