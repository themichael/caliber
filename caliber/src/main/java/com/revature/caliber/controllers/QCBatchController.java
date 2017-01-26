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

@RestController
@RequestMapping("/qc")
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
}
