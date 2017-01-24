package com.revature.caliber.gateway;

import java.util.List;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;

/**
 * Gathers data from appropriate services and
 * combines the model to deliver to the view.
 * 
 * Reduces complexity compared to calling each
 * service individually throughout the application.
 *
 */
public interface ApiGateway {

	/**
	 * Get all Batches for a given Trainer.
	 * @param trainer
	 * @return
	 */
	public List<Batch> getBatches(Trainer trainer);
	
	/**
	 * Get all Batches in the company. Useful for
	 * VP and QC roles to aggregate company data.
	 * 
	 * @return
	 */
	//TODO public List<Batch> getBatches();
	
	/**
	 * Get all Batches currently within the window
	 * of their training period. 
	 * @return
	 */
	// TODO public List<Batch> getCurrentBatches();
	
	
}
