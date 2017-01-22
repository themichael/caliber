package com.revature.caliber.training.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.service.BatchService;
import com.revature.caliber.training.service.BusinessDelegate;
import com.revature.caliber.training.service.TraineeService;
import org.springframework.stereotype.Component;

import com.revature.caliber.training.service.TrainerService;

/**
 * Implementation for the Business Delegate
 */
@Component(value = "trainingBusinessDelegateImplementation")
public class BusinessDelegateImplementation implements BusinessDelegate {

    TraineeService traineeService;
    @Autowired
    public void setTraineeService(TraineeService traineeService) { this.traineeService = traineeService; }

    BatchService batchService;
    @Autowired
    public void setBatchService(BatchService batchService) {this.batchService = batchService;}
    
    TrainerService trainerService;
    @Autowired
    public void setTrainerService(TrainerService trainerService) {this.trainerService = trainerService;}
	//trainee
    public void createTrainee(Trainee trainee) { traineeService.createTrainee(trainee); }
    public void updateTrainee(Trainee trainee) { traineeService.updateTrainee(trainee); }
    public Trainee getTrainee(Integer id) { return traineeService.getTrainee(id); }
    public Trainee getTrainee(String name) { return traineeService.getTrainee(name); }
    public List<Trainee> getTraineesInBatch(Integer batchId) { return traineeService.getTraineesInBatch(batchId); }
    public void deleteTrainee(Trainee trainee) { traineeService.deleteTrainee(trainee); }
    //end of trainee

    // batch
    public void createBatch(Batch batch) {batchService.createBatch(batch);}
    public List<Batch> getAllBatch() {return batchService.getAllBatch();}
    public List<Batch> getTrainerBatch(Integer id) {return batchService.getTrainerBatch(id);}
    public List<Batch> getCurrentBatch() {return batchService.getCurrentBatch();}
    public List<Batch> getCurrentBatch(Integer id) {return batchService.getCurrentBatch(id);}
    public Batch getBatch(Integer id) {return batchService.getBatch(id);}
    public void updateBatch(Batch batch) {batchService.updateBatch(batch);}
    public void deleteBatch(Batch batch) {batchService.deleteBatch(batch);}
    //end of batch
    
    //trainer
    public void createTrainer(Trainer trainer) {trainerService.createTrainer(trainer);}
    public Trainer getTrainer(Integer id) {return trainerService.getTrainer(id);}
    public List<Trainer> getTrainer(String name) {return trainerService.getTrainer(name);}
    public List<Trainer> getAllTrainers() {return trainerService.getAllTrainers();}
    public void updateTrainer(Trainer trainer) {trainerService.updateTrainer(trainer);}
    public void deleteTrainer(Trainer trainer) {trainerService.deleteTrainer(trainer);}
    //end of trainer
}
