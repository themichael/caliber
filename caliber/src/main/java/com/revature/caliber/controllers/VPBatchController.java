package com.revature.caliber.controllers;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.gateway.impl.ApiGatewayImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * The type Vp batch controller.
 */
@RestController
@RequestMapping("/vp")
public class VPBatchController {
    /**
     * getAllBatches - REST API method, retrieves all the batches
     *
     * @return in JSON, a set of batch objects
     */
    @RequestMapping(value = "/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Batch>> getAllBatches() {
        return new ResponseEntity<>(new ApiGatewayImpl().getAllBatches(), HttpStatus.OK);
    }


    /**
     * getAllCurrentBatches - REST API method, retrieves all current batches
     *
     * @return in JSON, a set of batch objects
     */
    @RequestMapping(value = "/batch/current/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Batch>> getAllCurrentBatches() {
        return new ResponseEntity<>(new ApiGatewayImpl().getAllCurrentBatches(), HttpStatus.OK);
    }

    /**
     * getBatch - REST API method, retrieves a batch object from the current batches by id
     *
     * @param id - batch id
     * @return in JSON, a batch object
     */
    @RequestMapping(value = "/batch/current/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> getBatchFromCurrentBatchesById(@PathVariable int id) {
        return new ResponseEntity<>(new ApiGatewayImpl().getBatchFromCurrentBatchesById(), HttpStatus.OK);
    }

    /**
     * getCurrentBatch - REST API method, retrieves a batch from all batches by id
     *
     * @param id - batch id
     * @return in JSON, a batch object
     */
    @RequestMapping(value = "/batch/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> getBatchFromAllBatchesById(@PathVariable int id) {
        return new ResponseEntity<>(new ApiGatewayImpl().getBatchFromAllBatchesById(), HttpStatus.OK);
    }


    /**
     * Gets batch.
     *
     * @param batchId the batch id
     * @return the batch
     */
    public ResponseEntity<Batch> getBatch(int batchId) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Gets current batch.
     *
     * @param batchId the batch id
     * @return the current batch
     */
    public ResponseEntity<Batch> getCurrentBatch(int batchId) {
        // TODO Auto-generated method stub
        return null;
    }
}
