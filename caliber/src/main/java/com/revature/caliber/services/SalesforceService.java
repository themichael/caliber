package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.data.SalesforceDAO;

@Service
public class SalesforceService {

	private static final Logger log = Logger.getLogger(SalesforceService.class);
	
	@Autowired
	private SalesforceDAO salesforceDAO;

	public void setSalesforceDAO(SalesforceDAO salesforceDAO) {
		this.salesforceDAO = salesforceDAO;
	}
	
	/**
	 * FIND ALL CURRENT SALESFORCE BATCHES
	 * @return
	 */
	
	// TODO  JESSICA
	public String getAllSalesforceBatches(){
		log.debug("Find all current salesforce batches");
		return salesforceDAO.getAllBatches();
	}
	
	public List<Batch> getAllRelevantSalesforceBatches(){
		log.debug("Find all current batches by year");
		return salesforceDAO.getAllRelevantBatches();
	}
	
	public Batch getSalesforceBatchByResourceId(String resourceId){
		log.debug("Find all current batches by resource id");
		return salesforceDAO.getBatchDetails(resourceId);
	}

	public List<Batch> findAllBatches() {
		log.debug("Find all current batches");
		return null;
	}
	
}
