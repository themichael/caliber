package com.revature.caliber.assessment.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

/**
 * Java Bean for Grade Object
 */
public class Grade {

    /**
     * gradeId- primary key for Grade table
     */
    private long gradeId;

    /**
     * Assessment - A trainee received a grade on specified assessment
     */
    private Assessment assessment;

    /**
     * Trainee- the trainee that receives this Grade object
     */
    private int trainee;

    /**
     * dateReceived- date this Grade object was received
     */
    @JsonIgnore
    private Date dateReceived;

    /**
     * score - score of the grade
     */
    private int score;

    public Grade() {
        super();
    }

    public Grade(long gradeId, Assessment assessment, int trainee, Date dateReceived, int score) {
        super();
        this.gradeId = gradeId;
        this.assessment = assessment;
        this.trainee = trainee;
        this.dateReceived = dateReceived;
        this.score = score;
    }

    public Grade(Assessment assessment, int trainee, Date dateReceived, int score) {
        super();
        this.assessment = assessment;
        this.trainee = trainee;
        this.dateReceived = dateReceived;
        this.score = score;
    }

    public long getGradeId() {
        return gradeId;
    }

    public void setGradeId(long gradeId) {
        this.gradeId = gradeId;
    }

    public Assessment getAssessment() {
        return assessment;
    }

    public void setAssessment(Assessment assessment) {
        this.assessment = assessment;
    }

    public int getTrainee() {
        return trainee;
    }

    public void setTrainee(int trainee) {
        this.trainee = trainee;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Grade [gradeId=" + gradeId + ", assessment=" + assessment + ", trainee=" + trainee + ", dateReceived="
                + dateReceived + ", score=" + score + "]";
    }


}