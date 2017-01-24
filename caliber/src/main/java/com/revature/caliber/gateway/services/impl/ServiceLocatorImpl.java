package com.revature.caliber.gateway.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.caliber.gateway.services.ServiceLocator;
import com.revature.caliber.gateway.services.TrainingService;

@Component
public class ServiceLocatorImpl implements ServiceLocator{

	@Autowired
	private TrainingService trainingService;
	
	@Override
	public TrainingService getTrainingService() {
		return trainingService;
	}

	//////////SETTERS//////////////
	public void setTrainingService(TrainingService trainingService) {
		this.trainingService = trainingService;
	}
}
