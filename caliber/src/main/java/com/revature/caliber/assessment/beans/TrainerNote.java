package com.revature.caliber.assessment.beans;

public class TrainerNote extends Note{
	
	/**
	 * The trainer who made the comment
	 */
	private int trainer;
	
	/**
	 * The week that the trainer made the evaluation 
	 */
	private int week;

	public TrainerNote() {
		super();
	}
	
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


}
