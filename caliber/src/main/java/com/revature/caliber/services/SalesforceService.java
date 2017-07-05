package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
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
	 * @return List of Batches
	 */
	public List<Batch> getAllRelevantBatches(){
		log.debug("Find all current batches by year");
		//TODO - Change Fake data to All data
		return salesforceDAO.getFakeReleventBatches();
	}
	
	
	/**
	 * FIND ALL TRAINEES FROM A SALESFORCE BATCHE
	 * @return List of Trainee's from a batch
	 */
	
	public List<Trainee> getBatchDetails(String resourceId){
		log.debug("Find all current batches by resource id");
		return salesforceDAO.getBatchDetails(resourceId);
	}
	
}
