package com.revature.caliber.training.data;

import com.revature.caliber.training.beans.Trainee;

import java.util.List;

/**
 * DAO interface for trainee object
 */
public interface TraineeDAO {

    void createTrainee(Trainee trainee);
    void updateTrainee(Trainee trainee);
    Trainee getTrainee(Integer id);
    Trainee getTrainee(String name);
    List<Trainee> getTraineesInBatch(Integer batchId);
    void deleteTrainee(Trainee trainee);
}
