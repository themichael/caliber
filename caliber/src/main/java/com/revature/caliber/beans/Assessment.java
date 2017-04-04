package com.revature.caliber.beans;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The type Assessment.
 */
@Entity
@Table(name = "CALIBER_ASSESSMENT")
public class Assessment {

	@Id
	@Column(name = "ASSESSMENT_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSESSMENT_ID_SEQUENCE")
	@SequenceGenerator(name = "ASSESSMENT_ID_SEQUENCE", sequenceName = "ASSESSMENT_ID_SEQUENCE")
	private long assessmentId;

	/**
	 * Trainer inputted title, can be anything to help identify this assessment
	 */
	@Column(name = "ASSESSMENT_TITLE", nullable = false)
	private String title;

	/**
	 * Batch ID reference
	 */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BATCH_ID", nullable = false)
	private Batch batch;

	/**
	 * Raw numerical score before calculations This value is the maximum number
	 * of points that can be earned on this assignment.
	 */
	@Column(name = "RAW_SCORE")
	private int rawScore;

	/**
	 * Assessment type, e.g. LMS, Verbal
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "ASSESSMENT_TYPE", nullable = false)
	private AssessmentType type;

	@Column(name = "WEEK_NUMBER", nullable = false)
	private short week;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "ASSESSMENT_CATEGORY")
	private Category category;

	public long getAssessmentId() {
		return assessmentId;
	}

	public void setAssessmentId(long assessmentId) {
		this.assessmentId = assessmentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public int getRawScore() {
		return rawScore;
	}

	public void setRawScore(int rawScore) {
		this.rawScore = rawScore;
	}

	public AssessmentType getType() {
		return type;
	}

	public void setType(AssessmentType type) {
		this.type = type;
	}

	public short getWeek() {
		return week;
	}

	public void setWeek(short week) {
		this.week = week;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Assessment() {
		super();
	}

	public Assessment(String title, Batch batch, Integer rawScore, AssessmentType type, Integer week,
			Category category) {
		super();
		this.title = title;
		this.batch = batch;
		this.rawScore = rawScore;
		this.type = type;
		this.week = week.shortValue();
		this.category = category;
	}

	@Override
	public String toString() {
		return "Assessment [assessmentId=" + assessmentId + ", rawScore=" + rawScore + ", type=" + type + ", category="
				+ category + "]";
	}

}