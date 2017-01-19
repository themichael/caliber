package com.revature.caliber.training.data;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Category;
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

    // Batch methods
    public void createBatch(Batch batch);
    public List<Batch> getAllBatch();
    public List<Batch> getTrainerBatch(String name);
    public List<Batch> getCurrentBatch();
    public List<Batch> getCurrentBatch(String name);
    public Batch getBatch(Integer id);
    public void updateBatch(Batch batch);
    public void deleteBatch(Batch batch);
    
    //Category methods
	public Category getCategory(String category);
	public List<Category> getAllCategories();


}
