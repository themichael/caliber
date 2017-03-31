package com.revature.caliber.beans;

public enum TrainingType {
	REVATURE("Revature"), ACADEMIC_PARTNERSHIP("AP"),
	CORPORATE("Corporate"), OTHER("Other");
	
	private String type;

	TrainingType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
