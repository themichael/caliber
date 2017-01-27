package com.revature.caliber.controllers;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.gateway.impl.ApiGatewayImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * The type Qc batch controller.
 */
@RestController
@RequestMapping("/qc")
public class QCBatchController {
    @RequestMapping(value = "/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<Batch>> getAllBatches() {
        return new ResponseEntity<>(new ApiGatewayImpl().getAllBatches(), HttpStatus.OK);
    }
    @RequestMapping(value = "/grade/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createGrade(@RequestBody Grade grade) {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        apiGateway.createGrade(grade);
        return new ResponseEntity(HttpStatus.OK);
    }
    @RequestMapping(value = "/grade/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateGrade(@RequestBody Grade grade) {
        ApiGatewayImpl apiGateway = new ApiGatewayImpl();
        apiGateway.updateGrade(grade);
        return new ResponseEntity(HttpStatus.OK);
    }

}
