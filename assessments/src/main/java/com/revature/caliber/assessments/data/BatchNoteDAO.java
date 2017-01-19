package com.revature.caliber.assessments.data;

import java.util.HashSet;

import com.revature.caliber.assessments.beans.BatchNote;

public interface BatchNoteDAO {
	
	void createBatchNote(int batchId, int weekId);
	BatchNote getBatchNote(int batchId, int weekId);	
	HashSet<BatchNote> allBatchNotesByWeek(int weekId);
}
