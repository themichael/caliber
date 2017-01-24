package com.revature.caliber.assessments.beans;

import javax.persistence.*;
import java.util.Set;

/**
 * JavaBean for QCStatus Object Status of assessments determine by Quality
 * Control (Trainer Object)
 */
@Entity
@Table(name = "CALIBER_QC_STATUS")
public class QCStatus {

	@Id
	@Column(name = "QC_STATUS_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "QC_STATUS_ID_SEQUENCE")
	@SequenceGenerator(name = "QC_STATUS_ID_SEQUENCE", sequenceName = "QC_STATUS_ID_SEQUENCE")
	private short statusId;

	@Column(name = "QC_STATUS_STATUS")

	private String status;

	@OneToMany(mappedBy = "weeklyStatus")
	private Set<Assessment> assessments;

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

	public QCStatus(String status) {
		super();
		this.status = status;
	}

	public Set<Assessment> getAssessments() {
		return assessments;
	}

	public void setAssessments(Set<Assessment> assessments) {
		this.assessments = assessments;
	}

	public QCStatus(short statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}

	public QCStatus() {
		super();
	}

}
