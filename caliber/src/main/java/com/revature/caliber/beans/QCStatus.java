package com.revature.caliber.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QCStatus implements Serializable{
	@JsonProperty("Poor")
	Poor,
	@JsonProperty("Average")
	Average,
	@JsonProperty("Good")
	Good,
	@JsonProperty("Superstar")
	Superstar
}
