package com.revature.caliber.beans;

import java.util.Set;

public class Trainee {

	private int traineeId;
	private String name;
	private String email;
	private String trainingStatus;
	private Batch batch;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	private Set<Grade> grades;
	private Set<TrainerNote> trainerNotes;
	private Set<QCNotes> qcNotes;
	
}
