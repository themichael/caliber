package com.revature.caliber.gateway;

import java.util.List;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
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
	 * Create a new batch
	 * @param batch
	 */
	public void createBatch(Batch batch);

	/**
	 * Get all batches
	 * @return
	 */
	public List<Batch> allBatch();
	/**
	 * Get all Batches for a given Trainer.
	 * @param trainer
	 * @return
	 */
	public List<Batch> getBatches(Trainer trainer);

	/**
	 * Get all current Batches
	 * @return
	 */
	public List<Batch> currentBatch();

	/**
	 * Get all current Batches for a given Trainer
	 * @param trainer
	 * @return
	 */
	public List<Batch> currentBatch(Trainer trainer);

	/**
	 * Get a batch by ID
	 * @param id
	 * @return
	 */
	public Batch getBatch (Integer id);

	/**
	 * Update a Batch
	 * @param batch
	 */
	public void updateBatch(Batch batch);

	/**
	 * Delete a Batch
	 * @param batch
	 */
	public void deleteBatch(Batch batch);

	//Trainee
	/**
	 * Creates new trainee
	 * @param trainee trainee to create
	 */
	void createTrainee(Trainee trainee);

	/**
	 * Update trainee's info
	 * @param trainee trainee to update (with new info)
	 */
	void updateTrainee(Trainee trainee);

	/**
	 * Get trainee by id.
	 * @param id id of trainee to get
	 * @return Trainee object, or null if trainee with id doesn't exist
	 */
	Trainee getTrainee(Integer id);

	/**
	 * Get trainee by full name
	 * @param name name of trainee to get
	 * @return Trainee object or null if trainee with name doesn't exist
	 */
	Trainee getTrainee(String name);

	/**
	 * Get list of trainees for a certain batch
	 * @param batchId id of the batch
	 * @return list of trainees or an empty list if there is no batch (null?)
	 */
	List<Trainee> getTraineesInBatch(Integer batchId);

	/**
	 * Delete a trainee
	 * @param trainee trainee to delete
	 */
	void deleteTrainee(Trainee trainee);

	//end of Trainee Service
	
	
}
