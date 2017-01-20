package com.revature.caliber.assessments.service;

import java.util.List;

import com.revature.caliber.assessments.beans.TrainerNote;

public interface TrainerNoteService {
	
	void createTrainerNote(int trainerId);
	
	List<TrainerNote> getAllNotesByTrainer(int trainerId);
	
	TrainerNote getTrainerNoteForWeek(int trainerId, int weekId);

}
