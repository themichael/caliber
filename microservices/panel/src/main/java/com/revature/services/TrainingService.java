package com.revature.services;

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
//import com.revature.data.AddressDAO;
//import com.revature.data.BatchDAO;
//import com.revature.data.TraineeDAO;
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
	/*
	private TrainerDAO trainerDAO;
	private TraineeDAO traineeDAO;
	private BatchDAO batchDAO;
	private AddressDAO addressDAO;

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
		;
	}

	public void update(Address location) {
		/*
		log.debug("Update location: " + location);
		addressDAO.update(location);
		/*
	}

	public List<Address> findAllLocations() {
	/*
		log.debug("Finding all locations");
		return addressDAO.findAll();
		*/
	}
	
	public Address findById(int id) {
		return null;
		/*
		log.info("Getting Address with ID " + id);
		Address address = addressDAO.getAddressById(id);
		log.info("Got " + address);
		return address;
		*/
	}

	/*
	 *******************************************************
	 * TRAINER SERVICES
	 *
	 *******************************************************
	 */

	/**
	 * Add New Trainer
	 *
	 * @param trainer
	 */
	public void createTrainer(Trainer trainer) {
		/*
		log.debug("Creating Trainer " + trainer);
		trainerDAO.save(trainer);
		;
		*/
	}

	/**
	 * FIND TRAINER BY EMAIL
	 *
	 * @param email
	 * @return
	 */
	public Trainer findTrainer(String email) {
		return null;
		/*
		log.debug("Find trainer by email " + email);
		return trainerDAO.findByEmail(email);
		*/
	}
	
	

	/**
	 * FIND ALL TRAINERS
	 *
	 * @return
	 */
	public List<Trainer> findAllTrainers() {
		return null;
		/*
		log.debug("Finding all trainers");
		return trainerDAO.findAll();
		*/
	}

	/**
	 * UPDATE TRAINER
	 *
	 * @param trainer
	 */
	public void update(Trainer trainer) {
		/*
		log.debug("Update trainer: " + trainer);
		trainerDAO.update(trainer);
		*/
	}

	/**
	 * FIND TRAINER BY ID
	 *
	 * @param trainerId
	 * @return
	 */
	public Trainer findTrainer(Integer trainerId) {
		return null;
		/*
		log.debug("Find trainer by id: " + trainerId);
		return trainerDAO.findOne(trainerId);
		*/
	}

	/**
	 *
	 * MAKE TRAINER INACTIVE
	 *
	 * @param trainer
	 **/
	public void makeInactive(Trainer trainer) {
		/*
		log.debug(trainer + " is now inactive");
		trainer.setTier(TrainerRole.ROLE_INACTIVE);
		trainerDAO.update(trainer);
		*/
	}

	/**
	 * Find all distinct titles that have been given to trainers
	 **/
	public List<String> findAllTrainerTitles() {
		return null;
		/*
		log.debug("Found all trainer titles");
		return trainerDAO.findAllTrainerTitles();
		*/
	}

	/*
	 *******************************************************
	 * BATCH SERVICES
	 *
	 *******************************************************
	 */

	/**
	 * Returns a list of commonly used locations. Allows user to select from
	 * locations, but also add new locations manually. Suggested UI component is the
	 * HTML5 <datalist>
	 *
	 * @return
	 */
	public List<Address> findCommonLocations() {
		return null;
		/*
		return addressDAO.findAll();
		*/
	}

	/**
	 * ADD ANOTHER WEEK TO BATCH
	 *
	 * @param batchId
	 */
	public void addWeek(Integer batchId) {
		/*
		log.debug("Adding week to batch: " + batchId);
		Batch batch = batchDAO.findOne(batchId);
		if (batch == null)
			throw new IllegalArgumentException("Invalid batch");
		int weeks = batch.getWeeks();
		batch.setWeeks(++weeks);
		batchDAO.update(batch);
		*/
	}

	/**
	 * SAVE BATCH
	 *
	 * @param batch
	 */
	public void save(Batch batch) {
		/*
		log.debug("Saving batch: " + batch);
		batchDAO.save(batch);
		*/
	}

	/**
	 * FIND ALL BATCHES
	 *
	 * @return
	 */
	public List<Batch> findAllBatches() {
		/*
		log.debug("Find all batches");
		return batchDAO.findAll();
		*/
		return null;
	}

	/**
	 * FIND ALL CURRENT BATCHES
	 *
	 * @return
	 */
	public List<Batch> findAllCurrentBatches() {
		/*
		log.debug("Find all current batches");
		return batchDAO.findAllCurrent();
		*/
		return null;
	}

	/**
	 * FIND ALL BATCHES BY TRAINER
	 *
	 * @param trainerId
	 * @return
	 */
	public List<Batch> findAllBatches(int trainerId) {
		/*
		log.debug("Find all batches for trainer: " + trainerId);
		return batchDAO.findAllByTrainer(trainerId);
		*/
		return null;
	}

	/**
	 * FIND ALL CURRENT BATCHES BY TRAINER
	 *
	 * @param trainerId
	 * @return
	 */
	public List<Batch> findAllCurrentBatches(int trainerId) {
		/*
		log.debug("Find all current batches for trainer: " + trainerId);
		return batchDAO.findAllCurrent(trainerId);
		*/
		return null;
	}

	/**
	 * FIND BATCH BY ID
	 *
	 * @param batchId
	 * @return
	 */
	public Batch findBatch(Integer batchId) {
		/*
		log.debug("Finding batch with id: " + batchId);
		return batchDAO.findOne(batchId);
		*/
		return null;
	}

	/**
	 * UPDATE BATCH
	 *
	 * @param batch
	 */
	public void update(Batch batch) {
		/*
		log.debug("Update batch " + batch);
		batchDAO.update(batch);
		*/
	}

	/**
	 * DELETE BATCH
	 *
	 * @param batch
	 */
	public void delete(Batch batch) {
		/*
		Batch fullBatch = batchDAO.findOneWithDroppedTrainees(batch.getBatchId());
		log.debug("Delete batch " + fullBatch);
		batchDAO.delete(fullBatch);
		*/
	}

	/*
	 *******************************************************
	 * TRAINEE SERVICES
	 *
	 *******************************************************
	 */
	/**
	 * SAVE TRAINEE
	 *
	 * @param trainee
	 */
	public void save(Trainee trainee) {
		/*
		log.debug("Save trainee: " + trainee);
		traineeDAO.save(trainee);
		*/
	}

	/**
	 * FIND ALL TRAINEES
	 *
	 * @return
	 */
	public List<Trainee> findAllTrainees() {
		/*
		log.debug("Find all trainees");
		return traineeDAO.findAll();
		*/
		return null;
	}

	/**
	 * FIND ALL TRAINEES BY BATCH ID
	 *
	 *
	 * @param batchId
	 * @return
	 */
	public List<Trainee> findAllTraineesByBatch(Integer batchId) {
		/*
		log.debug("Find trainees by batch");
		return traineeDAO.findAllByBatch(batchId);
		*/
		return null;
	}

	/**
	 * FIND ALL DROPPED TRAINEES BY BATCH ID
	 *
	 * @param batchId
	 * @return
	 */
	public List<Trainee> findAllDroppedTraineesByBatch(Integer batchId) {
		/*
		log.debug("Find dropped trainees by batch");
		return traineeDAO.findAllDroppedByBatch(batchId);
		*/
		return null;
	}

	/**
	 * FIND ALL TRAINEES BY TRAINER ID
	 *
	 * @param trainerId
	 * @return
	 */
	public List<Trainee> findAllTraineesByTrainer(int trainerId) {
		/*
		log.debug("Find trainees by trainer id: " + trainerId);
		return traineeDAO.findAllByTrainer(trainerId);
		*/
		return null;
	}

	/**
	 * FIND TRAINEE BY ID
	 *
	 * @param traineeId
	 * @return
	 */
	public Trainee findTrainee(Integer traineeId) {
		/*
		log.debug("Find trainee by id: " + traineeId);
		return traineeDAO.findOne(traineeId);
		*/
		return null;
	}

	/**
	 * FIND TRAINEE BY EMAIL ADDRESS
	 *
	 * @param email
	 * @return
	 */
	public Set<Trainee> search(String searchTerm) {
		/*
		log.debug("Find trainee : " + searchTerm);
		Set<Trainee> result = new HashSet<>();
		List<Trainee> traineeByEmail = traineeDAO.findByEmail(searchTerm);
		result.addAll(traineeByEmail);
		List<Trainee> traineeByName = traineeDAO.findByName(searchTerm);
		result.addAll(traineeByName);
		List<Trainee> traineeBySkypeId = traineeDAO.findBySkypeId(searchTerm);
		result.addAll(traineeBySkypeId);
		return result;
		*/
		return null;
	}
	

	/**
	 * DELETE TRAINEE
	 *
	 * @param trainee
	 */
	public void delete(Trainee trainee) {
		/*
		log.debug("Delete trainee " + trainee);
		traineeDAO.delete(trainee);
		*/
	}

	/**
	 * UPDATE TRAINEE
	 *
	 * @param trainee
	 */
	public void update(Trainee trainee) {
		/*
		log.debug("Update trainee " + trainee);
		traineeDAO.update(trainee);
		*/
	}

}
