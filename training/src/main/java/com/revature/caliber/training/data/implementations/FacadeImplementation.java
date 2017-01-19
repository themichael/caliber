package com.revature.caliber.training.data.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Category;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.data.BatchDAO;
import com.revature.caliber.training.data.CategoryDAO;
import com.revature.caliber.training.data.Facade;
import com.revature.caliber.training.data.TierDAO;
import com.revature.caliber.training.data.TraineeDAO;
import com.revature.caliber.training.data.TrainerDAO;
import com.revature.caliber.training.data.WeekDAO;

/**
 * Our implementation of Facade interface.
 */

@Component(value = "trainingFacadeImplementation")
public class FacadeImplementation implements Facade {

    TraineeDAO traineeDAO;
    BatchDAO batchDAO;
    CategoryDAO categoryDAO;
    TierDAO tierDAO;
    TrainerDAO trainerDAO;
    WeekDAO weekDAO;

    @Autowired
    public void setTraineeDAO(TraineeDAO traineeDAO) { this.traineeDAO = traineeDAO; }
    @Autowired
    public void setBatchDAO(BatchDAO batchDAO){ this.batchDAO = batchDAO; }

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

    //Batch

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public void createBatch(Batch batch) {batchDAO.createBatch(batch);}

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public List<Batch> getAllBatch() {return batchDAO.getAllBatch();}

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public List<Batch> getTrainerBatch(String name) {return batchDAO.getTrainerBatch(name);}

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public List<Batch> getCurrentBatch() {return batchDAO.getCurrentBatch();}

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public List<Batch> getCurrentBatch(String name) {return batchDAO.getCurrentBatch(name);}

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public Batch getBatch(Integer id) {return batchDAO.getBatch(id);}

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public void updateBatch(Batch batch) {batchDAO.updateBatch(batch);}

    @Transactional (propagation = Propagation.REQUIRES_NEW)
    public void deleteBatch(Batch batch) {batchDAO.deleteBatch(batch);}
    //end batch
	
    //Category
    @Transactional (propagation = Propagation.REQUIRES_NEW)
	public Category getCategory(int categoryId) {return categoryDAO.getCategory(categoryId);}
    
    @Transactional (propagation = Propagation.REQUIRES_NEW)
	public List<Category> getAllCategories() {return categoryDAO.getAllCategories();}
	//End Category
}
