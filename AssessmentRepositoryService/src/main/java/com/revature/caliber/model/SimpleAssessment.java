package com.revature.caliber.model;

import java.io.Serializable;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * The type Simple Assessment.
 */
@Entity
@Table(name = "CALIBER_ASSESSMENT")
@Cacheable
public class SimpleAssessment implements Serializable {
	private static final long serialVersionUID = -3715103315340780554L;

	@Id
	@Column(name = "ASSESSMENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSESSMENT_ID_SEQUENCE")
	@SequenceGenerator(name = "ASSESSMENT_ID_SEQUENCE", sequenceName = "ASSESSMENT_ID_SEQUENCE")
	private Long assessmentId;

	@Min(value = 1)
	@Column(name = "RAW_SCORE", nullable = false)
	private Integer rawScore;

	@Column(name = "ASSESSMENT_TITLE")
	private String title;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "ASSESSMENT_TYPE", nullable = false)
	private AssessmentType type;

	@Min(value = 1)
	@Column(name = "WEEK_NUMBER", nullable = false)
	private Short week;

	@NotNull
	@Column(name = "BATCH_ID")
	private Integer batchId;

	@NotNull
	@Column(name = "ASSESSMENT_CATEGORY", nullable = false)
	private Integer categoryId;

	public SimpleAssessment() {
		super();
	}

	public SimpleAssessment(Long assessmentId, Integer rawScore, String title, AssessmentType type, Short week,
			Integer batchId, Integer categoryId) {
		super();
		this.assessmentId = assessmentId;
		this.rawScore = rawScore;
		this.title = title;
		this.type = type;
		this.week = week;
		this.batchId = batchId;
		this.categoryId = categoryId;
	}

	public SimpleAssessment(Assessment assessment) {
		super();
		this.assessmentId = assessment.getAssessmentId();
		this.rawScore = assessment.getRawScore();
		this.title = assessment.getTitle();
		this.type = assessment.getType();
		this.week = assessment.getWeek();
		this.batchId = assessment.getBatch().getBatchId();
		this.categoryId = assessment.getCategory().getCategoryId();
	}

	public Long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(Long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public Integer getRawScore() {
		return rawScore;
	}

	public void setRawScore(Integer rawScore) {
		this.rawScore = rawScore;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AssessmentType getType() {
		return type;
	}

	public void setType(AssessmentType type) {
		this.type = type;
	}

	public Short getWeek() {
		return week;
	}

	public void setWeek(Short week) {
		this.week = week;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@Override
	public int hashCode() {
		final Integer prime = 31;
		Integer result = 1;
		result = prime * result + batchId;
		result = prime * result + categoryId;
		result = prime * result + rawScore;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + week;
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
		SimpleAssessment other = (SimpleAssessment) obj;
		if (batchId != other.batchId)
			return false;
		if (categoryId != other.categoryId)
			return false;
		if (rawScore != other.rawScore)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (type != other.type)
			return false;
		if (week != other.week)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assessment [assessmentId=" + assessmentId + ", rawScore=" + rawScore + ", title=" + title + ", type="
				+ type + ", week=" + week + ", batchId=" + batchId + ", categoryId=" + categoryId + "]";
	}
}
