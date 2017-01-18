package com.revature.caliber.beans;

import java.util.Date;
import java.util.Set;

public class Batch {

	private int batchId;
	private String trainingName;
	private Trainer trainer;
	private Trainer coTrainer;
	private String skillType;
	private String trainingType;
	private Date startDate;
	private Date endDate;
	private String location;
	private short goodGradeThreshold;
	private short borderlineGradeThreshold;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	private Set<Trainee> trainees;
	private Set<Week> weeks;
	
	
}
