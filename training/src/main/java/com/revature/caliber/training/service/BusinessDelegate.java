package com.revature.caliber.training.service;

import java.util.List;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Category;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.beans.Week;

/**
 * Business delegate pattern (just in case)
 */
public interface BusinessDelegate {

	// TraineeService methods
	public void createTrainee(Trainee trainee);

	public void updateTrainee(Trainee trainee);

	public Trainee getTrainee(Integer id);

	public Trainee getTrainee(String email);

	public List<Trainee> getTraineesInBatch(Integer batchId);

	public void deleteTrainee(Trainee trainee);
	// end of TraineeService

	// BatchService methods
	public void createBatch(Batch batch);

	public List<Batch> getAllBatch();

	public List<Batch> getTrainerBatch(Integer id);

	public List<Batch> getCurrentBatch();

	public List<Batch> getCurrentBatch(Integer id);

	public Batch getBatch(Integer id);

	public void updateBatch(Batch batch);

	public void deleteBatch(Batch batch);
	// end of BatchServices

	// TrainerService methods
	public void createTrainer(Trainer trainer);

	public Trainer getTrainer(Integer id);

	public Trainer getTrainer(String email);

	public List<Trainer> getAllTrainers();

	public void updateTrainer(Trainer trainer);
	// end of TrainerService

	// CategoryService methods
	public Category getCategory(int categoryId);

	public List<Category> getAllCategories();
	
    //Week Service
	public List<Week> getAllWeeks();
	public List<Week> getWeekByBatchId(int batchId);
	public List<Week> getWeekByWeekNumber(int weekNumber);
	public void createWeek(Week newWeek);

}
