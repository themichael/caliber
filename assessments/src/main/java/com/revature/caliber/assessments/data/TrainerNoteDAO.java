package com.revature.caliber.assessments.data;

import java.util.List;

import com.revature.caliber.assessments.beans.TrainerNote;

public interface TrainerNoteDAO {
	
	//Create a TrainerNote based on the ID of the trainer
	void createTrainerNote(int trainerId);
	
	//Get a List of all the TrainerNotes ever written by a specific Trainer 
	List<TrainerNote> getAllTrainerNotesByTrainer(int trainerId);
	
	//Get a TrainerNote from a Trainer for a specific week
	TrainerNote getTrainerNoteForWeek(int trainerId, int weekId);
	
}
