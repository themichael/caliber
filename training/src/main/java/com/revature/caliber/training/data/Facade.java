package com.revature.caliber.training.data;

import java.util.List;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Category;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.beans.Week;

/**
 * Facade interface for data tier.
 */
public interface Facade {

	// Trainee methods, see TraineeDAO interface
	public void createTrainee(Trainee trainee);

	public void updateTrainee(Trainee trainee);

	public Trainee getTrainee(Integer id);

	public Trainee getTrainee(String email);

	public List<Trainee> getTraineesInBatch(Integer batchId);

	public void deleteTrainee(Trainee trainee);
	// end of Trainee methods

	// Batch methods
	public void createBatch(Batch batch);

	public List<Batch> getAllBatch();

	public List<Batch> getTrainerBatch(Integer id);

	public List<Batch> getCurrentBatch();

	public List<Batch> getCurrentBatch(Integer id);

	public Batch getBatch(Integer id);

	public void updateBatch(Batch batch);

	public void deleteBatch(Batch batch);

	// Category methods
	public Category getCategory(int categoryId);

	public List<Category> getAllCategories();

	// Trainer methods
	public void createTrainer(Trainer trainer);

	public Trainer getTrainer(Integer id);

	public Trainer getTrainer(String email);

	public List<Trainer> getAllTrainers();

	public void updateTrainer(Trainer trainer);

	
	//Week methods
	List<Week> getAllWeeks();
	List<Week> getWeekByBatchId(int batchId);
	List<Week> getWeekByWeekNumber(int weekNumber);
	void createWeek(Week newWeek);
	//end of week methods
	
	
}
