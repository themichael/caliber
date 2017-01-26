package com.revature.caliber.gateway.services.impl;

import com.revature.caliber.beans.*;
import com.revature.caliber.gateway.services.AssessmentService;

import java.util.List;
import java.util.Set;

/**
 * The type Assessment service.
 */
public class AssessmentServiceImpl implements AssessmentService {
    private String hostname;
    private String portNumber;
    
    @Override
	public Set<Assessment> getAllAssessments() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Assessment getAssessmentById(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Assessment> getAssessmentsByWeekId(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public long insertAssessment(Assessment assessment) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void updateAssessment(Assessment assessment) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteAssessment(Assessment assessment) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Grade> getAllGrades() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Grade getGradeByGradeId(long gradeId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Grade> getGradesByTraineeId(int traineeId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Grade> getGradesByAssessment(long assessmentId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void insertGrade(Grade grade) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteGrade(Grade grade) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateGrade(Grade grade) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void makeBatchNote(BatchNote batchNote) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public BatchNote weeklyBatchNote(int batchId, int weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<BatchNote> allBatchNotesInWeek(int weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<BatchNote> allBatchNotes(int batchId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateBatchNote(BatchNote batchNote) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteBatchNote(BatchNote batchNote) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createQCNote(QCNote note) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public QCNote getQCNoteById(Integer qcNoteId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public QCNote getQCNoteForTraineeWeek(Integer traineeId, Integer weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<QCNote> getQCNotesByTrainee(Integer traineeId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<QCNote> getQCNotesByWeek(Integer weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateQCNote(QCNote note) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteQCNote(QCNote note) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createTrainerNote(TrainerNote note) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public TrainerNote getTrainerNoteById(Integer trainerNoteId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public TrainerNote getTrainerNoteForTrainerWeek(Integer trainerId, Integer weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<TrainerNote> getTrainerNotesByTrainer(Integer trainerId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<TrainerNote> getTrainerNotesByWeek(Integer weekId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void updateTrainerNote(TrainerNote note) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteTrainerNote(TrainerNote note) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Note getNote(String note) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Note> getAllNotes() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Set<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Category getCategoryById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Sets hostname.
     *
     * @param hostname the hostname
     */
/////////// SETTERS ////////////////
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * Sets port number.
     *
     * @param portNumber the port number
     */
    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }
	
}
