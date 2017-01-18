package com.revature.caliber.training.beans;

import java.util.Set;

public class Week {

	private long weekId;
	private int weekNumber;
	private Batch batch;
	private Set<Category> topics;
	
	public long getWeekId() {
		return weekId;
	}
	public void setWeekId(long weekId) {
		this.weekId = weekId;
	}
	public int getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public Set<Category> getTopics() {
		return topics;
	}
	public void setTopics(Set<Category> topics) {
		this.topics = topics;
	}
	
	public Week(long weekId, int weekNumber, Batch batch, Set<Category> topics) {
		super();
		this.weekId = weekId;
		this.weekNumber = weekNumber;
		this.batch = batch;
		this.topics = topics;
	}

	public Week(int weekNumber, Batch batch, Set<Category> topics) {
		super();
		this.weekNumber = weekNumber;
		this.batch = batch;
		this.topics = topics;
	}

}
