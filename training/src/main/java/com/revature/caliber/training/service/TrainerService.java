package com.revature.caliber.training.service;

import java.util.List;

import com.revature.caliber.training.beans.Trainer;

public interface TrainerService {
	void createTrainer(Trainer trainer);
	Trainer getTrainer(Integer id);
	List<Trainer> getTrainer(String name);
	List<Trainer> getAllTrainers();
	void updateTrainer(Trainer trainer);
	void deleteTrainer(Trainer trainer);
}
