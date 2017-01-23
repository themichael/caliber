package com.revature.caliber.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.revature.caliber.beans.Batch;

@RestController
public class VPBatchController {

	/***
	 * Please change unit tests after connecting controller to midtier
	 * OR after changing return values of test data 
	 ****/
	
	/**
	 * getAllBatches - REST API method, retrieves all the batches
	 * 
	 * @return in JSON, a set of batch objects
	 */
	@RequestMapping(value = "/vp/batch/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Batch>> getAllBatches() {

		// Test data - remove and replace with call to midtier
		Batch firstBatch = new Batch();
		firstBatch.setBatchId(4);
		firstBatch.setTrainingName("Batch3425");
		firstBatch.setTrainingType("Java2EE");
		firstBatch.setLocation("Revature");
		firstBatch.setSkillType("Junior Level");

		Batch secondBatch = new Batch();
		secondBatch.setBatchId(5);
		secondBatch.setTrainingName("Batch4523");
		secondBatch.setTrainingType("Java2EE");
		secondBatch.setLocation("Penn State University");
		secondBatch.setSkillType("Junior Level");

		Batch thirdBatch = new Batch();
		thirdBatch.setBatchId(6);
		thirdBatch.setTrainingName("Batcwh5423");
		thirdBatch.setTrainingType("Java2EE");
		thirdBatch.setLocation("Queens College");
		thirdBatch.setSkillType("Junior Level");

		Set<Batch> set = new HashSet<>();
		set.add(firstBatch);
		set.add(secondBatch);
		set.add(thirdBatch);

		return new ResponseEntity<>(set, HttpStatus.OK);
	}

	/**
	 * getAllCurrentBatches - REST API method, retrieves all current batches
	 * 
	 * @return in JSON, a set of batch objects
	 */
	@RequestMapping(value = "/vp/batch/current/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Set<Batch>> getAllCurrentBatches() {

		// Test data - remove and replace with call to midtier
		Batch firstBatch = new Batch();
		firstBatch.setBatchId(8);
		firstBatch.setTrainingName("Batch1734");
		firstBatch.setTrainingType("Java2EE");
		firstBatch.setLocation("Revature");
		firstBatch.setSkillType("Junior Level");

		Batch secondBatch = new Batch();
		secondBatch.setBatchId(9);
		secondBatch.setTrainingName("Batch1743");
		secondBatch.setTrainingType("Java2EE");
		secondBatch.setLocation("Penn State University");
		secondBatch.setSkillType("Junior Level");

		Batch thirdBatch = new Batch();
		thirdBatch.setBatchId(10);
		thirdBatch.setTrainingName("Batcwh1723");
		thirdBatch.setTrainingType("Java2EE");
		thirdBatch.setLocation("Queens College");
		thirdBatch.setSkillType("Junior Level");

		Set<Batch> set = new HashSet<>();
		set.add(firstBatch);
		set.add(secondBatch);
		set.add(thirdBatch);

		return new ResponseEntity<>(set, HttpStatus.OK);
	}

	/**
	 * getBatch - REST API method, retrieves a batch object
	 * 
	 * @param id - batch id
	 * @return in JSON, a batch object
	 */
	@RequestMapping(value = "/vp/batch/{id}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> getBatch(@PathVariable int id) {

		// Test data - remove and replace with call to midtier
		Batch batch = new Batch();
		batch.setBatchId(id);
		batch.setTrainingName("Batch1634");
		batch.setTrainingType("Java2EE");
		batch.setLocation("Revature");
		batch.setSkillType("Junior Level");
		
		return new ResponseEntity<>(batch, HttpStatus.OK);
	}
	
	/**
	 * getCurrentBatch - REST API method, retrieves a current batch object
	 * 
	 * @param id - batch id
	 * @return in JSON, a batch object
	 */
	@RequestMapping(value = "/vp/current/batch/{id}", method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Batch> getCurrentBatch(@PathVariable int id) {

		// Test data - remove and replace with call to midtier
		Batch batch = new Batch();
		batch.setBatchId(id);
		batch.setTrainingName("Batch1606");
		batch.setTrainingType("Java2EE");
		batch.setLocation("Revature");
		batch.setSkillType("Junior Level");
		
		return new ResponseEntity<>(batch, HttpStatus.OK);
	}
}
