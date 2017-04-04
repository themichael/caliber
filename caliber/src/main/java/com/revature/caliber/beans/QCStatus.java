package com.revature.caliber.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QCStatus {
	@JsonProperty("Poor")
	Poor,
	@JsonProperty("Average")
	Average,
	@JsonProperty("Good")
	Good,
	@JsonProperty("Superstar")
	Superstar
}
