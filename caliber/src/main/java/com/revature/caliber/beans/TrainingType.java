package com.revature.caliber.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum TrainingType {
	@JsonProperty("Revature")
	Revature,
	@JsonProperty("Corporate")
	Corporate,
	@JsonProperty("University")
	University,
	@JsonProperty("Other")
	Other
}
