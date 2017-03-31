package com.revature.caliber.beans;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

/**
 * The type Trainee.
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
    
	@Column(name = "TRAINEE_EMAIL")
	private String email;
    
	@Column(name = "TRAINING_STATUS")
	private String trainingStatus;
    
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "BATCH_ID", nullable = false)
	@JsonBackReference(value = "traineeAndBatch")
	private Batch batch;

	@OneToMany(mappedBy="trainee")
    private Set<Grade> grades;

    /**
     * Gets trainee id.
     *
     * @return the trainee id
     */
    public int getTraineeId() {
        return traineeId;
    }

    /**
     * Sets trainee id.
     *
     * @param traineeId the trainee id
     */
    public void setTraineeId(int traineeId) {
        this.traineeId = traineeId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets training status.
     *
     * @return the training status
     */
    public String getTrainingStatus() {
        return trainingStatus;
    }

    /**
     * Sets training status.
     *
     * @param trainingStatus the training status
     */
    public void setTrainingStatus(String trainingStatus) {
        this.trainingStatus = trainingStatus;
    }

    /**
     * Gets batch.
     *
     * @return the batch
     */
    public Batch getBatch() {
        return batch;
    }

    /**
     * Sets batch.
     *
     * @param batch the batch
     */
    public void setBatch(Batch batch) {
        this.batch = batch;
    }

    /**
     * Gets grades.
     *
     * @return the grades
     */
    public Set<Grade> getGrades() {
        return grades;
    }

    /**
     * Sets grades.
     *
     * @param grades the grades
     */
    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    /**
     * Instantiates a new Trainee.
     */
    public Trainee() {
        super();
    }

    /**
     * Instantiates a new Trainee.
     *
     * @param name           the name
     * @param email          the email
     * @param trainingStatus the training status
     * @param batch          the batch
     * @param grades         the grades
     */
    public Trainee(String name, String email, String trainingStatus, Batch batch, Set<Grade> grades) {
        super();
        this.name = name;
        this.email = email;
        this.trainingStatus = trainingStatus;
        this.batch = batch;
        this.grades = grades;
    }

    /**
     * Instantiates a new Trainee.
     *
     * @param traineeId      the trainee id
     * @param name           the name
     * @param email          the email
     * @param trainingStatus the training status
     * @param batch          the batch
     * @param grades         the grades
     * @param trainerNotes   the trainer notes
     * @param qcNotes        the qc notes
     */
    public Trainee(int traineeId, String name, String email, String trainingStatus, Batch batch, Set<Grade> grades) {
        super();
        this.traineeId = traineeId;
        this.name = name;
        this.email = email;
        this.trainingStatus = trainingStatus;
        this.batch = batch;
        this.grades = grades;
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
                '}';
    }
}
