package com.revature.caliber.training.web.controllers;

import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.service.BusinessDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Trainee Controller
 */
public class TraineeController {

    private BusinessDelegate businessDelegate;
    @Autowired
    public void setBusinessDelegate(BusinessDelegate businessDelegate) { this.businessDelegate = businessDelegate; }

    @ResponseBody
    @RequestMapping(value = "trainees/new",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> createTrainee(@RequestBody @Valid Trainee trainee) {
        ResponseEntity<Serializable> returnEntity;
        try {
            businessDelegate.createTrainee(trainee);
            returnEntity =  new ResponseEntity<Serializable>(corsHeaders(), HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Serializable>(corsHeaders(), HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    @ResponseBody
    @RequestMapping(value = "trainees/update",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> updateTrainee(@RequestBody @Valid Trainee trainee) {
        ResponseEntity<Serializable> returnEntity;

        try {
            businessDelegate.updateTrainee(trainee);
            returnEntity = new ResponseEntity<Serializable>(corsHeaders(), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Serializable>(corsHeaders(), HttpStatus.BAD_REQUEST);
        }

        return returnEntity;
    }

    @ResponseBody
    @RequestMapping(value = "trainees/delete",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> deleteTrainee(@RequestBody @Valid Trainee trainee) {
        ResponseEntity<Serializable> returnEntity;

        try {
            businessDelegate.deleteTrainee(trainee);
            returnEntity = new ResponseEntity<Serializable>(corsHeaders(), HttpStatus.OK);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Serializable>(corsHeaders(), HttpStatus.BAD_REQUEST);
        }

        return returnEntity;
    }

    @ResponseBody
    @RequestMapping(value = "trainees/byid/{identifier}",
                    method = RequestMethod.GET,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Trainee> getTraineeById(@PathVariable("identifier") int id) {
        ResponseEntity<Trainee> returnEntity;

        try {
            Trainee result = businessDelegate.getTrainee(id);

            if (result == null) {
                returnEntity = new ResponseEntity<Trainee>(result, corsHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                returnEntity = new ResponseEntity<Trainee>(result, corsHeaders(), HttpStatus.OK);
            }
        }
        catch (RuntimeException e) {
                returnEntity = new ResponseEntity<Trainee>(null, corsHeaders(), HttpStatus.BAD_REQUEST);
        }

        return returnEntity;
    }

    @ResponseBody
    @RequestMapping(value = "trainees/byname/{identifier}",
                    method = RequestMethod.GET,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Trainee> getTraineeByName(@PathVariable("identifier") String name) {
        ResponseEntity<Trainee> returnEntity;

        try {
            Trainee result = businessDelegate.getTrainee(name);

            if (result == null) {
                returnEntity = new ResponseEntity<Trainee>(result, corsHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                returnEntity = new ResponseEntity<Trainee>(result, corsHeaders(), HttpStatus.OK);
            }
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Trainee>(null, corsHeaders(), HttpStatus.BAD_REQUEST);
        }

        return returnEntity;
    }

    @ResponseBody
    @RequestMapping(value = "trainees/bybatch/{identifier}",
                    method = RequestMethod.GET,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Trainee>> getTraineesForBatch(@PathVariable("identifier") int batchId) {
        ResponseEntity<List<Trainee>> returnEntity;

        try {
            List<Trainee> result = businessDelegate.getTraineesInBatch(batchId);

            if (result == null) {
                returnEntity = new ResponseEntity<List<Trainee>>(result, corsHeaders(), HttpStatus.NOT_FOUND);
            }
            else {
                returnEntity = new ResponseEntity<List<Trainee>>(result, corsHeaders(), HttpStatus.OK);
            }
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<List<Trainee>>(null, corsHeaders(), HttpStatus.BAD_REQUEST);
        }

        return returnEntity;
    }


    public MultiValueMap<String, String> corsHeaders(){
        MultiValueMap<String, String> headers =
                new LinkedMultiValueMap<String, String>();
        headers.put("Access-Control-Allow-Origin",
                Arrays.asList(new String[]{"*"}));
        headers.put("Access-Control-Allow-Methods",
                Arrays.asList(new String[]{"POST", "GET", "OPTIONS"}));
        headers.put("Access-Control-Allow-Headers",
                Arrays.asList(new String[]{"X-PINGOTHER", "Content-Type"}));
        headers.put("Access-Control-Max-Age",
                Arrays.asList(new String[]{"10"}));
        return headers;
    }
}