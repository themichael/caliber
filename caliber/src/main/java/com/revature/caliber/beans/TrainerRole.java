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
 *
 */
public enum TrainerRole implements Serializable{
	@JsonProperty("ROLE_VP")
	ROLE_VP,
	@JsonProperty("ROLE_QC")
	ROLE_QC,
	@JsonProperty("ROLE_TRAINER")
	ROLE_TRAINER;
}
