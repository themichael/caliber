package com.revature.caliber.assessments.service.implementations;


import com.revature.caliber.assessments.beans.QCNote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.Grade;
import com.revature.caliber.assessments.service.AssessmentService;
import com.revature.caliber.assessments.service.BatchService;
import com.revature.caliber.assessments.service.BusinessDelegate;
import com.revature.caliber.assessments.service.CategoryService;
import com.revature.caliber.assessments.service.GradeService;
import com.revature.caliber.assessments.service.NoteService;
import com.revature.caliber.assessments.service.QCNoteService;
import com.revature.caliber.assessments.service.QCStatusService;
import com.revature.caliber.assessments.service.TrainerNoteService;

@Component(value ="delegate")
public class BusinessDelegateImpl implements BusinessDelegate {

    private AssessmentService assessmentService;
    private BatchService batchService;
    //private CategoryService categoryService;
    private GradeService gradeService;
    //TODO finish service impl: private NoteService noteService;
    private QCNoteService qcNoteService;
    //TODO finish service impl: private QCStatusService qcStatusService;
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

    /*   
     * 	TODO reconsider how to approach this implementation.
     * 		 data resides in another service, so you cannot query this way
    @Override
    public HashSet<Assessment> getAssessmentsByTrainerId(int id) {
        return (HashSet<Assessment>) assessmentService.getByTrainerId(id);
    }
    */

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




//    Spring setter based DI
    @Autowired
    public void setAssessmentService(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }
    @Autowired
    public void setBatchService(BatchService batchService) {
        this.batchService = batchService;
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

    }*/
    @Autowired
    public void setQcNoteService(QCNoteService qcNoteService) {
        this.qcNoteService = qcNoteService;
    }
    /*
        @Autowired
        public void setQcStatusService(QCStatusService qcStatusService) {
            this.qcStatusService = qcStatusService;
        }*/
    @Autowired
    public void setTrainerNoteService(TrainerNoteService trainerNoteService) {
        this.trainerNoteService = trainerNoteService;
    }

    //QC Note -------------------
    @Override
    public void createQCNote(QCNote note) { qcNoteService.createQCNote(note); }

    @Override
    public QCNote getQCNoteById(Integer QCNoteId) { return qcNoteService.getQCNoteById(QCNoteId); }

    @Override
    public QCNote getQCNoteForTraineeWeek(Integer traineeId, Integer weekId) { return qcNoteService.getQCNoteForTraineeWeek(traineeId, weekId); }

    @Override
    public List<QCNote> getQCNotesByTrainee(Integer traineeId) { return qcNoteService.getQCNotesByTrainee(traineeId); }

    @Override
    public List<QCNote> getQCNotesByWeek(Integer weekId) { return qcNoteService.getQCNotesByWeek(weekId); }

    @Override
    public void updateQCNote(QCNote note) { qcNoteService.updateQCNote(note); }

    @Override
    public void deleteQCNote(QCNote note) { qcNoteService.deleteQCNote(note); }
    //end QCNote ---------------------
}
