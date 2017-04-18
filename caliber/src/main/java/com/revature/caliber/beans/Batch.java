package com.revature.caliber.beans;

import java.io.Serializable;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.revature.caliber.validator.BatchValObject;

/**
 * The type Batch.
 */
@Entity
@Table(name = "CALIBER_BATCH")
@BatchValObject
public class Batch implements Serializable {

	private static final long serialVersionUID = -5755409643112884001L;

	@Id
	@Column(name = "BATCH_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BATCH_ID_SEQUENCE")
	@SequenceGenerator(name = "BATCH_ID_SEQUENCE", sequenceName = "BATCH_ID_SEQUENCE")
	private int batchId;

	/**
	 * Example: 1702 Java CUNY
	 */
	@Column(name = "TRAINING_NAME")
	private String trainingName;

	@JsonProperty
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "TRAINER_ID", nullable = false)
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

	@Temporal(TemporalType.DATE)
	@Column(name = "START_DATE", nullable = false)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(name = "END_DATE", nullable = false)
	private Date endDate;

	@Past
	@Temporal(TemporalType.DATE)
	@Column(name = "BENCHMARK_START_DATE", nullable = false)
	private Date benchmarkStartDate;

	@Column(name = "LOCATION", nullable = false)
	private String location;

	/**
	 * Anything above this grade is GREEN
	 */
	@Column(name = "GOOD_GRADE_THRESHOLD")
	private short goodGradeThreshold;

	/**
	 * Anything above this grade but below goodGradeThreshold is YELLOW Anything
	 * below this grade is RED
	 */
	@Column(name = "BORDERLINE_GRADE_THRESHOLD")
	private short borderlineGradeThreshold;

	@OneToMany(mappedBy = "batch", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonManagedReference(value = "traineeAndBatch")
	private Set<Trainee> trainees;

	@Column(name = "NUMBER_OF_WEEKS", nullable = false)
	private int weeks;

	@JsonIgnore
	@OneToMany(mappedBy = "batch")
	private Set<Note> notes;

	public Batch() {
		super();
		this.weeks = 1;
	}

	public Batch(String trainingName, Trainer trainer, SkillType skillType, TrainingType trainingType, Date startDate,
			Date endDate, Date benchmarkStartDate, String location, Integer goodGradeThreshold,
			Integer borderlineGradeThreshold, Integer weeks) {
		this();
		this.trainingName = trainingName;
		this.trainer = trainer;
		this.skillType = skillType;
		this.trainingType = trainingType;
		this.startDate = startDate;
		this.endDate = endDate;
		this.benchmarkStartDate = benchmarkStartDate;
		this.location = location;
		this.goodGradeThreshold = goodGradeThreshold.shortValue();
		this.borderlineGradeThreshold = borderlineGradeThreshold.shortValue();
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public Trainer getCoTrainer() {
		return coTrainer;
	}

	public void setCoTrainer(Trainer coTrainer) {
		this.coTrainer = coTrainer;
	}

	public SkillType getSkillType() {
		return skillType;
	}

	public void setSkillType(SkillType skillType) {
		this.skillType = skillType;
	}

	public TrainingType getTrainingType() {
		return trainingType;
	}

	public void setTrainingType(TrainingType trainingType) {
		this.trainingType = trainingType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getBenchmarkStartDate() {
		return benchmarkStartDate;
	}

	public void setBenchmarkStartDate(Date benchmarkStartDate) {
		this.benchmarkStartDate = benchmarkStartDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public short getGoodGradeThreshold() {
		return goodGradeThreshold;
	}

	public void setGoodGradeThreshold(short goodGradeThreshold) {
		this.goodGradeThreshold = goodGradeThreshold;
	}

	public short getBorderlineGradeThreshold() {
		return borderlineGradeThreshold;
	}

	public void setBorderlineGradeThreshold(short borderlineGradeThreshold) {
		this.borderlineGradeThreshold = borderlineGradeThreshold;
	}

	public Set<Trainee> getTrainees() {
		return trainees;
	}

	public void setTrainees(Set<Trainee> trainees) {
		this.trainees = trainees;
	}

	public int getWeeks() {
		return weeks;
	}

	public void setWeeks(int weeks) {
		this.weeks = weeks;
	}

	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", trainingName=" + trainingName + ", skillType=" + skillType
				+ ", trainingType=" + trainingType + " trainees=" + trainees + "]";
	}

}