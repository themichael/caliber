package com.revature.caliber.beans;

import java.util.Set;

public class Trainee {

	private int traineeId;
	private String name;
	private String email;
	private String trainingStatus;
	private Batch batch;
	
	// Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	private Set<Grade> grades;
	private Set<TrainerNote> trainerNotes;
	private Set<QCNote> qcNotes;
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
	public Set<Grade> getGrades() {
		return grades;
	}
	public void setGrades(Set<Grade> grades) {
		this.grades = grades;
	}
	public Set<TrainerNote> getTrainerNotes() {
		return trainerNotes;
	}
	public void setTrainerNotes(Set<TrainerNote> trainerNotes) {
		this.trainerNotes = trainerNotes;
	}
	public Set<QCNote> getQcNotes() {
		return qcNotes;
	}
	public void setQcNotes(Set<QCNote> qcNotes) {
		this.qcNotes = qcNotes;
	}
	public Trainee() {
		super();
	}
	public Trainee(String name, String email, String trainingStatus, Batch batch, Set<Grade> grades) {
		super();
		this.name = name;
		this.email = email;
		this.trainingStatus = trainingStatus;
		this.batch = batch;
		this.grades = grades;
	}
	public Trainee(int traineeId, String name, String email, String trainingStatus, Batch batch, Set<Grade> grades,
			Set<TrainerNote> trainerNotes, Set<QCNote> qcNotes) {
		super();
		this.traineeId = traineeId;
		this.name = name;
		this.email = email;
		this.trainingStatus = trainingStatus;
		this.batch = batch;
		this.grades = grades;
		this.trainerNotes = trainerNotes;
		this.qcNotes = qcNotes;
	}

	@Override
	public String toString() {
		return "Trainee{" +
				"traineeId=" + traineeId +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				", trainingStatus='" + trainingStatus + '\'' +
				", batch=" + batch +
				", grades=" + grades +
				", trainerNotes=" + trainerNotes +
				", qcNotes=" + qcNotes +
				'}';
	}
}
