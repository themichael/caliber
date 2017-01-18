package com.revature.caliber.beans;

public class QCNote extends Note{

	private Trainee trainee;
	private Week week;
	
	public QCNote(Trainee trainee, Week week) {
		super();
		this.trainee = trainee;
		this.week = week;
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

	public QCNote() {
		super();
	}
	
	public QCNote(String content, boolean sugarCoated) {
		super(content, sugarCoated);
	}
	
}
