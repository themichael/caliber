package com.revature.caliber.gateway.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.gateway.services.TrainingService;

public class TrainingServiceImpl implements TrainingService{

	private String hostname; 
	private String portNumber;
	private String allBatchesForTrainer;
	
	@Override
	public List<Batch> getBatches(Trainer trainer) {
		RestTemplate service = new RestTemplate();
		// Build Service URL
		final String URI = 
				UriComponentsBuilder.fromHttpUrl(hostname+portNumber
						+allBatchesForTrainer).path(trainer.getName())
				.build().toUriString();
		// Invoke the service
		ResponseEntity<Batch[]> response =
				service.getForEntity(URI, Batch[].class);
		if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
			// TODO Create custom runtime exception
			throw new RuntimeException("Trainer not found.");
		}else if(response.getStatusCode() == HttpStatus.OK){
			return Arrays.asList(response.getBody());
		}else {
			// Includes 404 and other responses. Give back no data.
			return new ArrayList<Batch>();
		}
	}

	
	/////////// SETTERS ////////////////
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public void setPortNumber(String portNumber) {
		this.portNumber = portNumber;
	}
	public void setAllBatchesForTrainer(String allBatchesForTrainer) {
		this.allBatchesForTrainer = allBatchesForTrainer;
	}
}
