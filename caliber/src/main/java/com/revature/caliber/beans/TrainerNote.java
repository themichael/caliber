package com.revature.caliber.beans;

public class TrainerNote extends Note{

	private Trainee trainee;
	private Week week;
	
	public TrainerNote(String content, boolean sugarCoated) {
		super(content, sugarCoated);
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public TrainerNote() {
		super();
	}
	
	
	
	
}
