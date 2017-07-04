package com.revature.caliber.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.security.models.SalesforceUser;
import com.revature.caliber.services.SalesforceService;

@RestController
public class SalesforceController {

	private static final Logger log = Logger.getLogger(SalesforceController.class);
	
	@Autowired
	private SalesforceService salesforceService;

	public void setSalesforceService(SalesforceService salesforceService) {
		this.salesforceService = salesforceService;
	}
	
	@RequestMapping(value="/salesforce/test", method=RequestMethod.GET)
	public String salesforceJson(){
		return salesforceService.getAllSalesforceBatches();
	}
	
	@RequestMapping(value="/salesforce/token", method=RequestMethod.GET)
	public String getSalesforceToken(){
		return ((SalesforceUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getSalesforceToken().getAccessToken();
	}
	
	/**
	 * Gets all current batches from salesforce
	 * 
	 * @return the all batches
	 */
	@RequestMapping(value ="/all/batch/importget", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	// @PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<List<Batch>> getAllSalesforceBatches() {
		log.info("Fetching all salesforce batches");
		List<Batch> batches = salesforceService.findAllBatches();
		return new ResponseEntity<>(batches, HttpStatus.OK);
	}
	
}
