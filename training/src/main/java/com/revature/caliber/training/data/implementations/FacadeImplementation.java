package com.revature.caliber.training.data.implementations;

import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.data.Facade;
import com.revature.caliber.training.data.TraineeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Our implementation of Facade interface.
 */

@Component(value = "trainingFacadeImplementation")
public class FacadeImplementation implements Facade {

    TraineeDAO traineeDAO;
    @Autowired
    public void setTraineeDAO(TraineeDAO traineeDAO) { this.traineeDAO = traineeDAO; }

    //Trainee
    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public void createTrainee(Trainee trainee) { traineeDAO.createTrainee(trainee); }

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public void updateTrainee(Trainee trainee){ traineeDAO.updateTrainee(trainee); }

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public Trainee getTrainee(Integer id){ return traineeDAO.getTrainee(id); }

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public Trainee getTrainee(String name){ return traineeDAO.getTrainee(name); }

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public List<Trainee> getTraineesInBatch(Integer batchId){ return traineeDAO.getTraineesInBatch(batchId); }

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public void deleteTrainee(Trainee trainee){ traineeDAO.deleteTrainee(trainee); }
    //end of trainee
}
