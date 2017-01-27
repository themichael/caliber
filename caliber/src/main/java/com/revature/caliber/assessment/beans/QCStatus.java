package com.revature.caliber.assessment.beans;

import java.util.Set;

/**
 * JavaBean for QCStatus Object Status of assessments determine by Quality Control (Trainer Object)
 */
public class QCStatus {

	private short statusId;

	private String status;

	private Set<Assessment> assessments;

	public QCStatus() {
		super();
	}

	public QCStatus(String status) {
		this.status = status;
	}

	public QCStatus(short statusId, String status) {
		this.statusId = statusId;
		this.status = status;
	}

	public short getStatusId() {
		return statusId;
	}

	public void setStatusId(short statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Assessment> getAssessments() {
		return assessments;
	}

	public void setAssessments(Set<Assessment> assessments) {
		this.assessments = assessments;
	}

	@Override
	public String toString() {
		return "QCStatus [statusId=" + statusId + ", status=" + status + ", assessments=" + assessments + "]";
	}

}