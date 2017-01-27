package com.revature.caliber.beans;

import java.util.Set;

public class Week {

	private long weekId;
	private int weekNumber;
	private Batch batch;
	private Set<Category> topics;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	private Set<Assessment> assessments;
	private BatchNote batchNotes;
	private Set<QCNote> qcNotes;
	private Set<TrainerNote> trainerNotes;
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
	public Set<Assessment> getAssessments() {
		return assessments;
	}
	public void setAssessments(Set<Assessment> assessments) {
		this.assessments = assessments;
	}
	public BatchNote getBatchNotes() {
		return batchNotes;
	}
	public void setBatchNotes(BatchNote batchNotes) {
		this.batchNotes = batchNotes;
	}
	public Set<QCNote> getQcNotes() {
		return qcNotes;
	}
	public void setQcNotes(Set<QCNote> qcNotes) {
		this.qcNotes = qcNotes;
	}
	public Set<TrainerNote> getTrainerNotes() {
		return trainerNotes;
	}
	public void setTrainerNotes(Set<TrainerNote> trainerNotes) {
		this.trainerNotes = trainerNotes;
	}
	public Week(long weekId, int weekNumber, Batch batch, Set<Category> topics, Set<Assessment> assessments) {
		super();
		this.weekId = weekId;
		this.weekNumber = weekNumber;
		this.batch = batch;
		this.topics = topics;
		this.assessments = assessments;
	}
	public Week(long weekId, int weekNumber, Batch batch, Set<Category> topics, Set<Assessment> assessments,
			BatchNote batchNotes, Set<QCNote> qcNotes, Set<TrainerNote> trainerNotes) {
		super();
		this.weekId = weekId;
		this.weekNumber = weekNumber;
		this.batch = batch;
		this.topics = topics;
		this.assessments = assessments;
		this.batchNotes = batchNotes;
		this.qcNotes = qcNotes;
		this.trainerNotes = trainerNotes;
	}
	public Week(int weekNumber, Batch batch, Set<Category> topics, Set<Assessment> assessments) {
		super();
		this.weekNumber = weekNumber;
		this.batch = batch;
		this.topics = topics;
		this.assessments = assessments;
	}

	public Week() {
	}

	@Override
	public String toString() {
		return "Week{" +
				"weekId=" + weekId +
				", weekNumber=" + weekNumber +
				", batch=" + batch +
				", topics=" + topics +
				", assessments=" + assessments +
				", batchNotes=" + batchNotes +
				", qcNotes=" + qcNotes +
				", trainerNotes=" + trainerNotes +
				'}';
	}
}

