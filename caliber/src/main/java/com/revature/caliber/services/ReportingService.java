package com.revature.caliber.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.data.BatchDAO;

/**
 * Exclusively used to generate data for charts 
 * 
 * Provides logic concerning grade and aggregated data sets.
 * Application logic has no business being in a DAO
 * nor in a Controller. This is the ideal place for calculations 
 * 
 * @author Patrick Walsh
 *
 */
@Service
public class ReportingService {

	private final static Logger log = Logger.getLogger(ReportingService.class);
	private BatchDAO batchDAO;
	
	@Autowired
	public void setBatchDAO(BatchDAO batchDAO) {this.batchDAO = batchDAO;}
	
	
	
}
