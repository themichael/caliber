package com.revature.caliber.training.web.controllers;

import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.service.BusinessDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by illyasviel on 1/18/17.
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