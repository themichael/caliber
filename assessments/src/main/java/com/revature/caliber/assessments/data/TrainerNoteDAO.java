package com.revature.caliber.assessments.data;

import java.util.List;
import java.util.Set;

import com.revature.caliber.assessments.beans.TrainerNote;

public interface TrainerNoteDAO {


	/**
	 * Get TrainerNotes with matching provided trainerId
	 * @param trainerId
	 * @return
	 */
	Set<TrainerNote> getTrainerNotesByTrainerId(int trainerId);

	/**
	 * Insert provided TrainerNote into database
	 * @param trainerNote
	 */
	void createTrainerNote(TrainerNote trainerNote);
	
	//Get a TrainerNote from a Trainer for a specific week
	TrainerNote getTrainerNoteForWeek(int trainerId, int weekId);
	
}
