package com.revature.caliber.training.service;

import com.revature.caliber.training.beans.Trainee;

import java.util.List;

/**
 * Business delegate pattern (just in case)
 */
public interface BusinessDelegate {

    //Trainee methods
    public void createTrainee(Trainee trainee);
    public void updateTrainee(Trainee trainee);
    public Trainee getTrainee(Integer id);
    public Trainee getTrainee(String name);
    public List<Trainee> getTraineesInBatch(Integer batchId);
    public void deleteTrainee(Trainee trainee);
    //end of Trainee methods
}
