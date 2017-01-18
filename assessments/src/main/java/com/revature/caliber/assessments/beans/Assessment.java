package com.revature.caliber.assessments.beans;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CALIBER_ASSESSMENT")
public class Assessment {

    @Id
    @Column(name = "ASSESSMENT_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long assessmentId;

    @Column(name = "TITLE", nullable = false)
    private String title;

    @Column(name = "BATCH_ID", nullable = false)
    private int batch;

    @Column(name = "RAW_SCORE")
    private int rawScore;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "WEEK_ID", nullable = false)
    private int week;

    // Bi-directional mapping -- to avoid recursion, make DTO to send to UI
    @ManyToOne
    @JoinColumn(name = "WEEKLY_STATUS")
    private QCStatus weeklyStatus;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Category> categories;

    public Assessment(long assessmentId,
                      String title,
                      int batch,
                      int rawScore,
                      String type,
                      int week,
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
                      int week,
                      Set<Category> categories) {
        super();
        this.title = title;
        this.batch = batch;
        this.rawScore = rawScore;
        this.type = type;
        this.week = week;
        this.categories = categories;
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

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
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
