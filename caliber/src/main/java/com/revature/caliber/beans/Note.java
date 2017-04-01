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

@Entity
@Table(name="CALIBER_NOTE")
public class Note {

	@Id
	@Column(name="NOTE_ID", nullable=false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="NOTE_ID_SEQUENCE")
	@SequenceGenerator(name = "NOTE_ID_SEQUENCE", sequenceName= "NOTE_ID_SEQUENCE")
	private int noteId;
	
	@Column(name="NOTE_CONTENT")
	private String content;
	
	@Column(name="WEEK_NUMBER")
	private short week;
	
	/**
	 * Will be null if the note is individual trainee feedback
	 */
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="BATCH_ID", nullable=true)
	private Batch batch;
	
	/**
	 * Will be null if the note is overall batch feedback
	 */
	@ManyToOne(cascade=CascadeType.PERSIST, fetch=FetchType.LAZY)
	@JoinColumn(name="TRAINEE_ID", nullable=true)
	private Trainee trainee;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="MAX_VISIBILITY")
	private TrainerRole maxVisibility;
	
	@Enumerated(EnumType.STRING)
	@Column(name="NOTE_TYPE")
	private NoteType type;
	
	@Column(name="IS_QC_FEEDBACK", nullable=false)
	private boolean qcFeedback;
	
	@Enumerated(EnumType.STRING)
	@Column(name="QC_STATUS", nullable=true)
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

	public Note(String content, short week, Batch batch, Trainee trainee, TrainerRole maxVisibility, NoteType type,
			boolean qcFeedback, QCStatus qcStatus) {
		super();
		this.content = content;
		this.week = week;
		this.batch = batch;
		this.trainee = trainee;
		this.maxVisibility = maxVisibility;
		this.type = type;
		this.qcFeedback = qcFeedback;
		this.qcStatus = qcStatus;
	}

	public Note() {
		super();
	}
	
}
