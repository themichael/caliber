package com.revature.caliber.assessments.service;

import com.revature.caliber.assessments.beans.Assessment;

import java.util.HashSet;

public interface BusinessDelegate {

    //    Assessment
    HashSet<Assessment> getAllAssessments();

    HashSet<Assessment> getAssessmentsByTrainerId(int id);

    HashSet<Assessment> getByWeekId(int id);

    HashSet<Assessment> getByBatchId(int id);

    void insert(Assessment assessment);

    void update(Assessment assessment);

    void delete(Assessment assessment);

//    Batch

}
