package com.revature.caliber.training.data;

import com.revature.caliber.training.beans.Trainee;

import java.util.List;

/**
 * Facade interface for data tier.
 */
public interface Facade {

    public void createTrainee(Trainee trainee);
    public void updateTrainee(Trainee trainee);
    public Trainee getTrainee(Integer id);
    public Trainee getTrainee(String name);
    public List<Trainee> getTraineesInBatch(Integer batchId);
    public void deleteTrainee(Trainee trainee);

}
