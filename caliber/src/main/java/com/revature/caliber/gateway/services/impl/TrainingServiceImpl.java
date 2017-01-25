package com.revature.caliber.gateway.services.impl;

import java.io.Serializable;
import java.util.*;

import com.revature.caliber.beans.Trainee;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.gateway.services.TrainingService;

public class TrainingServiceImpl implements TrainingService{

	private String hostname;
	private String portNumber;
	//paths for batch
	private String newBatch, allBatch, allBatchesForTrainer, allCurrentBatch, allCurrentBatchByTrainer,
			batchById, updateBatch, deleteBatch;
	//paths for trainee (look at beans.xml for the paths themselves)
	private String addTraineePath, updateTraineePath, deleteTraineePath, getTraineeByIdPath, getTraineeByNamePath,
			getTraineesByBatchPath;

	/***********************************Batch**********************************/
	@Override
	public void createBatch(Batch batch) {
		RestTemplate service = new RestTemplate();
		// Build Service URL
		final String URI = UriComponentsBuilder.fromHttpUrl( hostname + portNumber ).path(newBatch)
						.build().toUriString();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Batch> entity = new HttpEntity<>(batch, headers);

		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.PUT, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Batch could not be created");
		}
	}

	@Override
	public List<Batch> allBatch() {
		RestTemplate service = new RestTemplate();
		// Build Service URL
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(allBatch)
						.build().toUriString();
		// Invoke the service
		ResponseEntity<Batch[]> response = service.getForEntity(URI, Batch[].class);

		if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
			throw new RuntimeException("Batches not found.");
		}else if(response.getStatusCode() == HttpStatus.OK){
			return Arrays.asList(response.getBody());
		}else {
			// Includes 404 and other responses. Give back no data.
			return new ArrayList<>();
		}
	}

	@Override
	public List<Batch> getBatches(Trainer trainer) {
		RestTemplate service = new RestTemplate();
		// Build Service URL
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber + allBatchesForTrainer)
				.path(trainer.getName()).build().toUriString();

		// Invoke the service
		ResponseEntity<Batch[]> response = service.getForEntity(URI, Batch[].class);

		if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
			throw new RuntimeException("Trainer not found.");
		}else if(response.getStatusCode() == HttpStatus.OK){
			return Arrays.asList(response.getBody());
		}else {
			// Includes 404 and other responses. Give back no data.
			return new ArrayList<>();
		}
	}

	@Override
	public List<Batch> currentBatch() {
		RestTemplate service = new RestTemplate();
		// Build Service URL
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(allCurrentBatch)
				.build().toUriString();
		// Invoke the service
		ResponseEntity<Batch[]> response = service.getForEntity(URI, Batch[].class);

		if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
			throw new RuntimeException("No Current batches.");
		}else if(response.getStatusCode() == HttpStatus.OK){
			return Arrays.asList(response.getBody());
		}else {
			// Includes 404 and other responses. Give back no data.
			return new ArrayList<>();
		}
	}

//	LOUIS START HERE
	@Override
	public List<Batch> currentBatch(Trainer trainer) {
		RestTemplate service = new RestTemplate();
		// Build Service URL
		final String URI =
				UriComponentsBuilder.fromHttpUrl(hostname + portNumber
						+ allCurrentBatchByTrainer).path(String.valueOf(trainer.getTraineeId()))
						.build().toUriString();
		// Invoke the service
		ResponseEntity<Batch[]> response = service.getForEntity(URI,Batch[].class);
		if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
			throw new RuntimeException("Trainer not found.");
		}else if(response.getStatusCode() == HttpStatus.OK){
			return Arrays.asList(response.getBody());
		}else {
			// Includes 404 and other responses. Give back no data.
			return new ArrayList<>();
		}
	}

	@Override
	public Batch getBatch(Integer id) {
		RestTemplate service = new RestTemplate();
		String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(batchById).path(String.valueOf(id)).build().toUriString();
		ResponseEntity<Batch> response = service.getForEntity(URI,Batch.class);
		if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
			throw new RuntimeException("Batch not found");
		}else return response.getBody();
	}

	@Override
	public void updateBatch(Batch batch) {

	}

	@Override
	public void deleteBatch(Batch batch) {

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

	//Batch
	public void setNewBatch(String newBatch) {this.newBatch = newBatch;}
	public void setAllBatch(String allBatch) {this.allBatch = allBatch;}
	public void setAllBatchesForTrainer(String allBatchesForTrainer) {this.allBatchesForTrainer = allBatchesForTrainer;}
	public void setAllCurrentBatch(String allCurrentBatch) {this.allCurrentBatch = allCurrentBatch;}
	public void setAllCurrentBatchByTrainer(String allCurrentBatchByTrainer) {this.allCurrentBatchByTrainer = allCurrentBatchByTrainer;}
	public void setBatchById(String batchById) {this.batchById = batchById;}
	public void setUpdateBatch(String updateBatch) {this.updateBatch = updateBatch;}
	public void setDeleteBatch(String deleteBatch) {this.deleteBatch = deleteBatch;}
	//end of batch

	//Trainee
	public void setAddTraineePath(String addTraineePath) { this.addTraineePath = addTraineePath; }
	public void setUpdateTraineePath(String updateTraineePath) { this.updateTraineePath = updateTraineePath; }
	public void setDeleteTraineePath(String deleteTraineePath) { this.deleteTraineePath = deleteTraineePath; }
	public void setGetTraineeByIdPath(String getTraineeByIdPath) { this.getTraineeByIdPath = getTraineeByIdPath; }
	public void setGetTraineeByNamePath(String getTraineeByNamePath) { this.getTraineeByNamePath = getTraineeByNamePath; }
	public void setGetTraineesByBatchPath(String getTraineesByBatchPath) { this.getTraineesByBatchPath = getTraineesByBatchPath; }
	//end of Trainee


}