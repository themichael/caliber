package com.revature.caliber.training.service;

import java.util.List;

import com.revature.caliber.training.beans.Trainer;

public interface TrainerService {
	void createTrainer(Trainer trainer);
	Trainer getTrainer(Integer id);
	Trainer getTrainer(String email);
	List<Trainer> getAllTrainers();
	void updateTrainer(Trainer trainer);
}
