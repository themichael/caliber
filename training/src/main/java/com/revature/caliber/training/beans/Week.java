package com.revature.caliber.training.beans;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "CALIBER_WEEK")
public class Week {

	@Id
	@Column(name = "WEEK_ID")
<<<<<<< HEAD
<<<<<<< HEAD
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WEEK_ID_SEQUENCE")
	@SequenceGenerator(name = "WEEK_ID_SEQUENCE", sequenceName = "WEEK_ID_SEQUENCE")
=======
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WEEK_ID_SEQUENCE")
    @SequenceGenerator(name = "WEEK_ID_SEQUENCE", sequenceName = "WEEK_ID_SEQUENCE")
>>>>>>> a6dc4481299afa34c63677a09485d35ed25d8913
=======
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WEEK_ID_SEQUENCE")
  @SequenceGenerator(name = "WEEK_ID_SEQUENCE", sequenceName = "WEEK_ID_SEQUENCE")
>>>>>>> 7b7c0f4a1e6292d93350544eea8a52dd7413c239
	private long weekId;

	@Column(name = "WEEK_NUMBER")
	private int weekNumber;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "BATCH_ID")
	private Batch batch;

	@ManyToMany(mappedBy = "weeks")
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

	public Week() {
		super();
	}

	@Override
	public String toString() {
		return "Week [weekId=" + weekId + ", weekNumber=" + weekNumber + ", batch=" + batch + ", topics=" + topics
				+ "]";
	}

}
