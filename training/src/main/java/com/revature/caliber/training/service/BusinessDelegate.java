package com.revature.caliber.training.service;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.beans.Trainer;

import java.util.List;

/**
 * Business delegate pattern (just in case)
 */
public interface BusinessDelegate {

    //TraineeService methods
    public void createTrainee(Trainee trainee);
    public void updateTrainee(Trainee trainee);
    public Trainee getTrainee(Integer id);
    public Trainee getTrainee(String name);
    public List<Trainee> getTraineesInBatch(Integer batchId);
    public void deleteTrainee(Trainee trainee);
    //end of TraineeService

    //BatchService methods
    public void createBatch(Batch batch);
    public List<Batch> getAllBatch();
    public List<Batch> getTrainerBatch(Integer id);
    public List<Batch> getCurrentBatch();
    public List<Batch> getCurrentBatch(Integer id);
    public Batch getBatch(Integer id);
    public void updateBatch(Batch batch);
    public void deleteBatch(Batch batch);
    //end of BatchServices
    
    //TrainerService methods
    public void createTrainer(Trainer trainer);
    public Trainer getTrainer(Integer id);
    public List<Trainer> getTrainer(String name);
    public List<Trainer> getAllTrainers();
    public void updateTrainer(Trainer trainer);
    public void deleteTrainer(Trainer trainer);
    //end of TrainerService
}
