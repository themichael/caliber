package com.revature.caliber.beans;

import java.util.Set;

public class Week {

	private long weekId;
	private int weekNumber;
	private Batch batch;
	private Set<Category> topics;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	private Set<Assessment> assessments;
	private BatchNote batchNotes;
	private Set<QCNotes> qcNotes;
	private Set<TrainerNote> trainerNotes;
	
}
