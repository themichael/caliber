package com.revature.caliber.assessments.data.implementations;

import java.util.List;
import java.util.Set;

import com.revature.caliber.assessments.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.caliber.assessments.data.AssessmentDAO;
import com.revature.caliber.assessments.data.BatchNoteDAO;
import com.revature.caliber.assessments.data.CategoryDAO;
import com.revature.caliber.assessments.data.Facade;
import com.revature.caliber.assessments.data.GradeDAO;
import com.revature.caliber.assessments.data.NoteDAO;
import com.revature.caliber.assessments.data.QCNoteDAO;
import com.revature.caliber.assessments.data.QCStatusDAO;
import com.revature.caliber.assessments.data.TrainerNoteDAO;

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
    public Set<Assessment> getAllAssessments() {
        return assessmentDAO.getAll();
    }

    @Transactional
    @Override
    public Assessment getById(int id) {
        return assessmentDAO.getById(id);
    }

    /*   
     * 	TODO reconsider how to approach this implementation.
     * 		 data resides in another service, so you cannot query this way
    @Transactional
    @Override
    public Set<Assessment> getAssessmentsByTrainerId(int id) {
        return assessmentDAO.getByTrainerId(id);
    }
    */

    @Transactional
    @Override
    public Set<Assessment> getAssessmentsByWeekId(int id) {
        return assessmentDAO.getByWeekId(id);
    }

    @Transactional
    @Override
    public Set<Assessment> getAssessmentsByBatchId(int id) {
        return assessmentDAO.getByBatchId(id);
    }

    //Create
    @Transactional(
            isolation=Isolation.READ_COMMITTED,
            rollbackFor=Exception.class,
            propagation=Propagation.REQUIRES_NEW)
    @Override
    public void insertAssessment(Assessment assessment) {
        assessmentDAO.insert(assessment);
    }

    //Update
    @Transactional(
            isolation=Isolation.READ_COMMITTED,
            rollbackFor=Exception.class,
            propagation=Propagation.REQUIRES_NEW)
    @Override
    public void updateAssessment(Assessment assessment) {
        assessmentDAO.update(assessment);
    }

    //Delete
    @Transactional(isolation=Isolation.READ_COMMITTED,
            rollbackFor=Exception.class,
            propagation=Propagation.REQUIRES_NEW)
    @Override
    public void deleteAssessment(Assessment assessment) {
        assessmentDAO.delete(assessment);
    }

//  Batch
    // Get

    // Create

    // Update

    // Delete

    
// Grade

    //Gets
	@Override
	public List<Grade> getAllGrades() {
		return gradeDAO.getAllGrades();
	}
	
	@Override
	public Grade getGradeByGradeId(int gradeId) {
		return gradeDAO.getGradeByGradeId(gradeId);
	}

	@Override
	public List<Grade> getGradesByTraineeId(int traineeId) {
		return gradeDAO.getGradesByTraineeId(traineeId);
	}

	@Override
	public List<Grade> getGradesByAssesessment(int assessmentId) {
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

	
	//TrainerNote Facade Methods
	@Override
	public void makeTrainerNote(int trainerId) {
		trainerNoteDAO.createTrainerNote(trainerId);
	}

	@Override
	public List<TrainerNote> listTrainerNotes(int trainerId) {
		return trainerNoteDAO.getAllTrainerNotesByTrainer(trainerId);
	}

	@Override
	public TrainerNote getTrainerNoteForWeek(int trainerId, int weekId) {
		return trainerNoteDAO.getTrainerNoteForWeek(trainerId, weekId);
	}

	//QCNote
    @Override
    public void createQCNote(QCNote note) { qcNoteDAO.createQCNote(note); }

    @Override
    public QCNote getNoteById(Integer QCNoteId) { return qcNoteDAO.getNoteById(QCNoteId); }

    @Override
    public QCNote getNoteForTraineeWeek(Integer traineeId, Integer weekId) { return qcNoteDAO.getNoteForTraineeWeek(traineeId, weekId); }

    @Override
    public List<QCNote> getQCNotesByTrainee(Integer traineeId) { return qcNoteDAO.getQCNotesByTrainee(traineeId); }

    @Override
    public List<QCNote> getQCNotesByWeek(Integer weekId) { return qcNoteDAO.getQCNotesByWeek(weekId); }
    //end QCNote
}
