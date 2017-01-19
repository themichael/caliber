package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.data.Facade;
import com.revature.caliber.assessments.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;

public class AssessmentServiceImpl implements AssessmentService {

    private Facade facade;
    //    Spring setter based DI
    @Autowired
    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    @Override
    public HashSet<Assessment> getAllAssessments() {
        return facade.getAllAssessments();
    }

    @Override
    public HashSet<Assessment> getAssessmentsByTrainerId(int id) {
        return facade.getAssessmentsByTrainerId(id);
    }

    @Override
    public HashSet<Assessment> getAssessmentsByWeekId(int id) {
        return facade.getAssessmentsByWeekId(id);
    }

    @Override
    public HashSet<Assessment> getAssessmentsByBatchId(int id) {
        return facade.getAssessmentsByBatchId(id);
    }

    @Override
    public void insertAssessment(Assessment assessment) {
        facade.insertAssessment(assessment);
    }

    @Override
    public void updateAssessment(Assessment assessment) {
        facade.updateAssessment(assessment);
    }

    @Override
    public void deleteAssessment(Assessment assessment) {
        facade.deleteAssessment(assessment);
    }
}
