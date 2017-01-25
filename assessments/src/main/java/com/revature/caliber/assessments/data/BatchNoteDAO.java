package com.revature.caliber.assessments.data;

import java.util.List;

import com.revature.caliber.assessments.beans.BatchNote;

public interface BatchNoteDAO {
	
	//Creates BatchNotes
	/**
	 * Creates BatchNotes for QC using both the batchID, and the weekID
	 * @param batchNote
	 **/
	void createBatchNote(BatchNote batchNote);
	
	//Get BatchNote using both batchID, and the weekID  
	/**
	 * Get BatchNote using both the batchID, and the weekID
	 * @param batchId, weekId
	 **/
	BatchNote getBatchNote(int batchId, int weekId);	
	
	//Create a List of all BatchNotes
	/**
	 * Creates a List of BatchNotes for every Batch within weekID
	 * @param weekId
	 **/
	List<BatchNote> allBatchNotesByWeek(int weekId);
	
	//Create a List of BatchNotes
	/**
	 * Creates a List of BatchNotes for a specific Batch
	 * @param batchId
	 **/
	List<BatchNote> allBatchNotes(int batchId);
	
	//Update a BatchNote
	/**
	 * Update BatchNote
	 * @param batchNote
	 **/
	void updateBatchNote(BatchNote batchNote);
	
	//Delete a BatchNote
	/**
	 * Delete BatchNote
	 * @param batchNote
	 **/
	void deleteBatchNote(BatchNote batchNote);
}
