package com.revature.caliber.beans;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
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

@Entity
@Table(name="CALIBER_BATCH")
public class Batch {

	@Id
	@Column(name = "BATCH_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="BATCH_ID_SEQUENCE")
	@SequenceGenerator(name = "BATCH_ID_SEQUENCE", sequenceName = "BATCH_ID_SEQUENCE")
    private int batchId;
	
	@Column(name = "TRAINING_NAME")
    private String trainingName;
	
	@ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	@JoinColumn(name = "TRAINER_ID", nullable = false)
	@JsonProperty
    private Trainer trainer;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "CO_TRAINER_ID")
	private Trainer coTrainer;
    
	@Enumerated(EnumType.STRING)
	@Column(name = "SKILL_TYPE")
	private SkillType skillType;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "TRAINING_TYPE")
    private TrainingType trainingType;
    
	@Column(name = "START_DATE", nullable = false)
    private Date startDate;
    
	@Column(name = "END_DATE", nullable = false)
    private Date endDate;
    
	@Column(name = "LOCATION", nullable = false)
    private String location;
    
	@Column(name = "GOOD_GRADE_THRESHOLD")
    private short goodGradeThreshold;
    
	@Column(name = "BORDERLINE_GRADE_THRESHOLD")
    private short borderlineGradeThreshold;
	
	@Column(name="NUMBER_OF_WEEKS")
    private short weeks;
	
	/**
	 * How far back in time to compare this batch against the average
	 */
	@Column(name = "BENCHMARK_START_DATE")
    private Date benchmarkStartDate;
    
	/**
	 * When to stop comparing this batch against the average
	 */
	@Column(name = "BENCHMARK_END_DATE")
    private Date benchmarkEndDate;

    // Bi-directional mapping -- to avoid recursion, make DTO to send to UI
	@OneToMany(mappedBy = "batch", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JsonManagedReference(value = "traineeAndBatch")
    private Set<Trainee> trainees;
	
	@OneToMany(mappedBy="batch")
	private Set<Note> notes;
	
	@OneToMany(mappedBy="batch")
	private Set<Assessment> assessments;

    /**
     * Gets batch id.
     *
     * @return the batch id
     */
//Getters and setters
    public int getBatchId() {
        return batchId;
    }

    public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	public Set<Assessment> getAssessments() {
		return assessments;
	}

	public void setAssessments(Set<Assessment> assessments) {
		this.assessments = assessments;
	}

	/**
     * Sets batch id.
     *
     * @param batchId the batch id
     */
    public void setBatchId(int batchId) {
        this.batchId = batchId;
    }

    /**
     * Gets training name.
     *
     * @return the training name
     */
    public String getTrainingName() {
        return trainingName;
    }

    /**
     * Sets training name.
     *
     * @param trainingName the training name
     */
    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    /**
     * Gets trainer.
     *
     * @return the trainer
     */
    public Trainer getTrainer() {
        return trainer;
    }

    /**
     * Sets trainer.
     *
     * @param trainer the trainer
     */
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    /**
     * Gets co trainer.
     *
     * @return the co trainer
     */
    public Trainer getCoTrainer() {
        return coTrainer;
    }

    /**
     * Sets co trainer.
     *
     * @param coTrainer the co trainer
     */
    public void setCoTrainer(Trainer coTrainer) {
        this.coTrainer = coTrainer;
    }

    /**
     * Gets skill type.
     *
     * @return the skill type
     */
    public SkillType getSkillType() {
        return skillType;
    }

    /**
     * Sets skill type.
     *
     * @param skillType the skill type
     */
    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    /**
     * Gets training type.
     *
     * @return the training type
     */
    public TrainingType getTrainingType() {
        return trainingType;
    }

    /**
     * Sets training type.
     *
     * @param trainingType the training type
     */
    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets start date.
     *
     * @param startDate the start date
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Sets end date.
     *
     * @param endDate the end date
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets good grade threshold.
     *
     * @return the good grade threshold
     */
    public short getGoodGradeThreshold() {
        return goodGradeThreshold;
    }

    /**
     * Sets good grade threshold.
     *
     * @param goodGradeThreshold the good grade threshold
     */
    public void setGoodGradeThreshold(short goodGradeThreshold) {
        this.goodGradeThreshold = goodGradeThreshold;
    }

    /**
     * Gets borderline grade threshold.
     *
     * @return the borderline grade threshold
     */
    public short getBorderlineGradeThreshold() {
        return borderlineGradeThreshold;
    }

    /**
     * Sets borderline grade threshold.
     *
     * @param borderlineGradeThreshold the borderline grade threshold
     */
    public void setBorderlineGradeThreshold(short borderlineGradeThreshold) {
        this.borderlineGradeThreshold = borderlineGradeThreshold;
    }

    /**
     * Gets trainees.
     *
     * @return the trainees
     */
    public Set<Trainee> getTrainees() {
        return trainees;
    }

    /**
     * Sets trainees.
     *
     * @param trainees the trainees
     */
    public void setTrainees(Set<Trainee> trainees) {
        this.trainees = trainees;
    }

    /**
     * Gets weeks.
     *
     * @return the weeks
     */
    public short getWeeks() {
        return weeks;
    }

    /**
     * Sets weeks.
     *
     * @param weeks the weeks
     */
    public void setWeeks(short weeks) {
        this.weeks = weeks;
    }

    /**
     * Instantiates a new Batch.
     *
     * @param batchId                  the batch id
     * @param trainingName             the training name
     * @param trainer                  the trainer
     * @param coTrainer                the co trainer
     * @param skillType                the skill type
     * @param trainingType             the training type
     * @param startDate                the start date
     * @param endDate                  the end date
     * @param location                 the location
     * @param goodGradeThreshold       the good grade threshold
     * @param borderlineGradeThreshold the borderline grade threshold
     * @param trainees                 the trainees
     * @param weeks                    the weeks
     */
    public Batch(int batchId, String trainingName, Trainer trainer, Trainer coTrainer, SkillType skillType,
    		TrainingType trainingType, Date startDate, Date endDate, String location, short goodGradeThreshold,
                 short borderlineGradeThreshold, Set<Trainee> trainees, short weeks, Date benchmarkStartDate, Date benchmarkEndDate) {
        super();
        this.batchId = batchId;
        this.trainingName = trainingName;
        this.trainer = trainer;
        this.coTrainer = coTrainer;
        this.skillType = skillType;
        this.trainingType = trainingType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.goodGradeThreshold = goodGradeThreshold;
        this.borderlineGradeThreshold = borderlineGradeThreshold;
        this.trainees = trainees;
        this.weeks = weeks;
		this.benchmarkStartDate = benchmarkStartDate;
		this.benchmarkEndDate = benchmarkEndDate;
    }

    /**
     * Instantiates a new Batch.
     *
     * @param trainingName             the training name
     * @param trainer                  the trainer
     * @param coTrainer                the co trainer
     * @param skillType                the skill type
     * @param trainingType             the training type
     * @param startDate                the start date
     * @param endDate                  the end date
     * @param location                 the location
     * @param goodGradeThreshold       the good grade threshold
     * @param borderlineGradeThreshold the borderline grade threshold
     * @param trainees                 the trainees
     * @param weeks                    the weeks
     */
    public Batch(String trainingName, Trainer trainer, Trainer coTrainer, SkillType skillType, TrainingType trainingType,
                 Date startDate, Date endDate, String location, short goodGradeThreshold, short borderlineGradeThreshold,
                 Set<Trainee> trainees, short weeks, Date benchmarkStartDate, Date benchmarkEndDate) {
        super();
        this.trainingName = trainingName;
        this.trainer = trainer;
        this.coTrainer = coTrainer;
        this.skillType = skillType;
        this.trainingType = trainingType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.goodGradeThreshold = goodGradeThreshold;
        this.borderlineGradeThreshold = borderlineGradeThreshold;
        this.trainees = trainees;
        this.weeks = weeks;
		this.benchmarkStartDate = benchmarkStartDate;
		this.benchmarkEndDate = benchmarkEndDate;
    }

    /**
     * Instantiates a new Batch.
     */
    public Batch() {
        super();
    }

    @Override
    public String toString() {
        return "Batch{" +
                "batchId=" + batchId +
                ", trainingName='" + trainingName + '\'' +
                ", trainer=" + trainer +
                ", coTrainer=" + coTrainer +
                ", skillType='" + skillType + '\'' +
                ", trainingType='" + trainingType.getType() + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", location='" + location + '\'' +
                ", goodGradeThreshold=" + goodGradeThreshold +
                ", borderlineGradeThreshold=" + borderlineGradeThreshold +
                ", trainees=" + trainees +
                ", weeks=" + weeks +
                '}';
    }

    
}
