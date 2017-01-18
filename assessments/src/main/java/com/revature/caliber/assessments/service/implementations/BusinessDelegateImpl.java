package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.data.Facade;
import com.revature.caliber.assessments.service.BusinessDelegate;

import java.util.HashSet;

public class BusinessDelegateImpl implements BusinessDelegate{

    private Facade facade;

//    Spring setter based DI
    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    @Override
    public HashSet<Assessment> getAllAssessments() {
        return null;
    }

    @Override
    public HashSet<Assessment> getAssessmentsByTrainerId(int id) {
        return null;
    }

    @Override
    public HashSet<Assessment> getAssessmentsByWeekId(int id) {
        return null;
    }

    @Override
    public HashSet<Assessment> getAssessmentsByBatchId(int id) {
        return null;
    }

    @Override
    public void insertAssessment(Assessment assessment) {

    }

    @Override
    public void updateAssessment(Assessment assessment) {

    }

    @Override
    public void deleteAssessment(Assessment assessment) {

    }
}
