package com.revature.caliber.beans;

import java.util.Set;

public class Assessment {

	private long assessmentId;
	private String title;
	private Batch batch;
	private int rawScore;
	private String type;
	private Week week;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	private QCStatus weeklyStatus;
	private Set<Category> categories;
	public long getAssessmentId() {
		return assessmentId;
	}
	public void setAssessmentId(long assessmentId) {
		this.assessmentId = assessmentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public int getRawScore() {
		return rawScore;
	}
	public void setRawScore(int rawScore) {
		this.rawScore = rawScore;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Week getWeek() {
		return week;
	}
	public void setWeek(Week week) {
		this.week = week;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	public QCStatus getWeeklyStatus() {
		return weeklyStatus;
	}
	public void setWeeklyStatus(QCStatus weeklyStatus) {
		this.weeklyStatus = weeklyStatus;
	}
	public Assessment(long assessmentId, String title, Batch batch, int rawScore, String type, Week week,
			Set<Category> categories) {
		super();
		this.assessmentId = assessmentId;
		this.title = title;
		this.batch = batch;
		this.rawScore = rawScore;
		this.type = type;
		this.week = week;
		this.categories = categories;
	}
	public Assessment() {
		super();
	}
	public Assessment(String title, Batch batch, int rawScore, String type, Week week, Set<Category> categories) {
		super();
		this.title = title;
		this.batch = batch;
		this.rawScore = rawScore;
		this.type = type;
		this.week = week;
		this.categories = categories;
	}
	
	
	
}
