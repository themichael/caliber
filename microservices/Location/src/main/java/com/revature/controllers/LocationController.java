package com.revature.controllers;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.revature.beans.Address;
import com.revature.beans.Batch;
import com.revature.beans.Trainee;
import com.revature.beans.Trainer;
import com.revature.service.TrainingService;

class LocationController{
	private static final Logger log = Logger.getLogger(LocationController.class);
	private TrainingService trainingService;
	
	@Autowired
	public void setTrainingService(TrainingService trainingService) {
		this.trainingService = trainingService;
	}
	@RequestMapping(value = "/vp/location/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<Address> createLocation(@Valid @RequestBody Address location) {
		log.info("Saving location: " + location);
		trainingService.createLocation(location);
		return new ResponseEntity<>(location, HttpStatus.CREATED);
	}

/**
 * Update location
 *
 * @param location
 *
 * @return the response entity
 */
	@RequestMapping(value = "/vp/location/update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<Void> updateLocation(@Valid @RequestBody Address location) {
		log.info("Updating location: " + location);
		trainingService.update(location);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

/**
 * Returns all locations from the database `
 *
 * @return
 */
	@RequestMapping(value = "/all/location/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP', 'QC', 'TRAINER', 'STAGING', 'PANEL')")
	public ResponseEntity<List<Address>> getAllLocations() {
		log.info("Fetching all locations");
		List<Address> locations = trainingService.findAllLocations();
		return new ResponseEntity<>(locations, HttpStatus.OK);
	}

/**
 * Removes the location
 *
 * @param location
 * @return response entity
 */
	@RequestMapping(value = "/vp/location/delete", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<Void> removeLocation(@Valid @RequestBody Address location) {
		log.info("Deactivating location: " + location);
		trainingService.update(location);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

/**
 * Reactivates the location
 *
 * @param location
 * @return response entity
 */
	@RequestMapping(value = "/vp/location/reactivate", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP')")
	public ResponseEntity<Void> reactivateLocation(@Valid @RequestBody Address location) {
		log.info("Updating location: " + location);
		trainingService.update(location);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	@RequestMapping(value = "/all/locations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	@PreAuthorize("hasAnyRole('VP', 'STAGING', 'QC', 'TRAINER', 'PANEL')")
	public ResponseEntity<List<Address>> findCommonLocations() {
		log.info("Fetching common training locations");
		return new ResponseEntity<>(trainingService.findCommonLocations(), HttpStatus.OK);
	}
}
