package com.revature.caliber.controllers;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Week;
import com.revature.caliber.gateway.impl.ApiGatewayImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * The type Trainer batch controller.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/trainer")
public class TrainerBatchController {

    /**
     * getAllBatches - REST API method, retrieves all batches belonging to the trainer
     *
     * @return in JSON, a set of batch objects
     */
    @RequestMapping(value = "/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Batch>> getAllBatches() {
        return new ResponseEntity<>(new ApiGatewayImpl().getAllBatches(), HttpStatus.OK);
    }

    /**
     * getCurrentBatch - REST API method, retrieves the current batch
     *
     * @return - in JSON, a batch object
     */
    @RequestMapping(value = "/batch/current", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> getCurrentBatch() {
        return new ResponseEntity<>(new ApiGatewayImpl().getCurrentBatch(), HttpStatus.OK);
    }

    /**
     * getBatch - REST API method, retrieves a batch belonging to the trainer with the given id
     *
     * @param id - batch id
     * @return - in JSON, a batch object
     */
    @RequestMapping(value = "/batch/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> getBatchByTrainerId(@PathVariable int id) {
        return new ResponseEntity<>(new ApiGatewayImpl().getBatchByTrainerId(id), HttpStatus.OK);
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
     * Create new week response entity.
     *
     * @param week the week
     * @return the response entity
     */
    @RequestMapping(value = "/week/new", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNewWeek(@PathVariable Week week) {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        apiGateway.createNewWeek(week);
        return new ResponseEntity(HttpStatus.OK);
    }
}
