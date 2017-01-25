package com.revature.caliber.training.web.controllers;


import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.service.BusinessDelegate;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RestController
@CrossOrigin(origins = "*",
        methods = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET, RequestMethod.PUT },
        allowedHeaders = {"X-PINGOTHER", "Content-Type"}
        )
public class BatchController {
    private static Logger log = Logger.getLogger(BatchController.class);
    private BusinessDelegate businessDelegate;
    @Autowired
    public void setBusinessDelegate(BusinessDelegate businessDelegate) { this.businessDelegate = businessDelegate; }

        /**
        * Request for new batch to be created
        * @param batch
        * @return
        */
    @RequestMapping(value = "batch/create",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> createBatch(@RequestBody @Valid Batch batch) {
        ResponseEntity<Serializable> returnEntity;
        try {
            businessDelegate.createBatch(batch);
            returnEntity =  new ResponseEntity(HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            log.error("Runtime Exception.", e);
        }
        return returnEntity;
    }

    /**
     * Request to get all batches
     * @return
     */
    @RequestMapping(value = "batch/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<List<Batch>> getAllBatches(){
        ResponseEntity<List<Batch>> returnEntity;
        try{
            List<Batch> batches = businessDelegate.getAllBatch();
            if(batches == null)
                returnEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            else
                returnEntity = new ResponseEntity<>(batches, HttpStatus.OK);
        }catch(RuntimeException e){
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            log.error("Runtime Exception.", e);
        }
        return returnEntity;
    }

    /**
     * Request to get all batches by Trainer id
     * @param id
     * @return
     */
    @RequestMapping(value = "batch/byTrainerId/{id}",
                method = RequestMethod.GET,
                consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<List<Batch>> getTrainerBatch(@PathVariable("id") Integer id){
        ResponseEntity<List<Batch>> returnEntity;
        try{
            List<Batch> batches = businessDelegate.getTrainerBatch(id);
            if(batches == null)
                returnEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            else
                returnEntity = new ResponseEntity<>(batches, HttpStatus.OK);
        }catch(RuntimeException e){
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            log.error("Runtime Exception.", e);
        }
        return returnEntity;
    }

    /**
     * Request to get all active batches
     * @return
     */
    @RequestMapping(value = "batch/current",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<List<Batch>> getCurrentBatch(){
        ResponseEntity<List<Batch>> returnEntity;
        try{
            List<Batch> batches = businessDelegate.getCurrentBatch();
            if(batches == null)
                returnEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            else
                returnEntity = new ResponseEntity<>(batches, HttpStatus.OK);
        }catch(RuntimeException e){
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            log.error("Runtime Exception.", e);
        }
        return returnEntity;
    }

    /**
     * Request to get active batches by Trainer id
     * @param id
     * @return
     */
    @RequestMapping(value = "batch/current/{id}",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<List<Batch>> getCurrentBatch(@PathVariable("id") Integer id){
        ResponseEntity<List<Batch>> returnEntity;
        try{
            List<Batch> batches = businessDelegate.getCurrentBatch(id);
            if(batches == null)
                returnEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            else
                returnEntity = new ResponseEntity<>(batches, HttpStatus.OK);
        }catch(RuntimeException e){
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            log.error("Runtime Exception.", e);
        }
        return returnEntity;
    }

    /**
     * Request to get a single batch by id
     * @param id
     * @return
     */
    @RequestMapping(value = "batch/byId/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Batch> getBatch(@PathVariable("id") int id){
        ResponseEntity<Batch> returnEntity;
        try{
            Batch batch = businessDelegate.getBatch(id);
            if(batch == null)
            returnEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
        else
            returnEntity = new ResponseEntity<Batch>(batch, HttpStatus.OK);
    }catch(RuntimeException e){
        returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        log.error("Runtime Exception.", e);
    }
        return returnEntity;
    }

    /**
     * Request to update a batch
     * @param batch
     * @return
     */
    @RequestMapping(value = "batch/update",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Batch> updateBatch(@RequestBody @Valid Batch batch){
        ResponseEntity<Batch> returnEntity;
        try{
            businessDelegate.updateBatch(batch);
            returnEntity = new ResponseEntity(HttpStatus.OK);
        }catch(RuntimeException e){
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            log.error("Runtime Exception.", e);
        }
        return returnEntity;
    }

    /**
     * Request to delete a batch
     * @param batch
     * @return
     */
    @RequestMapping(value = "batch/delete",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Batch> deleteBatch(@RequestBody @Valid Batch batch){
        ResponseEntity<Batch> returnEntity;
        try{
            businessDelegate.deleteBatch(batch);
            returnEntity = new ResponseEntity(HttpStatus.OK);
        }catch(RuntimeException e){
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
            log.error("Runtime Exception.", e);
        }
        return returnEntity;
    }
}
