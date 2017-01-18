package com.revature.caliber.training.beans;


import java.util.Set;

import javax.persistence.*;

@Entity(name="Caliber_Week")
public class Week {
	
	@Id
	@Column(name="Week_Id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long weekId;
	
	@Column(name="Week_Number")
	private int weekNumber;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="Week_Batch")
	private Batch batch;
	
	@OneToMany(mappedBy="weeks")
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
