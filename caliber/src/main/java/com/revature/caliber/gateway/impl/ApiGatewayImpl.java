package com.revature.caliber.gateway.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.BatchNote;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.TrainerNote;
import com.revature.caliber.gateway.ApiGateway;
import com.revature.caliber.gateway.services.ServiceLocator;

@Component
public class ApiGatewayImpl implements ApiGateway {

    @Autowired
    private ServiceLocator serviceLocator;

    /****************************Batch*******************************/
    public void createBatch(Batch batch) {
        serviceLocator.getTrainingService().createBatch(batch);
    }

    public List<Batch> allBatch() {
        return serviceLocator.getTrainingService().allBatch();
    }

    public List<Batch> getBatches(Integer id) {
        return serviceLocator.getTrainingService().getBatches(id);
    }

    public List<Batch> currentBatch() {
        return serviceLocator.getTrainingService().currentBatch();
    }

    public List<Batch> currentBatch(Trainer trainer) {
        return serviceLocator.getTrainingService().currentBatch(trainer);
    }

    public Batch getBatch(Integer id) {
        return serviceLocator.getTrainingService().getBatch(id);
    }

    public void updateBatch(Batch batch) {
        serviceLocator.getTrainingService().updateBatch(batch);
    }

    public void deleteBatch(Batch batch) {
        serviceLocator.getTrainingService().deleteBatch(batch);
    }

    /****************************Trainee*******************************/
    @Override
    public void createTrainee(Trainee trainee) {
        serviceLocator.getTrainingService().createTrainee(trainee);
    }

    @Override
    public void updateTrainee(Trainee trainee) {
        serviceLocator.getTrainingService().updateTrainee(trainee);
    }

    @Override
    public Trainee getTrainee(Integer id) {
        return serviceLocator.getTrainingService().getTrainee(id);
    }

    @Override
    public Trainee getTrainee(String email) {
        return serviceLocator.getTrainingService().getTrainee(email);
    }

    @Override
    public List<Trainee> getTraineesInBatch(Integer batchId) {
        return serviceLocator.getTrainingService().getTraineesInBatch(batchId);
    }

    @Override
    public void deleteTrainee(Trainee trainee) {
        serviceLocator.getTrainingService().deleteTrainee(trainee);
    }

    //
    @Override
    public void createTrainer(Trainer trainer) {
        serviceLocator.getTrainingService().createTrainer(trainer);
    }

    @Override
    public Trainer getTrainer(Integer id) {
        return serviceLocator.getTrainingService().getTrainer(id);
    }

    @Override
    public Trainer getTrainer(String email) {
        return serviceLocator.getTrainingService().getTrainer(email);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        return serviceLocator.getTrainingService().getAllTrainers();
    }

    @Override
    public void updateTrainer(Trainer trainer) {
        serviceLocator.getTrainingService().updateTrainer(trainer);
    }

	public Set<Batch> getAllBatches() {
		// TODO Auto-generated method stub
		return null;
	}

	public Batch getBatchFromCurrentBatchesById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Batch getCurrentBatch() {
		// TODO Auto-generated method stub
		return null;
	}

	public Batch getBatchByTrainerId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<Batch> getAllCurrentBatches() {
		// TODO Auto-generated method stub
		return null;
	}

	public Batch getBatchFromCurrentBatchesById() {
		// TODO Auto-generated method stub
		return null;
	}

	public Batch getBatchFromAllBatchesById() {
		// TODO Auto-generated method stub
		return null;
	}

	/**************************************Grade************************************/
	@Override
	public List<Grade> getGradesByAssessment(Integer assessmentId) {
		return serviceLocator.getAssessmentService().getGradesByAssessment(assessmentId);
	}

	@Override
	public void insertGrade(Grade grade) {
		serviceLocator.getAssessmentService().insertGrade(grade);
		
	}

	@Override
	public void updateGrade(Grade grade) {
		serviceLocator.getAssessmentService().updateGrade(grade);
	}

	
	/***********************************Trainer Notes**********************************/
	@Override
	public void createTrainerNote(TrainerNote note) {
		serviceLocator.getAssessmentService().createTrainerNote(note);
		
	}

	@Override
	public void updateTrainerNote(TrainerNote note) {
		serviceLocator.getAssessmentService().updateTrainerNote(note);
	}

	@Override
	public void deleteTrainerNote(TrainerNote note) {
		serviceLocator.getAssessmentService().deleteTrainerNote(note);
		
	}

	/****************************Batch Notes**********************************/
	@Override
	public void createBatchNote(BatchNote batchNote) {
		serviceLocator.getAssessmentService().createBatchNote(batchNote);
		
	}

	@Override
	public void updateBatchNote(BatchNote batchNote) {
		serviceLocator.getAssessmentService().updateBatchNote(batchNote);
		
	}

	@Override
	public void deleteBatchNote(BatchNote batchNote) {
		serviceLocator.getAssessmentService().deleteBatchNote(batchNote);
		
	}
}
