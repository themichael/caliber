package com.revature.caliber.training.service;

import com.revature.caliber.training.beans.Trainee;

import java.util.List;

/**
 * Service for trainee (just delegation to facade)
 * methods are the same as in TraineeDAO
 * @see com.revature.caliber.training.data.TraineeDAO
 */
public interface TraineeService {
    void createTrainee(Trainee trainee);
    void updateTrainee(Trainee trainee);
    Trainee getTrainee(Integer id);
    Trainee getTrainee(String name);
    List<Trainee> getTraineesInBatch(Integer batchId);
    void deleteTrainee(Trainee trainee);
}
