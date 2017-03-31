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
import javax.validation.constraints.NotNull;

/**
 * Notes for trainee-level and batch-level for each week.
 * 
 * If it is a Trainee note, the Batch should be null.
 * If it is a Batch note, the Trainee should be null.
 * 
 * If it is a QC Audit, set boolean and QCStatus. 
 * QC Audit can be Trainee- or Batch-level
 * 
 * @author Patrick Walsh
 *
 */
@Entity(name = "CALIBER_NOTE")
public class Note {

	@Id
	@Column(name="NOTE_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTE_ID_SEQUENCE")
	@SequenceGenerator(name = "NOTE_ID_SEQUENCE", sequenceName = "NOTE_ID_SEQUENCE")
	private int noteId;
	
	/**
	 * Use this when evaluating a trainee for a week
	 */
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="TRAINEE_ID", nullable=true)
	private Trainee trainee;
	
	/**
	 * Use this when evaluating a batch for a week
	 */
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="BATCH_ID", nullable=true)
	private Batch batch;
	
	/**
	 * Which week was the trainee/batch evaluated
	 */
	@Column(name="WEEK_NUMBER", nullable=false)
	@NotNull
	private short week;
	
	@Column(name="NOTE_CONTENT")
	private String content;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="NOTE_TYPE")
	private NoteType noteType;
	
	@Column(name="MAX_VISIBILITY")
	private int maxVisibility; 
	
	@Column(name="IS_QC_FEEDBACK")
	private boolean qcFeedback;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="QC_STATUS")
	private QCStatus qcStatus;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public Trainee getTrainee() {
		return trainee;
	}

	public void setTrainee(Trainee trainee) {
		this.trainee = trainee;
	}

	public Batch getBatch() {
		return batch;
	}

	public void setBatch(Batch batch) {
		this.batch = batch;
	}

	public short getWeek() {
		return week;
	}

	public void setWeek(short week) {
		this.week = week;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public NoteType getNoteType() {
		return noteType;
	}

	public void setNoteType(NoteType noteType) {
		this.noteType = noteType;
	}
	
	public int getMaxVisibility() {
		return maxVisibility;
	}

	public void setMaxVisibility(int maxVisibility) {
		this.maxVisibility = maxVisibility;
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
	 * Trainee-level note for a week. 
	 * Includes trainer and QC qualitative feedback
	 * as well as QC marking for individual eval (red,yellow,green,superstar,etc)
	 * @param trainee
	 * @param week
	 * @param content
	 * @param noteType
	 */
	public Note(Trainee trainee, short week, String content, 
			NoteType noteType, NoteVisibility maxVisibility,
			boolean qcFeedback) {
		super();
		this.trainee = trainee;
		this.week = week;
		this.content = content;
		this.noteType = noteType;
		this.maxVisibility = maxVisibility.getRole();
		this.qcFeedback = qcFeedback;
	}

	/**
	 * Batch-level note for a week. 
	 * Includes trainer and QC qualitative feedback
	 * as well as QC marking for individual eval (red,yellow,green,etc)
	 * @param batch
	 * @param week
	 * @param content
	 * @param noteType
	 */
	public Note(Batch batch, short week, String content, NoteType noteType,
			NoteVisibility maxVisibility, boolean qcFeedback) {
		super();
		this.batch = batch;
		this.week = week;
		this.content = content;
		this.noteType = noteType;
		this.maxVisibility = maxVisibility.getRole();
		this.qcFeedback = qcFeedback;
	}

	public Note() {
		super();
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", trainee=" + trainee + ", batch=" + batch + ", week=" + week + ", content="
				+ content + ", noteType=" + noteType + ", maxVisibility=" + maxVisibility + "]";
	}

}
