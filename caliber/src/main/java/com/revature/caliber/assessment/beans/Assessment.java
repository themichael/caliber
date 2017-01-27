package com.revature.caliber.assessment.beans;

import java.util.Set;

/**
 * This is the hibernate annotated bean that for the
 * CALIBER_ASSESSMENT table in the database
 */

public class Assessment {

    /**
     * This is the PK ID using a unique sequence generator
     */
    private long assessmentId;

    /**
     * Trainer inputted title,
     * can be anything to help identify this assessment
     */
    private String title;

    /**
     * Batch ID reference
     */
    private int batch;

    /**
     * Raw numerical score before calculations
     */
    private int rawScore;

    /**
     * Assessment type, e.g. LMS, Verbal
     */
    private String type;

    /**
     * WeekID for reference
     */
    private long week;

    /**
     * QCStatus for a week, statuses can be
     * good, ok, bad
     * indicated with smiley, meh and frowny face
     */
    private QCStatus weeklyStatus;

    /**
     * Set of Categories for Assessments (for Hibernate ORM)
     */
    private Set<Category> categories;

    public Assessment(long assessmentId,
                      String title,
                      int batch,
                      int rawScore,
                      String type,
                      long week,
                      Set<Category> categories) {
        super();
        this.assessmentId = assessmentId;
        this.title = title;
        this.batch = batch;
        this.rawScore = rawScore;
        this.type = type;
        this.week = week;
        this.categories = categories;
    }

    public Assessment() {
        super();
    }

    public Assessment(String title,
                      int batch,
                      int rawScore,
                      String type,
                      long week,
                      Set<Category> categories) {
        super();
        this.title = title;
        this.batch = batch;
        this.rawScore = rawScore;
        this.type = type;
        this.week = week;
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentId=" + assessmentId +
                ", title='" + title + '\'' +
                ", batch=" + batch +
                ", rawScore=" + rawScore +
                ", type='" + type + '\'' +
                ", week=" + week +
                ", weeklyStatus=" + weeklyStatus.getStatus() +
                ", categories=" + categories +
                '}';
    }

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

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getRawScore() {
        return rawScore;
    }

    public void setRawScore(int rawScore) {
        this.rawScore = rawScore;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getWeek() {
        return week;
    }

    public void setWeek(long week) {
        this.week = week;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public QCStatus getWeeklyStatus() {
        return weeklyStatus;
    }

    public void setWeeklyStatus(QCStatus weeklyStatus) {
        this.weeklyStatus = weeklyStatus;
    }

}