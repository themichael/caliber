package com.revature.caliber.beans;

import java.util.Set;

public class Assessment {

	private long assessmentId;
	private String title;
	private Batch batch;
	private int rawScore;
	private String type;
	private Week week;
	private QCStatus weeklyStatus;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	private QCStatus qcStatus;
	private Set<Category> categories;
	
}
