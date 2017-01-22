package com.revature.caliber.training.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.service.BatchService;
import com.revature.caliber.training.service.BusinessDelegate;
import com.revature.caliber.training.service.TraineeService;

/**
 * Implementation for the Business Delegate
 */
@Service
public class BusinessDelegateImplementation implements BusinessDelegate{
	
    TraineeService traineeService;
    @Autowired
    public void setTraineeService(TraineeService traineeService) { this.traineeService = traineeService; }

    BatchService batchService;
    @Autowired
    public void setBatchService(BatchService batchService) {this.batchService = batchService;}

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
    public List<Batch> getTrainerBatch(String name) {return batchService.getTrainerBatch(name);}
    public List<Batch> getCurrentBatch() {return batchService.getCurrentBatch();}
    public List<Batch> getCurrentBatch(String name) {return batchService.getCurrentBatch(name);}
    public Batch getBatch(Integer id) {return batchService.getBatch(id);}
    public void updateBatch(Batch batch) {batchService.updateBatch(batch);}
    public void deleteBatch(Batch batch) {batchService.deleteBatch(batch);}
    //end of batch

}