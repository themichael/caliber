package com.revature.caliber.training.web.controllers;


import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.service.BusinessDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public class BatchController {
    private BusinessDelegate businessDelegate;
    @Autowired
    public void setBusinessDelegate(BusinessDelegate businessDelegate) { this.businessDelegate = businessDelegate; }

    @ResponseBody
    @RequestMapping(value = "batch/new",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> createTrainee(@RequestBody @Valid Batch batch) {
        ResponseEntity<Serializable> returnEntity;
        try {
            businessDelegate.createBatch(batch);
            returnEntity =  new ResponseEntity<Serializable>(HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Serializable>(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @ResponseBody
    @RequestMapping(value = "batch/all",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<List<Batch>> getAllBatches(){
        ResponseEntity<List<Batch>> returnEntity;
        try{
            List<Batch> batches = businessDelegate.getAllBatch();
            returnEntity = new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);
        }catch(RuntimeException e){
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @ResponseBody
    @RequestMapping(value = "batch/byTrainerName/{name}",
                method = RequestMethod.GET,
                consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<List<Batch>> getTrainerBatch(@PathVariable("name") String name){
        ResponseEntity<List<Batch>> returnEntity;
        return null;
    }
}
