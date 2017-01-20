package com.revature.caliber.training.data;

import java.util.List;

import com.revature.caliber.training.beans.Trainer;

/**
 * DAO interface for trainer object
 */
public interface TrainerDAO {

	void createTrainer(Trainer trainer);
	Trainer getTrainer(Integer id);
	Trainer getTrainer(String name);
	List<Trainer> getAllTrainers();
	void updateTrainer(Trainer trainer);
	void deleteTrainer(Trainer trainer);
}
