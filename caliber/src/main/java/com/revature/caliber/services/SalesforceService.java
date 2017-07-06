package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.SalesforceDAO;

@Service
public class SalesforceService {

	private static final Logger log = Logger.getLogger(SalesforceService.class);

	@Autowired
	private SalesforceDAO salesforceDAO;
	@Autowired
	private BatchDAO batchDAO;

	public void setSalesforceDAO(SalesforceDAO salesforceDAO) {
		this.salesforceDAO = salesforceDAO;
	}

	/**
	 * FIND ALL CURRENT SALESFORCE BATCHES
	 * 
	 * @return List of Batches
	 */
	public List<Batch> getAllRelevantBatches() {
		log.debug("Find all current batches by year");
		// TODO - Change Fake data to All data
		List<Batch> allSalesForceBatches = salesforceDAO.getFakeReleventBatches();
		List<Batch> allCaliberBatches = batchDAO.findAll();

		for (int sfIndex = 0; sfIndex < allSalesForceBatches.size(); sfIndex++) {
			String sfResourceId = allSalesForceBatches.get(sfIndex).getResourceId();
			for (int cIndex = 0; cIndex < allCaliberBatches.size(); cIndex++) {
				String cResourceId = allCaliberBatches.get(cIndex).getResourceId();
				if (cResourceId == null) {
					continue;
				}
				if (cResourceId.equals(sfResourceId)) {
					allSalesForceBatches.remove(sfIndex--);
					break;
				}
			}
		}

		return allSalesForceBatches;
	}

	
	/**
	 * FIND ALL TRAINEES
	 * @return List of Trainees
	 */
	
	public List<Trainee> getAllTraineesFromBatch(String resourceId){
		log.debug("Find all trainees");
		return salesforceDAO.getFakeBatchDetails(resourceId);
	}
	

}
