package com.revature.caliber.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * The type Simple Grade.
 */
@Entity
@Table(name = "CALIBER_GRADE")
@Cacheable
public class SimpleGrade implements Serializable {
	private static final long serialVersionUID = -1052997429979145920L;

	@Id
	@Column(name = "GRADE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRADE_ID_SEQUENCE")
	@SequenceGenerator(name = "GRADE_ID_SEQUENCE", sequenceName = "GRADE_ID_SEQUENCE")
	private Long gradeId;

	/**
	 * Assessment - The specified assessment taken by the Trainee
	 */
	@Column(name = "ASSESSMENT_ID")
	private Long assessmentId;

	/**
	 * Trainee- the trainee that receives this Grade
	 */
	@Column(name = "TRAINEE_ID")
	private Integer traineeId;

	/**
	 * dateReceived- date this Grade was earned
	 */
	@NotNull
	@Column(name = "DATE_RECEIVED")
	private Date dateReceived;

	/**
	 * score - points earned. should be based on raw score of Assessment. Example:
	 * Assessment is worth 200 points, and Trainee made a 75% thus score is 150
	 */
	@Min(value = 0)
	@Column(name = "SCORE")
	private Double score;

	public SimpleGrade() {
		super();
	}

	public SimpleGrade(Long assessmentId, Integer traineeId, Date dateReceived, Double score) {
		super();
		this.assessmentId = assessmentId;
		this.traineeId = traineeId;
		this.dateReceived = dateReceived;
		this.score = score;
	}

	public SimpleGrade(Grade grade) {
		super();
		this.gradeId = grade.getGradeId();
		this.assessmentId = grade.getAssessment() != null ? grade.getAssessment().getAssessmentId() : null;
		this.traineeId = grade.getTrainee() != null ? grade.getTrainee().getTraineeId() : null;
		this.dateReceived = grade.getDateReceived();
		this.score = grade.getScore();
	}

	public Long getGradeId() {
		return gradeId;
	}

	public void setGradeId(Long gradeId) {
		this.gradeId = gradeId;
	}

	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}

	public Date getDateReceived() {
		return dateReceived;
	}

	public void setDateReceived(Date dateReceived) {
		this.dateReceived = dateReceived;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assessmentId == null) ? 0 : assessmentId.hashCode());
		result = prime * result + ((dateReceived == null) ? 0 : dateReceived.hashCode());
		result = prime * result + (int) (gradeId ^ (gradeId >>> 32));
		long temp;
		temp = Double.doubleToLongBits(score);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((traineeId == null) ? 0 : traineeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SimpleGrade other = (SimpleGrade) obj;
		if (assessmentId == null) {
			if (other.assessmentId != null)
				return false;
		} else if (!assessmentId.equals(other.assessmentId))
			return false;
		if (dateReceived == null) {
			if (other.dateReceived != null)
				return false;
		} else if (!dateReceived.equals(other.dateReceived))
			return false;
		if (gradeId != other.gradeId)
			return false;
		if (Double.doubleToLongBits(score) != Double.doubleToLongBits(other.score))
			return false;
		if (traineeId == null) {
			if (other.traineeId != null)
				return false;
		} else if (!traineeId.equals(other.traineeId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Grade [gradeId=" + gradeId + ", assessmentId=" + assessmentId + ", traineeId=" + traineeId
				+ ", dateReceived=" + dateReceived + ", score=" + score + "]";
	}
}
