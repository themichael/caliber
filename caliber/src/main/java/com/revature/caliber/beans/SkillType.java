package com.revature.caliber.beans;

public enum SkillType {
	J2EE("J2EE"), NET(".NET"), SDET("SDET"), BPM("BPM"), OTHER("Other");
	
	private String type;

	private SkillType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}
	
}
