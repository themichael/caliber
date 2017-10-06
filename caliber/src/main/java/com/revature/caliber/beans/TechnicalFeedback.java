package com.revature.caliber.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Feedback regarding how well an associate performed
 * for each topic in the panel interview
 * 
 * @author Patrick Walsh
 *
 */
public enum TechnicalFeedback implements Serializable{

		@JsonProperty("Excellent")
		Excellent,
		@JsonProperty("Good")
		Good,
		@JsonProperty("Average")
		Average,
		@JsonProperty("Poor")
		Poor
		
}
