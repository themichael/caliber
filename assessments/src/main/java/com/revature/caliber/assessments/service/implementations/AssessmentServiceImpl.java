package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.data.Facade;
import com.revature.caliber.assessments.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service(value = "assessmentService")
public class AssessmentServiceImpl implements AssessmentService {

    private Facade facade;
    //    Spring setter based DI
    @Autowired
    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    @Override
    public Set<Assessment> getAll() {
        return facade.getAllAssessments();
    }

    @Override
    public Assessment getById(int id) {
        return facade.getById(id);
    }

    @Override
    public Set<Assessment> getByWeekId(int id) {
        return facade.getAssessmentsByWeekId(id);
    }

    @Override
    public void insert(Assessment assessment) {
        facade.insertAssessment(assessment);
    }

    @Override
    public void update(Assessment assessment) {
        facade.updateAssessment(assessment);
    }

    @Override
    public void delete(Assessment assessment) {
        facade.deleteAssessment(assessment);
    }
}
