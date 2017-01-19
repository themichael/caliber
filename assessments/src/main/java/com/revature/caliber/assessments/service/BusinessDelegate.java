package com.revature.caliber.assessments.service;

import com.revature.caliber.assessments.beans.Assessment;

import java.util.Set;

public interface BusinessDelegate {

//    Assessment
    Set<Assessment> getAllAssessments();

    Assessment getAssessmentById(int id);

    Set<Assessment> getAssessmentsByTrainerId(int id);

    Set<Assessment> getAssessmentsByWeekId(int id);

    Set<Assessment> getAssessmentsByBatchId(int id);

    void insertAssessment(Assessment assessment);

    void updateAssessment(Assessment assessment);

    void deleteAssessment(Assessment assessment);

//    Batch

}
