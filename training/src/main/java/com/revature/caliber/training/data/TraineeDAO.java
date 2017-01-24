package com.revature.caliber.training.data;

import com.revature.caliber.training.beans.Trainee;

import java.util.List;

/**
 * DAO interface for trainee object
 */
public interface TraineeDAO {

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
}
