package com.revature.caliber.assessments.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="CALIBER_QC_STATUS")
public class QCStatus {

	@Id
	@Column(name="STATUS_ID")
	@GeneratedValue(generator="statusIdGenerator", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="statusIdGenerator", sequenceName="STATUS_ID_GENERATOR")
	private short statusId;
	@Column(name="QC_STATUS")
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
	public QCStatus(short statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	public QCStatus() {
		super();
	}
	
	
	
}
