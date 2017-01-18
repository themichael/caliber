package com.revature.caliber.training.beans;

public class Trainee {

	private int traineeId;
	private String name;
	private String email;
	private String trainingStatus;
	private Batch batch;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	public int getTraineeId() {
		return traineeId;
	}
	public void setTraineeId(int traineeId) {
		this.traineeId = traineeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTrainingStatus() {
		return trainingStatus;
	}
	public void setTrainingStatus(String trainingStatus) {
		this.trainingStatus = trainingStatus;
	}
	public Batch getBatch() {
		return batch;
	}
	public void setBatch(Batch batch) {
		this.batch = batch;
	}
	public Trainee() {
		super();
	}
	public Trainee(String name, String email, String trainingStatus, Batch batch) {
		super();
		this.name = name;
		this.email = email;
		this.trainingStatus = trainingStatus;
		this.batch = batch;
	}
	public Trainee(int traineeId, String name, String email, String trainingStatus, Batch batch) {
		super();
		this.traineeId = traineeId;
		this.name = name;
		this.email = email;
		this.trainingStatus = trainingStatus;
		this.batch = batch;
	}
	
	
	
}
