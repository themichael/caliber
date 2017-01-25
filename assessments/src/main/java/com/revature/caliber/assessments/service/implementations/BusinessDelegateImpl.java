package com.revature.caliber.assessments.service.implementations;




import com.revature.caliber.assessments.beans.QCNote;
import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.BatchNote;
import com.revature.caliber.assessments.beans.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.revature.caliber.assessments.service.*;
import com.revature.caliber.assessments.service.AssessmentService;
import com.revature.caliber.assessments.service.BatchNoteService;
import com.revature.caliber.assessments.service.BusinessDelegate;
import com.revature.caliber.assessments.service.GradeService;
import com.revature.caliber.assessments.service.TrainerNoteService;
import com.revature.caliber.assessments.beans.TrainerNote;
import com.revature.caliber.assessments.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component(value = "delegate")
public class BusinessDelegateImpl implements BusinessDelegate {

    private AssessmentService assessmentService;
    private BatchNoteService batchNoteService;
    //private CategoryService categoryService;
    private GradeService gradeService;
    //TODO finish service impl: private NoteService noteService;
    private QCNoteService qcNoteService;
    //TODO finish service impl: private QCStatusService qcStatusService;
    private TrainerNoteService trainerNoteService;

    //    Assessment
    @Override
    public Set<Assessment> getAllAssessments() {
        return assessmentService.getAll();
    }

    @Override
    public Assessment getAssessmentById(long id) {
        return assessmentService.getById(id);
    }

    @Override
    public Set<Assessment> getAssessmentsByWeekId(long id) {
        return assessmentService.getByWeekId(id);
    }

    @Override
    public long insertAssessment(Assessment assessment) {
        return assessmentService.insert(assessment);
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
    public Grade getGradeByGradeId(long gradeId) {
        return gradeService.getGradeByGradeId(gradeId);
    }

    @Override
    public List<Grade> getGradesByTraineeId(int traineeId) {
        return gradeService.getGradesByTraineeId(traineeId);
    }

    @Override
    public List<Grade> getGradesByAssessment(long assessmentId) {
        return gradeService.getGradesByAssessment(assessmentId);
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

    // Trainer Note
    @Override
    public void createTrainerNote(TrainerNote note) {trainerNoteService.createTrainerNote(note);}
    public TrainerNote getTrainerNoteById(Integer trainerNoteId) {return trainerNoteService.getTrainerNoteById(trainerNoteId);}
    public TrainerNote getTrainerNoteForTrainerWeek(Integer trainerId, Integer weekId) {return trainerNoteService.getTrainerNoteForTrainerWeek(trainerId,weekId);}
    public Set<TrainerNote> getTrainerNotesByTrainer(Integer trainerId) {return trainerNoteService.getTrainerNotesByTrainer(trainerId);}
    public Set<TrainerNote> getTrainerNotesByWeek(Integer weekId) {return trainerNoteService.getTrainerNotesByWeek(weekId);}
    public void updateTrainerNote(TrainerNote note) {trainerNoteService.updateTrainerNote(note);}
    public void deleteTrainerNote(TrainerNote note) {trainerNoteService.deleteTrainerNote(note);}
    // end trainer note

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

    
    //BatchNote 
	@Override
	public void makeBatchNote(BatchNote batchNote) {
		batchNoteService.createBatchNote(batchNote);
	}

	@Override
	public BatchNote weeklyBatchNote(int batchId, int weekId) {
		return batchNoteService.weeklyBatchNote(batchId, weekId);
	}

	@Override
	public List<BatchNote> allBatchNotesInWeek(int weekId) {
		return batchNoteService.allBatchNotesInWeek(weekId);
	}

	@Override
	public void updateBatchNote(BatchNote batchNote) {
		batchNoteService.updateBatchNote(batchNote);
	}
}
