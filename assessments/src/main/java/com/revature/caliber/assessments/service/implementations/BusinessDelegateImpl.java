package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

@Component(value ="delegate")
public class BusinessDelegateImpl implements BusinessDelegate {

    private AssessmentService assessmentService;
    private BatchService batchService;
    private CategoryService categoryService;
    private GradeService gradeService;
    private NoteService noteService;
    private QCNoteService qcNoteService;
    private QCStatusService qcStatusService;
    private TrainerNoteService trainerNoteService;

//    Assessment
    @Override
    public HashSet<Assessment> getAllAssessments() {
        return (HashSet<Assessment>) assessmentService.getAll();
    }

    @Override
    public Assessment getAssessmentById(int id) {
        return assessmentService.getById(id);
    }

    @Override
    public HashSet<Assessment> getAssessmentsByTrainerId(int id) {
        return (HashSet<Assessment>) assessmentService.getByTrainerId(id);
    }

    @Override
    public HashSet<Assessment> getAssessmentsByWeekId(int id) {
        return (HashSet<Assessment>) assessmentService.getByWeekId(id);
    }

    @Override
    public HashSet<Assessment> getAssessmentsByBatchId(int id) {
        return (HashSet<Assessment>) assessmentService.getByBatchId(id);
    }

    @Override
    public void insertAssessment(Assessment assessment) {
        assessmentService.insert(assessment);
    }

    @Override
    public void updateAssessment(Assessment assessment) {
        assessmentService.update(assessment);
    }

    @Override
    public void deleteAssessment(Assessment assessment) {
        assessmentService.delete(assessment);
    }

//    Batch








//    Spring setter based DI
    @Autowired
    public void setAssessmentService(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }
    @Autowired
    public void setBatchService(BatchService batchService) {
        this.batchService = batchService;
    }
    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Autowired
    public void setGradeService(GradeService gradeService) {
        this.gradeService = gradeService;
    }
    @Autowired
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }
    @Autowired
    public void setQcNoteService(QCNoteService qcNoteService) {
        this.qcNoteService = qcNoteService;
    }
    @Autowired
    public void setQcStatusService(QCStatusService qcStatusService) {
        this.qcStatusService = qcStatusService;
    }
    @Autowired
    public void setTrainerNoteService(TrainerNoteService trainerNoteService) {
        this.trainerNoteService = trainerNoteService;
    }

}
