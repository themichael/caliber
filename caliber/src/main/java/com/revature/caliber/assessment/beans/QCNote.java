package com.revature.caliber.assessment.beans;

public class QCNote extends Note{

	private int trainee;
	private int week;
	
	public QCNote(int trainee, int week) {
		super();
		this.trainee = trainee;
		this.week = week;
	}

	public QCNote() {
		super();
	}

	public QCNote(String content, boolean sugarCoated) {
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

	@Override
	public String toString() {
		return super.toString() + " > QCNote{" +
				"trainee=" + trainee +
				", week=" + week +
				'}';
	}

}
