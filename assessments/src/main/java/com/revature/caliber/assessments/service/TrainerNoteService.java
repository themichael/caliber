package com.revature.caliber.assessments.service;

import java.util.HashSet;

import com.revature.caliber.assessments.beans.TrainerNote;

public interface TrainerNoteService {
	void createTrainerNote(int trainerId);
	HashSet<TrainerNote> getAllNotesByTrainer(int trainerId);
	TrainerNote getTrainerNoteForWeek(int trainerId, int weekId);

}
