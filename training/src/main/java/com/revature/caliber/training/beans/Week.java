package com.revature.caliber.training.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Bean for Week
 */
@Entity
@Table(name = "CALIBER_WEEK")
public class Week {

	@Id
	@Column(name = "WEEK_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WEEK_ID_SEQUENCE")
	@SequenceGenerator(name = "WEEK_ID_SEQUENCE", sequenceName = "WEEK_ID_SEQUENCE")
	private long weekId;

	@Column(name = "WEEK_NUMBER")
	private int weekNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BATCH_ID")
	@JsonManagedReference
	private Batch batch;

	@ManyToMany(mappedBy = "weeks", fetch = FetchType.EAGER)
	private Set<Category> topics;

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

	public Week() {
		super();
	}

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

	@Override
	public String toString() {
		return "Week [weekId=" + weekId + ", weekNumber=" + weekNumber + ", batch=" + batch + ", topics=" + topics
				+ "]";
	}

}
