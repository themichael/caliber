package com.revature.caliber.assessments.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="CALIBER_BATCH_NOTE")
public class BatchNote extends Note{
	 
	/**
	 * The week of the batch's evaluation
	 */
	@Column(name="WEEK_ID", nullable=false)
	private int week;
	
	/**
	 * The batch identifier
	 */
	@Column(name="BATCH_ID")
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
