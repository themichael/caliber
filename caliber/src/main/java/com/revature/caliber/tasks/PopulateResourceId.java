package com.revature.caliber.tasks;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;

public class PopulateResourceId {
	
	private static final Logger log=Logger.getLogger(PopulateResourceId.class);
	
	private PopulateResourceId() {
		super();
	}
	
	public static boolean getBatchResourceId(Batch caliberBatch,List<Batch> salesforceBatches) {
		boolean batchUpdated = false;
		String cTrainingName = caliberBatch.getTrainingName();
		for(int sIndex=0;sIndex<salesforceBatches.size();sIndex++) {
			String sTrainingName = salesforceBatches.get(sIndex).getTrainingName();
			if(cTrainingName.equals(sTrainingName)) {
				caliberBatch.setResourceId(salesforceBatches.get(sIndex).getResourceId());
				log.info("Found ResourceId: "+caliberBatch.getResourceId());
				batchUpdated = true;
			}
		}
			
		return batchUpdated;
	}
	
	/* 
	 * 	Compare the set of Trainees in Caliber with the Trainees retrieved from Salesforce
	 * 	and update the Caliber information if a change occurred
	 * 
	 * 	Junit tests will fail unless you comment out all of the DAO update lines in the function
	 */
	public static boolean getTraineeResourceId(Trainee caliberTrainee,Set<Trainee> salesforceTrainees) {
		log.debug("Update Trainees");
		Iterator<Trainee> sIt = salesforceTrainees.iterator();
		String cTraineeEmail = caliberTrainee.getEmail();
		boolean traineeUpdated = false;
		
		while(sIt.hasNext()) {
			Trainee salesforceTrainee = sIt.next();
			String sTraineeEmail = salesforceTrainee.getEmail();
			if(cTraineeEmail.equals(sTraineeEmail)) {
				caliberTrainee.setResourceId(salesforceTrainee.getResourceId());
				log.info("Found ResourceId: "+caliberTrainee.getResourceId());
				traineeUpdated = true;
			}
		}
		return traineeUpdated;
	}
}
