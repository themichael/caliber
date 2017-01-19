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

@RestController
public class BatchController {
    private BusinessDelegate businessDelegate;
    //@Autowired
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
            returnEntity =  new ResponseEntity(HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
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
            if(batches == null)
                returnEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            else
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
        try{
            List<Batch> batches = businessDelegate.getTrainerBatch(name);
            if(batches == null)
                returnEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            else
                returnEntity = new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);
        }catch(RuntimeException e){
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @ResponseBody
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
                returnEntity = new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);
        }catch(RuntimeException e){
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @ResponseBody
    @RequestMapping(value = "batch/current/{name}",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<List<Batch>> getCurrentBatch(@PathVariable("name") String name){
        ResponseEntity<List<Batch>> returnEntity;
        try{
            List<Batch> batches = businessDelegate.getCurrentBatch(name);
            if(batches == null)
                returnEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            else
                returnEntity = new ResponseEntity<List<Batch>>(batches, HttpStatus.OK);
        }catch(RuntimeException e){
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @ResponseBody
    @RequestMapping(value = "batch/byId/{id}",
            method = RequestMethod.GET,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public HttpEntity<Batch> getBatch(@PathVariable("id") int id){
        ResponseEntity<Batch> returnEntity;
        try{
            Batch batch = businessDelegate.getBatch(id);
            if(batch == null)
                returnEntity = new ResponseEntity(HttpStatus.NOT_FOUND);
            else
                returnEntity = new ResponseEntity<Batch>(batch, HttpStatus.OK);
        }catch(RuntimeException e){
            returnEntity = new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @ResponseBody
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
        }
        return returnEntity;
    }

    @ResponseBody
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
        }
        return returnEntity;
    }
}
