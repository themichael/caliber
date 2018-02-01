package com.revature.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Address;
import com.revature.beans.Batch;
import com.revature.beans.Trainee;
import com.revature.beans.Trainer;
import com.revature.beans.TrainerRole;

//import com.revature.caliber.data.AddressDAO;
//import com.revature.caliber.data.BatchDAO;
//import com.revature.caliber.data.TraineeDAO;
//import com.revature.caliber.data.TrainerDAO;

/**
 * Provides logic concerning trainer and trainee data. Application logic has no
 * business being in a DAO nor in a Controller. This is the ideal place for
 * calculations
 *
 * @author Patrick Walsh
 *
 */
@Service
public class TrainingService {

	private static final Logger log = Logger.getLogger(TrainingService.class);
	//private TrainerDAO trainerDAO;
	//private TraineeDAO traineeDAO;
	//private BatchDAO batchDAO;
	//private AddressDAO addressDAO;

	/*
	@Autowired
	public void setTrainerDAO(TrainerDAO trainerDAO) {
		this.trainerDAO = trainerDAO;
	}

	@Autowired
	public void setTraineeDAO(TraineeDAO traineeDAO) {
		this.traineeDAO = traineeDAO;
	}

	@Autowired
	public void setBatchDAO(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}

	@Autowired
	public void setAddressDAO(AddressDAO addressDAO) {
		this.addressDAO = addressDAO;
	}
	*/
	/*
	 *******************************************************
	 * LOCATION SERVICES
	 *
	 *******************************************************
	 */
	/*
	 * createLocation
	 * update
	 * findalllocations
	 * findcommonlocations
	 */

	/**
	 * Add New Address
	 *
	 * @param location
	 */
	public void createLocation(Address location) {
		/*
		log.debug("Creating Location " + location);
		addressDAO.save(location);
		*/
	}

	public void update(Address location) {
		/*
		log.debug("Update location: " + location);
		addressDAO.update(location);
		*/
	}

	public List<Address> findAllLocations() {
		/*
		log.debug("Finding all locations");
		return addressDAO.findAll();
		*/
		return null;
	}
	
	public Address findById(int id) {
		/*
		log.info("Getting Address with ID " + id);
		Address address = addressDAO.getAddressById(id);
		log.info("Got " + address);
		return address;
		*/
		return null;
	}

	/*
	 *******************************************************
	 * TRAINER SERVICES
	 *
	 *******************************************************
	 */

	public List<Address> findCommonLocations() {
		/*
		return addressDAO.findAll();
		*/
		return null;
	}
	
}
