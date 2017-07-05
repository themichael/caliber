package com.revature.caliber.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * System access roles. 
 * 
 * *****Listed in order of precedence descending******
 * 
 * @author Patrick Walsh
 * @author Stanley Chouloute
 * @author Adam Baker
 *
 */
public enum TrainerRole implements Serializable{
	@JsonProperty("ROLE_VP")
	ROLE_VP("VP"),
	@JsonProperty("ROLE_QC")
	ROLE_QC("QC"),
	@JsonProperty("ROLE_TRAINER")
	ROLE_TRAINER("Trainer"),
	@JsonProperty("ROLE_INACTIVE")
	ROLE_INACTIVE("Inactive");
	
	private String role;

	private TrainerRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}
	
	//This is needed to display the roles without the underscore
	@Override
	public String toString(){
		return role;
	}
	
	
}
