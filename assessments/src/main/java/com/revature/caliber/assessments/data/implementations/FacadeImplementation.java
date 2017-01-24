package com.revature.caliber.assessments.data.implementations;


import java.util.List;
import java.util.Set;

import com.revature.caliber.assessments.beans.*;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.beans.BatchNote;
import com.revature.caliber.assessments.beans.Grade;
import com.revature.caliber.assessments.beans.TrainerNote;
import com.revature.caliber.assessments.data.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.revature.caliber.assessments.data.AssessmentDAO;
import com.revature.caliber.assessments.data.BatchNoteDAO;
import com.revature.caliber.assessments.data.CategoryDAO;
import com.revature.caliber.assessments.data.Facade;
import com.revature.caliber.assessments.data.GradeDAO;
import com.revature.caliber.assessments.data.NoteDAO;
import com.revature.caliber.assessments.data.QCNoteDAO;
import com.revature.caliber.assessments.data.QCStatusDAO;
import com.revature.caliber.assessments.data.TrainerNoteDAO;
import java.util.List;
import java.util.Set;


/**
 * Our implementation of the Facade interface
 */

@Component(value = "assessmentFacadeImplementation")
public class FacadeImplementation implements Facade {

    //  DI via Spring setter injection
    private AssessmentDAO assessmentDAO;
    private BatchNoteDAO batchNoteDAO;
//    private CategoryDAO categoryDAO;
    private GradeDAO gradeDAO;
    private NoteDAO noteDAO;
    private QCNoteDAO qcNoteDAO;
    private QCStatusDAO qcStatusDAO;
    private TrainerNoteDAO trainerNoteDAO;

    //  Spring setter based DI
    @Autowired
    public void setAssessmentDAO(AssessmentDAO assessmentDAO) {
        this.assessmentDAO = assessmentDAO;
    }

    @Autowired
    public void setBatchNoteDAO(BatchNoteDAO batchNoteDAO) {
        this.batchNoteDAO = batchNoteDAO;
    }

    /*    @Autowired
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }*/

    @Autowired
    public void setGradeDAO(GradeDAO gradeDAO) {
        this.gradeDAO = gradeDAO;
    }

    @Autowired
    public void setNoteDAO(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Autowired
    public void setQcNoteDAO(QCNoteDAO qcNoteDAO) {
        this.qcNoteDAO = qcNoteDAO;
    }

    @Autowired
    public void setQcStatusDAO(QCStatusDAO qcStatusDAO) {
        this.qcStatusDAO = qcStatusDAO;
    }

    @Autowired
    public void setTrainerNoteDAO(TrainerNoteDAO trainerNoteDAO) {
        this.trainerNoteDAO = trainerNoteDAO;
    }

//  Assessment
    // Get
    @Override
    public Set<Assessment> getAllAssessments() {
        return assessmentDAO.getAll();
    }

    @Override
    public Assessment getById(int id) {
        return assessmentDAO.getById(id);
    }

    @Override
    public Set<Assessment> getAssessmentsByWeekId(int id) {
        return assessmentDAO.getByWeekId(id);
    }

    //Create
    @Override
    public void insertAssessment(Assessment assessment) {
        assessmentDAO.insert(assessment);
    }

    //Update
    @Override
    public void updateAssessment(Assessment assessment) {
        assessmentDAO.update(assessment);
    }

    //Delete
    @Override
    public void deleteAssessment(Assessment assessment) {
        assessmentDAO.delete(assessment);
    }

//  BatchNote

    //BatchNote Facade Methods
    @Override
    public void makeBatchNote(int batchId, int weekId) {
        batchNoteDAO.createBatchNote(batchId, weekId);
    }

    @Override
    public BatchNote getWeeklyBatchNote(int batchId, int weekId) {
        return batchNoteDAO.getBatchNote(batchId, weekId);
    }

    @Override
    public List<BatchNote> allBatchNotesInWeek(int weekId) {
        return batchNoteDAO.allBatchNotesByWeek(weekId);
    }


// Grade

    //Gets
    @Override
    public List<Grade> getAllGrades() {
        return gradeDAO.getAllGrades();
    }

    @Override
    public Grade getGradeByGradeId(long gradeId) {
        return gradeDAO.getGradeByGradeId(gradeId);
    }

    @Override
    public List<Grade> getGradesByTraineeId(int traineeId) {
        return gradeDAO.getGradesByTraineeId(traineeId);
    }

    @Override
    public List<Grade> getGradesByAssesessment(long assessmentId) {
        return gradeDAO.getGradesByAssesessment(assessmentId);
    }

    //Insert
    @Override
    public void insertGrade(Grade grade) {
        gradeDAO.insertGrade(grade);

    }

    //Delete
    @Override
    public void deleteGrade(Grade grade) {
        gradeDAO.deleteGrade(grade);

    }

    //Update
    @Override
    public void updateGrade(Grade grade) {
        gradeDAO.updateGrade(grade);
    }

//    Trainer
    //TrainerNote Facade Methods
    @Override
    public void makeTrainerNote(TrainerNote trainerNote) {trainerNoteDAO.createTrainerNote(trainerNote);}

    @Override
    public Set<TrainerNote> getTrainerNoteByTrainerId(int trainerId) {return trainerNoteDAO.getTrainerNotesByTrainerId(trainerId);}

    @Override
    public TrainerNote getTrainerNoteForWeek(int trainerId, int weekId) {return trainerNoteDAO.getTrainerNoteForWeek(trainerId, weekId);}
    @Override
    public void updateTrainerNote(TrainerNote trainerNote) {trainerNoteDAO.updateTrainerNote(trainerNote);}

	//QCNote
    @Override
    public void createQCNote(QCNote note) { qcNoteDAO.createQCNote(note); }

    @Override
    public QCNote getQCNoteById(Integer QCNoteId) { return qcNoteDAO.getQCNoteById(QCNoteId); }

    @Override
    public QCNote getQCNoteForTraineeWeek(Integer traineeId, Integer weekId) { return qcNoteDAO.getQCNoteForTraineeWeek(traineeId, weekId); }

    @Override
    public List<QCNote> getQCNotesByTrainee(Integer traineeId) { return qcNoteDAO.getQCNotesByTrainee(traineeId); }

    @Override
    public List<QCNote> getQCNotesByWeek(Integer weekId) { return qcNoteDAO.getQCNotesByWeek(weekId); }

    @Override
    public void updateQCNote(QCNote note) { qcNoteDAO.updateQCNote(note); }

    @Override
    public void deleteQCNote(QCNote note) { qcNoteDAO.deleteQCNote(note); }
    //end QCNote

}
