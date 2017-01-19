package com.revature.caliber.assessments.beans;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity(name="CALIBER_BATCH_NOTE")
public class BatchNote extends Note{
	
	@Id
	@Column(name="WEEK_ID", nullable=false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	@OneToOne(cascade=CascadeType.REMOVE, fetch=FetchType.EAGER) 	
	@JoinColumn(name="WEEK_ID")
	private int week;
	
	@Column(name="BATCH_ID")
	@NotNull
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
