package com.revature.caliber.controllers;

import com.revature.caliber.beans.Batch;
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
     * getAllCurrentBatches - REST API method, retrieves all current batches
     *
     * @return - in JSON, a set of batch objects
     */
    @RequestMapping(value = "/batch/current", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Batch>> getAllCurrentBatches() {
        return new ResponseEntity<>(new ApiGatewayImpl().getAllBatches(), HttpStatus.OK);
    }

    @RequestMapping(value = "/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Batch>> getAllBatches() {
        return new ResponseEntity<>(new ApiGatewayImpl().getAllBatches(), HttpStatus.OK);
    }

    /**
     * Update all current batches response entity.
     *
     * @param batches the batches
     * @return the response entity
     */
    @RequestMapping(value = "/batch/current", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Batch>> updateAllCurrentBatches(@RequestBody Set<Batch> batches) {
        return new ResponseEntity<>(new ApiGatewayImpl().updateAllCurrentBatches(batches), HttpStatus.OK);
    }


    /**
     * getCurrentBatch - REST API method, retrieves a batch from a list of
     * current batches
     *
     * @param id - batch id
     * @return - in JSON, a batch object
     */
    @RequestMapping(value = "/batch/current/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> getBatchFromCurrentBatchesById(@PathVariable int id) {
        return new ResponseEntity<>(new ApiGatewayImpl().getBatchFromCurrentBatchesById(id), HttpStatus.OK);
    }

    /**
     * Update batch from current batches by id response entity.
     *
     * @param batch the batch
     * @return the response entity
     */
    @RequestMapping(value = "/batch/current", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> updateBatchFromCurrentBatchesById(@RequestBody Batch batch) {
        return new ResponseEntity<>(new ApiGatewayImpl().updateBatchFromCurrentBatchesById(batch), HttpStatus.OK);
    }

    /**
     * Insert batch into current batches response entity.
     *
     * @param batch the batch
     * @return the response entity
     */
    @RequestMapping(value = "/batch/current", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> insertBatchIntoCurrentBatches(@RequestBody Batch batch) {
        return new ResponseEntity<>(new ApiGatewayImpl().insertBatchIntoCurrentBatches(batch), HttpStatus.OK);
    }


    /**
     * Delete batch from current batches by id.
     *
     * @param id the id
     * @return the response entity
     */
    @RequestMapping(value = "/batch/current/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> deleteBatchFromCurrentBatchesById(@PathVariable int id) {
        return new ResponseEntity<>(new ApiGatewayImpl().deleteBatchFromCurrentBatchesById(id), HttpStatus.OK);
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
