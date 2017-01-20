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

@RestController
@CrossOrigin(origins = {"*"},
            methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS},
            allowedHeaders = {"X-PINGOTHER", "Content-Type"},
            maxAge = 10)
public class TraineeController {

    private BusinessDelegate businessDelegate;
    @Autowired
    public void setBusinessDelegate(BusinessDelegate businessDelegate) { this.businessDelegate = businessDelegate; }

    /**
     * Greate a new trainee by making a PUT request to the URL
     * @param trainee trainee to put
     * @return Response with appropriate status
     */
    @RequestMapping(value = "trainees/new",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> createTrainee(@RequestBody @Valid Trainee trainee) {
        ResponseEntity<Serializable> returnEntity;
        try {
            businessDelegate.createTrainee(trainee);
            returnEntity =  new ResponseEntity<Serializable>(HttpStatus.CREATED);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Serializable>(HttpStatus.BAD_REQUEST);
        }
        return returnEntity;
    }

    /**
     * Update a trainee by making a POST request to the URL
     * @param trainee trainee to update (with updated fields)
     * @return Response with appropriate status
     */
    @RequestMapping(value = "trainees/update",
                    method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> updateTrainee(@RequestBody @Valid Trainee trainee) {
        ResponseEntity<Serializable> returnEntity;

        try {
            businessDelegate.updateTrainee(trainee);
            returnEntity = new ResponseEntity<Serializable>(HttpStatus.OK);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Serializable>(HttpStatus.BAD_REQUEST);
        }

        return returnEntity;
    }

    /**
     * Delete a trainee by making a DELETE request to the URL
     * @param trainee trainee to delete
     * @return Response with appropriate status
     */
    @RequestMapping(value = "trainees/delete",
                    method = RequestMethod.DELETE,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Serializable> deleteTrainee(@RequestBody @Valid Trainee trainee) {
        ResponseEntity<Serializable> returnEntity;

        try {
            businessDelegate.deleteTrainee(trainee);
            returnEntity = new ResponseEntity<Serializable>(HttpStatus.OK);
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Serializable>(HttpStatus.BAD_REQUEST);
        }

        return returnEntity;
    }

    /**
     * Get a trainee by id by making a GET request to the URL
     * @param id id as part of URL
     * @return Response with trainee object and/or appropriate status
     */
    @RequestMapping(value = "trainees/byid/{identifier}",
                    method = RequestMethod.GET,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Trainee> getTraineeById(@PathVariable("identifier") int id) {
        ResponseEntity<Trainee> returnEntity;

        try {
            Trainee result = businessDelegate.getTrainee(id);

            if (result == null) {
                returnEntity = new ResponseEntity<Trainee>(result, HttpStatus.NOT_FOUND);
            }
            else {
                returnEntity = new ResponseEntity<Trainee>(result, HttpStatus.OK);
            }
        }
        catch (RuntimeException e) {
                returnEntity = new ResponseEntity<Trainee>(HttpStatus.BAD_REQUEST);
        }

        return returnEntity;
    }

    /**
     * Get trainee by name by making a GET request to the URL
     * @param name name as part of URL
     * @return Response with trainee object and/or status
     */
    @RequestMapping(value = "trainees/byname/{identifier}",
                    method = RequestMethod.GET,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Trainee> getTraineeByName(@PathVariable("identifier") String name) {
        ResponseEntity<Trainee> returnEntity;

        try {
            Trainee result = businessDelegate.getTrainee(name);

            if (result == null) {
                returnEntity = new ResponseEntity<Trainee>(result, HttpStatus.NOT_FOUND);
            }
            else {
                returnEntity = new ResponseEntity<Trainee>(result,  HttpStatus.OK);
            }
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<Trainee>(HttpStatus.BAD_REQUEST);
        }

        return returnEntity;
    }

    /**
     * Get a list of trainees in a batch by making a GET request to the URL
     * @param batchId id as part of URL
     * @return Response with list of trainee objects and/or status
     */
    @RequestMapping(value = "trainees/bybatch/{identifier}",
                    method = RequestMethod.GET,
                    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
                    produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Trainee>> getTraineesForBatch(@PathVariable("identifier") int batchId) {
        ResponseEntity<List<Trainee>> returnEntity;

        try {
            List<Trainee> result = businessDelegate.getTraineesInBatch(batchId);

            if (result == null) {
                returnEntity = new ResponseEntity<List<Trainee>>(result, HttpStatus.NOT_FOUND);
            }
            else {
                returnEntity = new ResponseEntity<List<Trainee>>(result, HttpStatus.OK);
            }
        }
        catch (RuntimeException e) {
            returnEntity = new ResponseEntity<List<Trainee>>(HttpStatus.BAD_REQUEST);
        }

        return returnEntity;
    }
}