package com.revature.caliber.assessments.beans;

public class BatchNote extends Note{

	private int week;
	private int batch;
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public long getBatch() {
		return batch;
	}
	public void setBatch(int batch) {
		this.batch = batch;
	}
	public BatchNote(int week, int batch) {
		super();
		this.week = week;
		this.batch = batch;
	}
	public BatchNote() {
		super();
	}
	
	public BatchNote(String content, boolean sugarCoated) {
		super(content, sugarCoated);
	}
	
}
