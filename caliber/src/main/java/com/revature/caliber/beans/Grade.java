package com.revature.caliber.beans;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The type Grade.
 */
@Entity
@Table(name = "CALIBER_GRADE")
public class Grade implements Serializable{

	private static final long serialVersionUID = -2031135710502844800L;

	@Id
	@Column(name = "GRADE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRADE_ID_SEQUENCE")
	@SequenceGenerator(name = "GRADE_ID_SEQUENCE", sequenceName = "GRADE_ID_SEQUENCE")
	private long gradeId;

	/**
	 * Assessment - The specified assessment taken by the Trainee
	 */
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "ASSESSMENT_ID", nullable = false)
	@JsonIgnore
	private Assessment assessment;

	/**
	 * Trainee- the trainee that receives this Grade
	 */
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "TRAINEE_ID", nullable = false)
	private Trainee trainee;

	/**
	 * dateReceived- date this Grade was earned
	 */
	@Column(name = "DATE_RECEIVED")
	@NotNull
	private Date dateReceived;

	/**
	 * score - points earned. should be based on raw score of Assessment.
	 * Example: Assessment is worth 200 points, and Trainee made a 150 thus
	 * score is stored as percentage, 75%
	 */
	@Column(name = "SCORE")
	@NotNull
	private double score;

	public Grade() {
		super();
	}

	public Grade(Assessment assessment, Trainee trainee, Date dateReceived, double score) {
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

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", assessment=" + assessment + ", trainee=" + trainee + ", dateReceived="
				+ dateReceived + ", score=" + score + "]";
	}
}