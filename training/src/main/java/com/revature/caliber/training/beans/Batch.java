package com.revature.caliber.training.beans;

import java.util.Date;
import java.util.Set;

public class Batch {

	private int batchId;
	private String trainingName;
	private Trainer trainer;
	private Trainer coTrainer;
	private String skillType;
	private String trainingType;
	private Date startDate;
	private Date endDate;
	private String location;
	private short goodGradeThreshold;
	private short borderlineGradeThreshold;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	private Set<Trainee> trainees;
	private Set<Week> weeks;
	public int getBatchId() {
		return batchId;
	}
	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public Trainer getTrainer() {
		return trainer;
	}
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	public Trainer getCoTrainer() {
		return coTrainer;
	}
	public void setCoTrainer(Trainer coTrainer) {
		this.coTrainer = coTrainer;
	}
	public String getSkillType() {
		return skillType;
	}
	public void setSkillType(String skillType) {
		this.skillType = skillType;
	}
	public String getTrainingType() {
		return trainingType;
	}
	public void setTrainingType(String trainingType) {
		this.trainingType = trainingType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public short getGoodGradeThreshold() {
		return goodGradeThreshold;
	}
	public void setGoodGradeThreshold(short goodGradeThreshold) {
		this.goodGradeThreshold = goodGradeThreshold;
	}
	public short getBorderlineGradeThreshold() {
		return borderlineGradeThreshold;
	}
	public void setBorderlineGradeThreshold(short borderlineGradeThreshold) {
		this.borderlineGradeThreshold = borderlineGradeThreshold;
	}
	public Set<Trainee> getTrainees() {
		return trainees;
	}
	public void setTrainees(Set<Trainee> trainees) {
		this.trainees = trainees;
	}
	public Set<Week> getWeeks() {
		return weeks;
	}
	public void setWeeks(Set<Week> weeks) {
		this.weeks = weeks;
	}
	public Batch(int batchId, String trainingName, Trainer trainer, Trainer coTrainer, String skillType,
			String trainingType, Date startDate, Date endDate, String location, short goodGradeThreshold,
			short borderlineGradeThreshold, Set<Trainee> trainees, Set<Week> weeks) {
		super();
		this.batchId = batchId;
		this.trainingName = trainingName;
		this.trainer = trainer;
		this.coTrainer = coTrainer;
		this.skillType = skillType;
		this.trainingType = trainingType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.goodGradeThreshold = goodGradeThreshold;
		this.borderlineGradeThreshold = borderlineGradeThreshold;
		this.trainees = trainees;
		this.weeks = weeks;
	}
	public Batch(String trainingName, Trainer trainer, Trainer coTrainer, String skillType, String trainingType,
			Date startDate, Date endDate, String location, short goodGradeThreshold, short borderlineGradeThreshold,
			Set<Trainee> trainees, Set<Week> weeks) {
		super();
		this.trainingName = trainingName;
		this.trainer = trainer;
		this.coTrainer = coTrainer;
		this.skillType = skillType;
		this.trainingType = trainingType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.location = location;
		this.goodGradeThreshold = goodGradeThreshold;
		this.borderlineGradeThreshold = borderlineGradeThreshold;
		this.trainees = trainees;
		this.weeks = weeks;
	}
	public Batch() {
		super();
	}
	
	
	
}
