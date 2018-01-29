package com.revature.category.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum AssessmentType implements Serializable{
	@JsonProperty("Exam")
	Exam,
	@JsonProperty("Verbal")
	Verbal,
	@JsonProperty("Project")
	Project,
	@JsonProperty("Other")
	Other
}
