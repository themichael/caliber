package com.revature.caliber.assessments.service;

import java.util.HashSet;

import com.revature.caliber.assessments.beans.BatchNote;

public interface BatchService {
	void createBatchNote(int batchId, int weekId);
	BatchNote getBatchNoteByWeek(int batchId, int weekId);	
	HashSet<BatchNote> allBatchNotesForWeek(int weekId);
}
