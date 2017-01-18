package com.revature.caliber.assessments.data.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

/**
 * Our implementation of the Facade interface
 */

@Component(value = "assessmentFacadeImplementation")
public class FacadeImplementation implements Facade {

    //  DI via Spring setter injection
    private AssessmentDAO assessmentDAO;
    private BatchNoteDAO batchNoteDAO;
    private CategoryDAO categoryDAO;
    private GradeDAO gradeDAO;
    private NoteDAO noteDAO;
    private QCNoteDAO qcNoteDAO;
    private QCStatusDAO qcStatusDAO;
    private TrainerNoteDAO trainerNoteDAO;


//  Organize methods by DAO like Assessment example above, e.g.

//  Assessment

    // Get
    @Transactional
    @Override
    public HashSet<Assessment> getAll() {
        return assessmentDAO.getAll();
    }

    @Transactional
    @Override
    public HashSet<Assessment> getByTrainerId(int id) {
        return assessmentDAO.getByTrainerId(id);
    }

    @Transactional

    @Override
    public HashSet<Assessment> getByWeekId(int id) {
        return assessmentDAO.getByWeekId(id);
    }

    @Transactional

    @Override
    public HashSet<Assessment> getByBatchId(int id) {
        return assessmentDAO.getByBatchId(id);
    }

    //Create
    @Transactional(
            isolation=Isolation.READ_COMMITTED,
            rollbackFor=Exception.class,
            propagation=Propagation.REQUIRES_NEW)
    @Override
    public void insert(Assessment assessment) {
        assessmentDAO.insert(assessment);
    }

    //Update
    @Transactional(
            isolation=Isolation.READ_COMMITTED,
            rollbackFor=Exception.class,
            propagation=Propagation.REQUIRES_NEW)
    @Override
    public void update(Assessment assessment) {
        assessmentDAO.update(assessment);
    }

    //Delete
    @Transactional(isolation=Isolation.READ_COMMITTED,
            rollbackFor=Exception.class,
            propagation=Propagation.REQUIRES_NEW)
    @Override
    public void delete(Assessment assessment) {
        assessmentDAO.delete(assessment);
    }

//  Batch
    // Get

    // Create

    // Update

    // Delete





//  Spring setter based DI
    @Autowired
    public void setAssessmentDAO(AssessmentDAO assessmentDAO) {
        this.assessmentDAO = assessmentDAO;
    }

    @Autowired
    public void setBatchNoteDAO(BatchNoteDAO batchNoteDAO) {
        this.batchNoteDAO = batchNoteDAO;
    }

    @Autowired
    public void setCategoryDAO(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

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
}
