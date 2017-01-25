package com.revature.caliber.gateway.impl;

import java.util.List;

import com.revature.caliber.beans.Trainee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.gateway.ApiGateway;
import com.revature.caliber.gateway.services.ServiceLocator;

@Component
public class ApiGatewayImpl implements ApiGateway{

	@Autowired
	private ServiceLocator serviceLocator;

	/****************************Batch*******************************/
	public void createBatch(Batch batch) {serviceLocator.getTrainingService().createBatch(batch);}
	public List<Batch> allBatch() {return serviceLocator.getTrainingService().allBatch();}
	public List<Batch> getBatches(Trainer trainer) {return serviceLocator.getTrainingService().getBatches(trainer);}
	public List<Batch> currentBatch() {return serviceLocator.getTrainingService().currentBatch();}
	public List<Batch> currentBatch(Trainer trainer) {return serviceLocator.getTrainingService().currentBatch(trainer);}
	public Batch getBatch(Integer id) {return serviceLocator.getTrainingService().getBatch(id);}
	public void updateBatch(Batch batch) {serviceLocator.getTrainingService().updateBatch(batch);}
	public void deleteBatch(Batch batch) {serviceLocator.getTrainingService().deleteBatch(batch);}

	/****************************Trainee*******************************/
	@Override
	public void createTrainee(Trainee trainee) { serviceLocator.getTrainingService().createTrainee(trainee); }

	@Override
	public void updateTrainee(Trainee trainee) { serviceLocator.getTrainingService().updateTrainee(trainee); }

	@Override
	public Trainee getTrainee(Integer id) { return serviceLocator.getTrainingService().getTrainee(id); }

	@Override
	public Trainee getTrainee(String name) { return serviceLocator.getTrainingService().getTrainee(name); }

	@Override
	public List<Trainee> getTraineesInBatch(Integer batchId) { return serviceLocator.getTrainingService().getTraineesInBatch(batchId); }

	@Override
	public void deleteTrainee(Trainee trainee) { serviceLocator.getTrainingService().deleteTrainee(trainee); }

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

	@Override
	public void deleteTrainer(Trainer trainer) {
		serviceLocator.getTrainingService().deleteTrainer(trainer);
	}
}
