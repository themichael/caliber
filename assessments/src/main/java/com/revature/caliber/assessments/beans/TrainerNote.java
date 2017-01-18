package com.revature.caliber.assessments.beans;

public class TrainerNote extends Note{

	private int trainee;
	private int week;
	
	public TrainerNote(String content, boolean sugarCoated) {
		super(content, sugarCoated);
	}

	public int getTrainee() {
		return trainee;
	}

	public void setTrainee(int trainee) {
		this.trainee = trainee;
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
