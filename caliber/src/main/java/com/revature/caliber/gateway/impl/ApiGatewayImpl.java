package com.revature.caliber.gateway.impl;

import java.util.List;

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
}
