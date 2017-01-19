package com.revature.caliber.assessments.service;

import com.revature.caliber.assessments.beans.Assessment;

import java.util.HashSet;

public interface BusinessDelegate {

//    Assessment
    HashSet<Assessment> getAllAssessments();

    Assessment getAssessmentById(int id);

    HashSet<Assessment> getAssessmentsByTrainerId(int id);

    HashSet<Assessment> getAssessmentsByWeekId(int id);

    HashSet<Assessment> getAssessmentsByBatchId(int id);

    void insertAssessment(Assessment assessment);

    void updateAssessment(Assessment assessment);

    void deleteAssessment(Assessment assessment);

//    Batch

}
