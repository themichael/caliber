package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.Category;
import com.revature.caliber.assessments.beans.Grade;
import com.revature.caliber.assessments.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component(value = "delegate")
public class BusinessDelegateImpl implements BusinessDelegate {

    private AssessmentService assessmentService;
    private BatchNoteService batchNoteService;
    private CategoryService categoryService;
    private GradeService gradeService;
    //TODO finish service impl: private NoteService noteService;
    //TODO finish service impl: private QCNoteService qcNoteService;
    //TODO finish service impl: private QCStatusService qcStatusService;
    private TrainerNoteService trainerNoteService;

    //    Spring setter based DI
    @Autowired
    public void setAssessmentService(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @Autowired
    public void setBatchNoteService(BatchNoteService batchService) {
        this.batchNoteService = batchNoteService;
    }

    /* TODO wire the beans
    *
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

    }*/
    @Autowired
    public void setTrainerNoteService(TrainerNoteService trainerNoteService) {
        this.trainerNoteService = trainerNoteService;
    }

//    Assessment
    @Override
    public Set<Assessment> getAllAssessments() {
        return assessmentService.getAll();
    }

    @Override
    public Assessment getAssessmentById(int id) {
        return assessmentService.getById(id);
    }

    @Override
    public Set<Assessment> getAssessmentsByWeekId(int id) {
        return assessmentService.getByWeekId(id);
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

//    Category
    @Override
    public Set<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @Override
    public Category getCategoryById(int id) {
        return categoryService.getById(id);
    }

// Grade
    @Override
    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }

    @Override
    public Grade getGradeByGradeId(int gradeId) {
        return gradeService.getGradeByGradeId(gradeId);
    }

    @Override
    public List<Grade> getGradesByTraineeId(int traineeId) {
        return gradeService.getGradesByTraineeId(traineeId);
    }

    @Override
    public List<Grade> getGradesByAssesessment(int assessmentId) {
        return gradeService.getGradesByAssesessment(assessmentId);
    }

    @Override
    public void insertGrade(Grade grade) {
        gradeService.insertGrade(grade);
    }

    @Override
    public void deleteGrade(Grade grade) {
        gradeService.deleteGrade(grade);
    }

    @Override
    public void updateGrade(Grade grade) {
        gradeService.updateGrade(grade);
    }

}
