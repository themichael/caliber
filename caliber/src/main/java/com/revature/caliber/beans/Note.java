package com.revature.caliber.beans;

import java.io.Serializable;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CALIBER_NOTE")
public class Note implements Serializable{

	private static final long serialVersionUID = -4960654794116385953L;

	@Id
	@Column(name = "NOTE_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTE_ID_SEQUENCE")
	@SequenceGenerator(name = "NOTE_ID_SEQUENCE", sequenceName = "NOTE_ID_SEQUENCE")
	private int noteId;

	@Length(min=0, max=1000)
	@Column(name = "NOTE_CONTENT")
	private String content;

	@Min(value=1)
	@Column(name = "WEEK_NUMBER")
	private short week;

	/**
	 * Will be null if the note is individual trainee feedback
	 */
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "BATCH_ID", nullable = true)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Batch batch;

	/**
	 * Will be null if the note is overall batch feedback
	 */
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinColumn(name = "TRAINEE_ID", nullable = true)
	private Trainee trainee;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "MAX_VISIBILITY")
	private TrainerRole maxVisibility;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "NOTE_TYPE")
	private NoteType type;

	@Column(name = "IS_QC_FEEDBACK", nullable = false)
	private boolean qcFeedback;

	@Enumerated(EnumType.STRING)
	@Column(name = "QC_STATUS", nullable = true)
	private QCStatus qcStatus;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public short getWeek() {
		return week;
	}

	public void setWeek(short week) {
		this.week = week;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public TrainerRole getMaxVisibility() {
		return maxVisibility;
	}

	public void setMaxVisibility(TrainerRole maxVisibility) {
		this.maxVisibility = maxVisibility;
	}

	public NoteType getType() {
		return type;
	}

	public void setType(NoteType type) {
		this.type = type;
	}

	public boolean isQcFeedback() {
		return qcFeedback;
	}

	public void setQcFeedback(boolean qcFeedback) {
		this.qcFeedback = qcFeedback;
	}

	public QCStatus getQcStatus() {
		return qcStatus;
	}

	public void setQcStatus(QCStatus qcStatus) {
		this.qcStatus = qcStatus;
	}

	/**
	 * Factory method to construct new QC weekly batch note
	 * 
	 * @param content
	 * @param week
	 * @param batch
	 * @param qcStatus
	 * @param isPublic
	 * @return
	 */
	public static Note qcBatchNote(String content, Integer week, Batch batch, QCStatus qcStatus, boolean isPublic) {
		if (isPublic)
			return new Note(content, week.shortValue(), batch, NoteType.PUBLIC_BATCH, qcStatus);
		else
			return new Note(content, week.shortValue(), batch, NoteType.QC_BATCH, qcStatus);
	}

	/**
	 * Factory method for creating new QC weekly individual trainee note
	 * 
	 * @param content
	 * @param week
	 * @param trainee
	 * @param qcStatus
	 * @param isPublic
	 * @return
	 */
	public static Note qcIndividualNote(String content, Integer week, Trainee trainee, QCStatus qcStatus,
			boolean isPublic) {
		if (isPublic)
			return new Note(content, week.shortValue(), trainee, NoteType.PUBLIC_TRAINEE, qcStatus);
		else
			return new Note(content, week.shortValue(), trainee, NoteType.QC_TRAINEE, qcStatus);
	}

	/**
	 * Factory method for creating a new Trainer weekly batch note
	 * 
	 * @param content
	 * @param week
	 * @param batch
	 * @return
	 */
	public static Note trainerBatchNote(String content, Integer week, Batch batch) {
		return new Note(content, week.shortValue(), batch);
	}

	/**
	 * Factory method for creating a new Trainer weekly individual trainee note
	 * 
	 * @param content
	 * @param week
	 * @param trainee
	 * @return
	 */
	public static Note trainerIndividualNote(String content, Integer week, Trainee trainee) {
		return new Note(content, week.shortValue(), trainee);
	}

	/**
	 * QC Status for the batch. Constructs the note and it's visibility If the
	 * feedback is public, anyone can view. If not, the feedback can only be
	 * viewed by QC and the VP.
	 * 
	 * @param content
	 * @param week
	 * @param batch
	 * @param maxVisibility
	 * @param type
	 * @param qcFeedback
	 * @param qcStatus
	 */
	private Note(String content, short week, Batch batch, NoteType type, QCStatus qcStatus) {
		this();
		this.content = content;
		this.week = week;
		this.batch = batch;
		if (type == NoteType.QC_BATCH)
			this.maxVisibility = TrainerRole.QC;
		else if (type == NoteType.PUBLIC_BATCH)
			this.maxVisibility = TrainerRole.TRAINER;
		else
			throw new IllegalArgumentException("Select proper NoteType");
		this.type = type;
		this.qcFeedback = true;
		this.qcStatus = qcStatus;
	}

	/**
	 * QC Status for each trainee. Constructs the note and it's visibility If
	 * the feedback is public, anyone can view. If not, the feedback can only be
	 * viewed by QC and the VP.
	 * 
	 * @param content
	 * @param week
	 * @param batch
	 * @param maxVisibility
	 * @param type
	 * @param qcFeedback
	 * @param qcStatus
	 */
	private Note(String content, short week, Trainee trainee, NoteType type, QCStatus qcStatus) {
		this();
		this.content = content;
		this.week = week;
		this.trainee = trainee;
		if (type == NoteType.QC_TRAINEE)
			this.maxVisibility = TrainerRole.QC;
		else if (type == NoteType.PUBLIC_TRAINEE)
			this.maxVisibility = TrainerRole.TRAINER;
		else
			throw new IllegalArgumentException("Select proper NoteType");
		this.type = type;
		this.qcFeedback = true;
		this.qcStatus = qcStatus;
	}

	/**
	 * Trainer feedback for a trainee
	 * 
	 * @param content
	 * @param week
	 * @param trainee
	 * @param maxVisibility
	 * @param type
	 */
	private Note(String content, short week, Trainee trainee) {
		this();
		this.content = content;
		this.week = week;
		this.trainee = trainee;
		this.maxVisibility = TrainerRole.TRAINER;
		this.type = NoteType.TRAINEE;
		this.qcFeedback = false;
	}

	/**
	 * Trainer feedback for a batch
	 * 
	 * @param content
	 * @param week
	 * @param trainee
	 * @param maxVisibility
	 * @param type
	 */
	private Note(String content, short week, Batch batch) {
		this();
		this.content = content;
		this.week = week;
		this.batch = batch;
		this.maxVisibility = TrainerRole.TRAINER;
		this.type = NoteType.BATCH;
		this.qcFeedback = false;
	}

	public Note() {
		super();
		this.maxVisibility = TrainerRole.TRAINER;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", content=" + content + ", week=" + week
				+ ", trainee=" + trainee + ", maxVisibility=" + maxVisibility + ", type=" + type
				+ ", qcFeedback=" + qcFeedback + ", qcStatus=" + qcStatus + "]";
	}

}
