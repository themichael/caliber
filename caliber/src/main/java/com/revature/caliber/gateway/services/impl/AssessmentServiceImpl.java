package com.revature.caliber.gateway.services.impl;

import java.util.List;
import java.util.Set;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.BatchNote;
import com.revature.caliber.beans.Category;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.QCNote;
import com.revature.caliber.beans.TrainerNote;
import com.revature.caliber.gateway.services.AssessmentService;

public class AssessmentServiceImpl implements AssessmentService {
    private String hostname;
    private String portNumber;
    

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
	public List<Grade> getGradesByAssessment(long assessmentId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void insertGrade(Grade grade) {
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
	public Set<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return null;
	}
	

    /////////// SETTERS ////////////////
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPortNumber(String portNumber) {
        this.portNumber = portNumber;
    }
	
	
}
