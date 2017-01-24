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
	
	@Override
	public List<Batch> getBatches(Trainer trainer) {
		return serviceLocator.getTrainingService().getBatches(trainer);
	}

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
}
