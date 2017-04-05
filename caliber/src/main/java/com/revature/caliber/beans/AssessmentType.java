package com.revature.caliber.beans;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * (NOTE) actually just random thoughts
 * Future iterations could accommodate for Panel interview
 * to input their feedback as well. That would require multiple categories
 * per one Assessment (unless they create many assessments and can access any trainer's batch)
 */
public enum AssessmentType implements Serializable{
	@JsonProperty("Exam")
	Exam,
	@JsonProperty("Written")
	Written,
	@JsonProperty("Verbal")
	Verbal,
	@JsonProperty("Project")
	Project,
	@JsonProperty("Demo")
	Demo,
	@JsonProperty("Quiz")
	Quiz,
	@JsonProperty("Other")
	Other
}
