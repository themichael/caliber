package com.revature.caliber.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
	
	/**
	 * TODO delete me.
	 * Used to grab access_token for running local tests of Salesforce API
	 * @return
	 */
	@RequestMapping(value="/salesforce/token", method=RequestMethod.GET)
	public String getSalesforceToken(){
		log.info("Getting access_token for testing purposes only");
		return ((SalesforceUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
				.getSalesforceToken().getAccessToken();
	}
	
}
