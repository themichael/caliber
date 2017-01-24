package com.revature.caliber.assessments.service;

import java.util.List;

import com.revature.caliber.assessments.beans.BatchNote;

public interface BatchNoteService {

	void makeBatchNote(int batchId, int weekId);
	
	BatchNote weeklyBatchNote(int batchId, int weekId);	
	
	List<BatchNote> allBatchNotesInWeek(int weekId);
}
