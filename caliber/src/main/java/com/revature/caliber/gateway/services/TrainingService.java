package com.revature.caliber.gateway.services;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;

import java.util.List;

public interface TrainingService {

    /**
     * Create a new batch
     *
     * @param batch new batch
     */
    void createBatch(Batch batch);

    /**
     * Get all batches
     *
     * @return A list of all batches
     */
    List<Batch> allBatch();

    /**
     * Get all Batches for a given Trainer.
     *
     * @param trainer - The trainer for whom all of his current batches are being retrieved
     * @return A list of this trainer's current batches
     * @param id
     * @return
     */
    List<Batch> getBatches(Integer id);

    /**
     * Get all current Batches
     *
     * @return A list of all the current batches
     */
    List<Batch> currentBatch();

    /**
     * Get all current Batches for a given Trainer
     *
     * @param trainer - The trainer requesting a list of all his current batches
     * @return A list of the trainers current batches
     */
    List<Batch> currentBatch(Trainer trainer);

    /**
     * Get a batch by ID
     *
     * @param id - The id of the batch being retreived
     * @return The batch that is being retrieved
     */
    Batch getBatch(Integer id);

    /**
     * Update a Batch
     *
     * @param batch - The batch being updated
     */
    void updateBatch(Batch batch);

    /**
     * Delete a Batch
     *
     * @param batch - The batch being deleted
     */
    void deleteBatch(Batch batch);

    //Trainee

    /**
     * Creates new trainee
     *
     * @param trainee trainee to create
     */
    void createTrainee(Trainee trainee);

    /**
     * Update trainee's info
     *
     * @param trainee trainee to update (with new info)
     */
    void updateTrainee(Trainee trainee);

    /**
     * Get trainee by id.
     *
     * @param id id of trainee to get
     * @return Trainee object, or null if trainee with id doesn't exist
     */
    Trainee getTrainee(Integer id);

    /**
     * Get trainee by full name
     *
     * @param email email of trainee to get (conversions needed (server side restrictions): '@' -> "%40", '.' -> "_dot_")
     * @return Trainee object or null if trainee with name doesn't exist
     */
    Trainee getTrainee(String email);

    /**
     * Get list of trainees for a certain batch
     *
     * @param batchId id of the batch
     * @return list of trainees or an empty list if there is no batch (null?)
     */
    List<Trainee> getTraineesInBatch(Integer batchId);

    /**
     * Delete a trainee
     *
     * @param trainee trainee to delete
     */
    void deleteTrainee(Trainee trainee);
    //End of Trainee

    //Trainer

    /**
     * Creates new trainer
     *
     * @param trainer trainer to create
     */
    void createTrainer(Trainer trainer);

    /**
     * Gets a trainer by id
     *
     * @param id: id of the trainer
     * @return Trainer object
     */
    Trainer getTrainer(Integer id);

    /**
     * Gets a trainer by email
     *
     * @param email: email of the trainer
     * @return A Trainer object
     */
    Trainer getTrainer(String email);

    /**
     * Gets a list of all trainers
     *
     * @return A list of Trainer objects
     */
    List<Trainer> getAllTrainers();

    /**
     * Updates a trainer
     *
     * @param trainer: the trainer object to update
     */
    void updateTrainer(Trainer trainer);

    //End of Trainer
}
