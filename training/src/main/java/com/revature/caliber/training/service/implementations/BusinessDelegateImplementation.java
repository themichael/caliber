package com.revature.caliber.training.service.implementations;

import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.service.BusinessDelegate;
import com.revature.caliber.training.service.TraineeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Implementation for the Business Delegate
 */
@Component(value = "trainingBusinessDelegateImplementation")
public class BusinessDelegateImplementation implements BusinessDelegate {

    TraineeService traineeService;
    @Autowired
    public void setTraineeService(TraineeService traineeService) { this.traineeService = traineeService; }

    //trainee
    public void createTrainee(Trainee trainee) { traineeService.createTrainee(trainee); }
    public void updateTrainee(Trainee trainee) { traineeService.updateTrainee(trainee); }
    public Trainee getTrainee(Integer id) { return traineeService.getTrainee(id); }
    public Trainee getTrainee(String name) { return traineeService.getTrainee(name); }
    public List<Trainee> getTraineesInBatch(Integer batchId) { return traineeService.getTraineesInBatch(batchId); }
    public void deleteTrainee(Trainee trainee) { traineeService.deleteTrainee(trainee); }
    //end of trainee
}
