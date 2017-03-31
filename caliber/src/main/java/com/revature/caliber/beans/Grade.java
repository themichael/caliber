package com.revature.caliber.beans;

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

/**
 * The type Grade.
 */
@Entity
@Table(name = "CALIBER_GRADE")
public class Grade {
	
    @Id
    @Column(name = "GRADE_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRADE_ID_SEQUENCE")
    @SequenceGenerator(name = "GRADE_ID_SEQUENCE", sequenceName = "GRADE_ID_SEQUENCE")
    private long gradeId;
    
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "ASSESSMENT_ID", nullable = false)
    private Assessment assessment;
    
    @ManyToOne(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinColumn(name = "TRAINEE_ID", nullable = false)
    private Trainee trainee;
    
    @Column(name = "DATE_RECEIVED")
    @NotNull
    private Date dateReceived;
    
    @Column(name = "SCORE")
    @NotNull
    private int score;

    /**
     * Gets grade id.
     *
     * @return the grade id
     */
    public long getGradeId() {
        return gradeId;
    }

    /**
     * Sets grade id.
     *
     * @param gradeId the grade id
     */
    public void setGradeId(long gradeId) {
        this.gradeId = gradeId;
    }

    /**
     * Gets assessment.
     *
     * @return the assessment
     */
    public Assessment getAssessment() {
        return assessment;
    }

    /**
     * Sets assessment.
     *
     * @param assessment the assessment
     */
    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    /**
     * Gets trainee.
     *
     * @return the trainee
     */
    public Trainee getTrainee() {
        return trainee;
    }

    /**
     * Sets trainee.
     *
     * @param trainee the trainee
     */
    public void setTrainee(Trainee trainee) {
        this.trainee = trainee;
    }

    /**
     * Gets date received.
     *
     * @return the date received
     */
    public Date getDateReceived() {
        return dateReceived;
    }

    /**
     * Sets date received.
     *
     * @param dateReceived the date received
     */
    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Instantiates a new Grade.
     *
     * @param gradeId      the grade id
     * @param assessment   the assessment
     * @param trainee      the trainee
     * @param dateReceived the date received
     * @param score        the score
     */
    public Grade(long gradeId, Assessment assessment, Trainee trainee, Date dateReceived, int score) {
        super();
        this.gradeId = gradeId;
        this.assessment = assessment;
        this.trainee = trainee;
        this.dateReceived = dateReceived;
        this.score = score;
    }

    /**
     * Instantiates a new Grade.
     */
    public Grade() {
        super();
    }

    /**
     * Instantiates a new Grade.
     *
     * @param assessment   the assessment
     * @param trainee      the trainee
     * @param dateReceived the date received
     * @param score        the score
     */
    public Grade(Assessment assessment, Trainee trainee, Date dateReceived, int score) {
        super();
        this.assessment = assessment;
        this.trainee = trainee;
        this.dateReceived = dateReceived;
        this.score = score;
    }


}