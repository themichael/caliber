package com.revature.caliber.beans;

import java.util.Set;

public class Category {

	private int categoryId;
	private String skillCategory;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	private Set<Assessment> assessments;
	private Set<TrainerNote> trainerNotes;
	private Set<Week> weeks;
	
}
