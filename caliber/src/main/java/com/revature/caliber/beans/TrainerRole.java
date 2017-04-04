package com.revature.caliber.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * System access roles. 
 * 
 * *****Listed in order of precedence descending******
 * 
 * @author Patrick Walsh
 *
 */
public enum TrainerRole {
	@JsonProperty("VP")
	VP,
	@JsonProperty("QC")
	QC,
	@JsonProperty("TRAINER")
	TRAINER
}
