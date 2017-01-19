package com.revature.caliber.assessments.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity(name="TRAINER_NOTE")
public class TrainerNote extends Note{
	@Id
	@Column(name="TRAINER_ID")
	@NotNull
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int trainer;
	
	@Column(name="WEEK_ID")
	@NotNull
	private int week;
	
	
	public TrainerNote(String content, boolean sugarCoated) {
		super(content, sugarCoated);
	}

	public int getTrainer() {
		return trainer;
	}

	public void setTrainer(int trainer) {
		this.trainer = trainer;
	}

	public int getWeek() {
		return week;
	}

	public void setWeek(int week) {
		this.week = week;
	}

	public TrainerNote() {
		super();
	}
	
}
