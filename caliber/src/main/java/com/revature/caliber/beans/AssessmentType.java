package com.revature.caliber.beans;

public enum AssessmentType {

	LMS("LMS"), VERBAL("Verbal"), PROJECT("Project"),
	HOMEWORK("Homework"), QUIZ("Quiz"), OTHER("Other");
	
	private String type;

	AssessmentType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
