package com.revature.caliber.gateway.services.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.caliber.beans.Trainee;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.gateway.services.TrainingService;

public class TrainingServiceImpl implements TrainingService{

	private String hostname; 
	private String portNumber;
	private String allBatchesForTrainer;
	//paths for trainee (look at beans.xml for the paths themselves)
	private String addTraineePath, updateTraineePath, deleteTraineePath, getTraineeByIdPath, getTraineeByNamePath,
			getTraineesByBatchPath;
	
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

	//Trainee------------------------------------------------------------
	@Override
	public void createTrainee(Trainee trainee) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(addTraineePath)
				.build().toUriString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Trainee> entity = new HttpEntity<>(trainee, headers);

		//Invoke the service
		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.PUT, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Trainee could not be created");
		}
	}

	@Override
	public void updateTrainee(Trainee trainee) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(updateTraineePath)
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Serializable> response = service.postForEntity(URI, trainee, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Trainer could not be updated");
		}
	}

	@Override
	public Trainee getTrainee(Integer id) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(getTraineeByIdPath)
				.path(id.toString())
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Trainee> response = service.getForEntity(URI, Trainee.class);

		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Failed to retrieve the trainee by id.");
		}
		else if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}
		else {
			return null;
		}
	}

	@Override
	public Trainee getTrainee(String name) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(getTraineeByNamePath)
				.path(name)
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Trainee> response = service.getForEntity(URI, Trainee.class);

		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Failed to retrieve the trainee by name.");
		}
		else if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		}
		else {
			return null;
		}
	}

	@Override
	public List<Trainee> getTraineesInBatch(Integer batchId) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(getTraineesByBatchPath)
				.path(batchId.toString())
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Trainee[]> response = service.getForEntity(URI, Trainee[].class);

		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Failed to retrieve trainees by batch.");
		}
		else if (response.getStatusCode() == HttpStatus.OK) {
			return Arrays.asList(response.getBody());
		}
		else {
			return new ArrayList<>();
		}
	}

	@Override
	public void deleteTrainee(Trainee trainee) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(deleteTraineePath)
				.build().toUriString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Trainee> entity = new HttpEntity<>(trainee, headers);

		//Invoke the service
		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.DELETE, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Trainee could not be deleted");
		}
	}
	//End of Trainee -------------------------------------------------------------------------------

	//Trainer --------------------------------------------------------------------------------------
	@Override
	public void createTrainer(Trainer trainer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Trainer getTrainer(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Trainer getTrainer(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Trainer> getAllTrainers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateTrainer(Trainer trainer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTrainer(Trainer trainer) {
		// TODO Auto-generated method stub
		
	}
	
	//End of Trainer ----------------------------------------------------------------------------
	
	
	
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

	//Trainee
	public void setAddTraineePath(String addTraineePath) { this.addTraineePath = addTraineePath; }
	public void setUpdateTraineePath(String updateTraineePath) { this.updateTraineePath = updateTraineePath; }
	public void setDeleteTraineePath(String deleteTraineePath) { this.deleteTraineePath = deleteTraineePath; }
	public void setGetTraineeByIdPath(String getTraineeByIdPath) { this.getTraineeByIdPath = getTraineeByIdPath; }
	public void setGetTraineeByNamePath(String getTraineeByNamePath) { this.getTraineeByNamePath = getTraineeByNamePath; }
	public void setGetTraineesByBatchPath(String getTraineesByBatchPath) { this.getTraineesByBatchPath = getTraineesByBatchPath; }
	//end of Trainee


}