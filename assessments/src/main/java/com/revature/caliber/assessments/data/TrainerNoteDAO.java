package com.revature.caliber.assessments.data;

import java.util.HashSet;

import com.revature.caliber.assessments.beans.TrainerNote;

public interface TrainerNoteDAO {
	void createTrainerNote(int trainerId);
	HashSet<TrainerNote> getAllTrainerNotesByTrainer(int trainerId);
	TrainerNote getTrainerNoteForWeek(int trainerId, int weekId);
	
}
