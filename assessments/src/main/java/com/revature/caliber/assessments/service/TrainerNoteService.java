package com.revature.caliber.assessments.service;

import java.util.List;
import java.util.Set;

import com.revature.caliber.assessments.beans.TrainerNote;

public interface TrainerNoteService {
	
	void createTrainerNote(TrainerNote trainerNote);
	
	Set<TrainerNote> getAllNotesByTrainer(int trainerId);
	
	TrainerNote getTrainerNoteForWeek(int trainerId, int weekId);

	void updateTrainerNote(TrainerNote trainerNote);
}
