package com.revature.caliber.assessments.beans;

public class QCStatus {

	private short statusId;
	private String status;



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
