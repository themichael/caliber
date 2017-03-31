package com.revature.caliber.beans;

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
    
    @Column(name = "ASSESSMENT_TITLE", nullable = false)
	private String title;
    
    @ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER)
    @JoinColumn(name = "BATCH_ID", nullable = false)
	private Batch batch;
    
    @Column(name = "RAW_SCORE")
	private int rawScore;
    
    @Column(name = "ASSESSMENT_TYPE", nullable = false)
	private AssessmentType type;
    
	@Column(name="WEEK_NUMBER")
	private short week;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="ASSESSMENT_CATEGORY", nullable=false)
	private Category category;

	/**
	 * Gets assessment id.
	 *
	 * @return the assessment id
	 */
	public long getAssessmentId() {
		return assessmentId;
	}

	/**
	 * Sets assessment id.
	 *
	 * @param assessmentId
	 *            the assessment id
	 */
	public void setAssessmentId(long assessmentId) {
		this.assessmentId = assessmentId;
	}

	/**
	 * Gets title.
	 *
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets title.
	 *
	 * @param title
	 *            the title
	 */
	public void setTitle(String title) {
		this.title = title;
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
	 * @param batch
	 *            the batch
	 */
	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	/**
	 * Gets raw score.
	 *
	 * @return the raw score
	 */
	public int getRawScore() {
		return rawScore;
	}

	/**
	 * Sets raw score.
	 *
	 * @param rawScore
	 *            the raw score
	 */
	public void setRawScore(int rawScore) {
		this.rawScore = rawScore;
	}

	/**
	 * Gets type.
	 *
	 * @return the type
	 */
	public AssessmentType getType() {
		return type;
	}

	/**
	 * Sets type.
	 *
	 * @param type
	 *            the type
	 */
	public void setType(AssessmentType type) {
		this.type = type;
	}

	/**
	 * Gets week.
	 *
	 * @return the week
	 */
	public short getWeek() {
		return week;
	}

	/**
	 * Sets week.
	 *
	 * @param week
	 *            the week
	 */
	public void setWeek(short week) {
		this.week = week;
	}

	/**
	 * Gets categories.
	 *
	 * @return the categories
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * Sets categories.
	 *
	 * @param categories
	 *            the categories
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * Instantiates a new Assessment.
	 *
	 * @param assessmentId
	 *            the assessment id
	 * @param title
	 *            the title
	 * @param batch
	 *            the batch
	 * @param rawScore
	 *            the raw score
	 * @param type
	 *            the type
	 * @param week
	 *            the week
	 * @param categories
	 *            the categories
	 */
	public Assessment(long assessmentId, String title, Batch batch, int rawScore, AssessmentType type, short week,
			Category category) {
		super();
		this.assessmentId = assessmentId;
		this.title = title;
		this.batch = batch;
		this.rawScore = rawScore;
		this.type = type;
		this.week = week;
		this.category = category;
	}

	/**
	 * Instantiates a new Assessment.
	 */
	public Assessment() {
		super();
	}

	/**
	 * Instantiates a new Assessment.
	 *
	 * @param title
	 *            the title
	 * @param batch
	 *            the batch
	 * @param rawScore
	 *            the raw score
	 * @param type
	 *            the type
	 * @param week
	 *            the week
	 * @param categories
	 *            the categories
	 */
	public Assessment(String title, Batch batch, int rawScore, AssessmentType type, short week, Category category) {
		super();
		this.title = title;
		this.batch = batch;
		this.rawScore = rawScore;
		this.type = type;
		this.week = week;
		this.category = category;
	}

	@Override
	public String toString() {
		return "Assessment [assessmentId=" + assessmentId + ", title=" + title + ", batch=" + batch + ", rawScore="
				+ rawScore + ", type=" + type + ", week=" + week + ", category="
				+ category + "]";
	}
}