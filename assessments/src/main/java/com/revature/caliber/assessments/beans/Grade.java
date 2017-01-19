package com.revature.caliber.assessments.beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Java Bean for Grade Object
 */
@Entity
@Table(name = "CALIBER_GRADE")
public class Grade {

	/**
	 * gradeId- primary key for Grade table
	 */
	@Id
	@Column(name = "GRADE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRADE_ID_SEQUENCE")
	@SequenceGenerator(name = "GRADE_ID_SEQUENCE", sequenceName = "GRADE_ID_SEQUENCE")
	private long gradeId;

	/**
	 * Assessment - A trainee received a grade on specified assessment TODO
	 * change
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ASSESSMENT_ID", nullable = false)
	private Assessment assessment;

	/**
	 * Trainee- the trainee that receives this Grade object
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "TRAINEE_ID", nullable = false)
	private int trainee;

	/**
	 * dateReceived- date this Grade object was received
	 */
	@Column(name = "DATE_RECEIVED")
	@NotNull
	private Date dateReceived;

	/**
	 * score - score of the grade
	 */
	@Column(name = "SCORE")
	@NotNull
	private int score;

	public Grade() {
		super();
	}

	public Grade(long gradeId, Assessment assessment, int trainee, Date dateReceived, int score) {
		super();
		this.gradeId = gradeId;
		this.assessment = assessment;
		this.trainee = trainee;
		this.dateReceived = dateReceived;
		this.score = score;
	}

	public Grade(Assessment assessment, int trainee, Date dateReceived, int score) {
		super();
		this.assessment = assessment;
		this.trainee = trainee;
		this.dateReceived = dateReceived;
		this.score = score;
	}

	public long getGradeId() {
		return gradeId;
	}

	public void setGradeId(long gradeId) {
		this.gradeId = gradeId;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public int getTrainee() {
		return trainee;
	}

	public void setTrainee(int trainee) {
		this.trainee = trainee;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}