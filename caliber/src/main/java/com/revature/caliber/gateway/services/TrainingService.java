package com.revature.caliber.gateway.services;

import java.util.List;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;

public interface TrainingService{
	/**
	 * Get all Batches for a given Trainer.
	 * @param trainer
	 * @return
	 */
	public List<Batch> getBatches(Trainer trainer);
}
