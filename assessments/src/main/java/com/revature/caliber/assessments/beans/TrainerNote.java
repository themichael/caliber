package com.revature.caliber.assessments.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="TRAINER_NOTE")
public class TrainerNote extends Note{
	//Get the ID for the trainer to set the owner of the Note 
	@Id
	@Column(name="TRAINER_ID")
	@JoinColumn(name="TRAINER_ID")
	@OneToOne
	private int trainer;
	
	//Get the ID for the week to set which week the note is regarding 
	@Column(name="WEEK_ID", nullable=false)
	@JoinColumn(name="WEEK_ID")
	@OneToOne
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
