package com.revature.caliber.beans;

public enum NoteVisibility {

	VP(1), QC(2), TRAINER(3);
	
	private Integer role;
	
	private NoteVisibility(Integer role) {
		this.role = role;
	}
	
	public Integer getRole(){
		return role;
	}
	
}
