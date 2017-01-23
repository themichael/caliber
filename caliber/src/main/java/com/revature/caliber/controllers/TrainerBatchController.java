package com.revature.caliber.controllers;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.middleTier.BusinessDelegate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class TrainerBatchController {

    /***
     * Please change unit tests after connecting controller to midtier
     * OR after changing return values of test data
     ****/

    /**
     * getAllBatches - REST API method, retrieves all batches belonging to the trainer
     *
     * @return in JSON, a set of batch objects
     */
    @RequestMapping(value = "/trainer/batch/all", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Batch>> getAllBatches() {
        BusinessDelegate businessDelegate = new BusinessDelegate();
        return new ResponseEntity<>(businessDelegate.getAllBatches(), HttpStatus.OK);
    }

    /**
     * getCurrentBatch - REST API method, retrieves the current batch
     *
     * @return - in JSON, a batch object
     */
    @RequestMapping(value = "/trainer/batch/current", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> getCurrentBatch() {

        // Test data - remove and replace with call to middleTier
        BusinessDelegate delegate = new BusinessDelegate();
        return new ResponseEntity<>(delegate.getCurrentBatch(), HttpStatus.OK);
    }

    /**
     * getBatch - REST API method, retrieves a batch belonging to the trainer with the given id
     *
     * @param id - batch id
     * @return - in JSON, a batch object
     */
    @RequestMapping(value = "/trainer/batch/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> getBatch(@PathVariable int id) {
        // Test data - remove and replace with call to midtier
        BusinessDelegate delegate = new BusinessDelegate();
        Batch batch = delegate.getCurrentBatch();
        batch.setBatchId(id);
        return new ResponseEntity<>(batch, HttpStatus.OK);
    }


}
