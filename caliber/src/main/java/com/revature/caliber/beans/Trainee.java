package com.revature.caliber.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The type Trainee.
 * 
 * (NOTE) 
 * Further iterations should include the following from the Salesforce: 
 * 		 recruiter_name, account_name, project_completion
 * This way we can analyze performance based on 
 * where they went to college, who recruited them, and if they finished RevaturePro.
 */
@Entity
@Table(name = "CALIBER_TRAINEE")
public class Trainee {

	@Id
	@Column(name = "TRAINEE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRAINEE_ID_SEQUENCE")
	@SequenceGenerator(name = "TRAINEE_ID_SEQUENCE", sequenceName = "TRAINEE_ID_SEQUENCE")
    private int traineeId;
	
	@Column(name = "TRAINEE_NAME")
    private String name;
	
	@Column(name="TRAINEE_EMAIL", nullable=false, unique=true)
    private String email;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TRAINING_STATUS")
    private TrainingStatus trainingStatus;
	
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "BATCH_ID", nullable = false)
	@JsonBackReference(value = "traineeAndBatch")
    private Batch batch;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNumber;
	
	@Column(name="SKYPE_ID")
	private String skypeId;
	
	@Column(name="PROFILE_URL")
	private String profileUrl;

	@JsonIgnore
	@OneToMany(mappedBy="trainee")
    private Set<Grade> grades;

	@JsonIgnore
	@OneToMany(mappedBy="trainee")
	private Set<Note> notes;

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

	public TrainingStatus getTrainingStatus() {
		return trainingStatus;
	}

	public void setTrainingStatus(TrainingStatus trainingStatus) {
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

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSkypeId() {
		return skypeId;
	}

	public void setSkypeId(String skypeId) {
		this.skypeId = skypeId;
	}

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public Trainee(String name, String email, TrainingStatus trainingStatus, String phoneNumber,
			String skypeId, String profileUrl, Batch batch) {
		super();
		this.name = name;
		this.email = email;
		this.trainingStatus = trainingStatus;
		this.batch = batch;
		this.phoneNumber = phoneNumber;
		this.skypeId = skypeId;
		this.profileUrl = profileUrl;
	}

	public Trainee() {
		super();
	}

	@Override
	public String toString() {
		return "Trainee [traineeId=" + traineeId + ", name=" + name + ", email=" + email + ", trainingStatus="
				+ trainingStatus + "]";
	}
}
