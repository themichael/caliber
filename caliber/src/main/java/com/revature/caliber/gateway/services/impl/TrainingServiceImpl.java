package com.revature.caliber.gateway.services.impl;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.beans.exceptions.TrainingServiceTraineeOperationException;
import com.revature.caliber.gateway.services.TrainingService;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainingServiceImpl implements TrainingService{

	private String localhost = "http://localhost:9001";
	private String hostname;
	private String portNumber;
	//paths for batch
	private String newBatch, allBatch, allBatchesForTrainer, allCurrentBatch, allCurrentBatchByTrainer,
			batchById, updateBatch, deleteBatch;
	//paths for trainee (look at beans.xml for the paths themselves)
	private String addTraineePath, updateTraineePath, deleteTraineePath, getTraineeByIdPath, getTraineeByNamePath,
			getTraineesByBatchPath;
	private String addTrainerPath, updateTrainerPath, getAllTrainersPath, getTrainerByIdPath, getTrainerByEmailPath;

	/***********************************Batch**********************************/
	@Override
	public void createBatch(Batch batch) {
		RestTemplate service = new RestTemplate();
		// Build Service URL
		final String URI = UriComponentsBuilder.fromHttpUrl( localhost ).path(newBatch)
						.build().toUriString();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
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
			throw new RuntimeException("Bad request.");
		}else if(response.getStatusCode() == HttpStatus.OK){
			return Arrays.asList(response.getBody());
		}else if(response.getStatusCode() == HttpStatus.NOT_FOUND){
			return new ArrayList<>();
		}else{
			// Includes 404 and other responses. Give back no data.
			return new ArrayList<>();
		}
	}

	@Override
	public List<Batch> getBatches(Integer id) {
		RestTemplate service = new RestTemplate();
		// Build Service URL
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber + allBatchesForTrainer)
				.path( id.toString() ).build().toUriString();

		// Invoke the service
		ResponseEntity<Batch[]> response = service.getForEntity(URI, Batch[].class);

		if(response.getStatusCode() == HttpStatus.BAD_REQUEST){
			throw new RuntimeException("Trainer not found in batch.");
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
		ResponseEntity<Batch[]> response = service.getForEntity(URI, Batch[].class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Trainer not found.");
		} else if (response.getStatusCode() == HttpStatus.OK) {
			return Arrays.asList(response.getBody());
		} else {
			// Includes 404 and other responses. Give back no data.
			return new ArrayList<>();
		}
	}

	@Override
	public Batch getBatch(Integer id) {
		RestTemplate service = new RestTemplate();
		String URI = UriComponentsBuilder.fromHttpUrl(localhost).path(batchById).path(String.valueOf(id)).build().toUriString();
		ResponseEntity<Batch> response = service.getForEntity(URI, Batch.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Batch not found");
		} else return response.getBody();
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
			throw new TrainingServiceTraineeOperationException("Trainee could not be created");
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
			throw new TrainingServiceTraineeOperationException("Trainer could not be updated");
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
			throw new TrainingServiceTraineeOperationException("Failed to retrieve the trainee by id.");
		} else if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
			return null;
		}
	}

	@Override
	public Trainee getTrainee(String email) {
		email = email.replace("@", "%40").replace(".", "_dot_");
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(getTraineeByNamePath)
				.path(email)
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Trainee> response = service.getForEntity(URI, Trainee.class);

		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new TrainingServiceTraineeOperationException("Failed to retrieve the trainee by email.");
		} else if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
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
			throw new TrainingServiceTraineeOperationException("Failed to retrieve trainees by batch.");
		} else if (response.getStatusCode() == HttpStatus.OK) {
			return Arrays.asList(response.getBody());
		} else {
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
			throw new TrainingServiceTraineeOperationException("Trainee could not be deleted");
		}
	}
	//End of Trainee -------------------------------------------------------------------------------

	//Trainer --------------------------------------------------------------------------------------
	@Override
	public void createTrainer(Trainer trainer) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(addTrainerPath)
				.build().toUriString();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<Trainer> entity = new HttpEntity<>(trainer, headers);

		//Invoke the service
		ResponseEntity<Serializable> response = service.exchange(URI, HttpMethod.PUT, entity, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Trainee could not be created");
		}
	}

	@Override
	public Trainer getTrainer(Integer id) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(getTrainerByIdPath)
				.path(id.toString())
				.build().toUriString();
		//Invoke the service
		ResponseEntity<Trainer> response = service.getForEntity(URI, Trainer.class);

		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Failed to retrieve the trainer by id.");
		} else if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
			return null;
		}
	}

	@Override
	public Trainer getTrainer(String email) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(getTrainerByEmailPath)
				.path(email)
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Trainer> response = service.getForEntity(URI, Trainer.class);

		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Failed to retrieve the trainer by email.");
		} else if (response.getStatusCode() == HttpStatus.OK) {
			return response.getBody();
		} else {
			return null;
		}
	}

	@Override
	public List<Trainer> getAllTrainers() {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(getAllTrainersPath)
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Trainer[]> response = service.getForEntity(URI, Trainer[].class);

		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Failed to retrieve all trainers.");
		} else if (response.getStatusCode() == HttpStatus.OK) {
			return Arrays.asList(response.getBody());
		} else {
			return new ArrayList<>();
		}
	}

	@Override
	public void updateTrainer(Trainer trainer) {
		RestTemplate service = new RestTemplate();
		//Build Parameters
		final String URI = UriComponentsBuilder.fromHttpUrl(hostname + portNumber).path(updateTrainerPath)
				.build().toUriString();

		//Invoke the service
		ResponseEntity<Serializable> response = service.postForEntity(URI, trainer, Serializable.class);
		if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
			throw new RuntimeException("Trainer could not be updated");
		}
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

	//Trainer
	public void setAddTrainerPath(String addTrainerPath) {this.addTrainerPath = addTrainerPath;}
	public void setUpdateTrainerPath(String updateTrainerPath) {this.updateTrainerPath = updateTrainerPath;}
	public void setGetAllTrainersPath(String getAllTrainersPath) {this.getAllTrainersPath = getAllTrainersPath;}
	public void setGetTrainerByIdPath(String getTrainerByIdPath) {this.getTrainerByIdPath = getTrainerByIdPath;}
	public void setGetTrainerByEmailPath(String getTrainerByEmailPath) {this.getTrainerByEmailPath = getTrainerByEmailPath;}
	//End of Trainer
}

