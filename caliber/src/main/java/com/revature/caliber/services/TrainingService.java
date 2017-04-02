package com.revature.caliber.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

/**
 * Provides logic concerning trainer and trainee data.
 * Application logic has no business being in a DAO
 * nor in a Controller. This is the ideal place for calculations 
 * 
 * @author Patrick Walsh
 *
 */
@Service
public class TrainingService {
	
	private static final Logger log = Logger.getLogger(TrainingService.class);
	private TrainerDAO trainerDAO;
	private TraineeDAO traineeDAO;
	private BatchDAO batchDAO;
	
	@Autowired
	public void setTrainerDAO(TrainerDAO trainerDAO) {this.trainerDAO = trainerDAO;}
	@Autowired
	public void setTraineeDAO(TraineeDAO traineeDAO) {this.traineeDAO = traineeDAO;}
	@Autowired
	public void setBatchDAO(BatchDAO batchDAO) {this.batchDAO = batchDAO;}

	/*
	 *******************************************************
	 *	TRAINER SERVICES
	 *
	 *******************************************************
	 */

	/**
	 * FIND TRAINER BY EMAIL
	 * @param email
	 * @return
	 */
	public Trainer findTrainer(String email){
		log.debug("Find trainer by email " + email);
		return trainerDAO.findByEmail(email);
	}
	
	/**
	 * FIND ALL TRAINERS
	 * @return
	 */
	public List<Trainer> findAllTrainers(){
		log.debug("Finding all trainers");
		return trainerDAO.findAll();
	}
	
	/**
	 * UPDATE TRAINER
	 * @param trainer
	 */
	public void update(Trainer trainer){
		log.debug("Update trainer: " + trainer);
		trainerDAO.update(trainer);
	}
	
	/**
	 * FIND TRAINER BY ID
	 * @param trainerId
	 * @return
	 */
	public Trainer findTrainer(Integer trainerId){
		log.debug("Find trainer by id: " + trainerId);
		return trainerDAO.findOne(trainerId);
	}
	
	/*
	 *******************************************************
	 *	BATCH SERVICES
	 *
	 *******************************************************
	 */
	
	/**
	 * ADD ANOTHER WEEK TO BATCH
	 * @param batchId
	 */
	public void addWeek(Integer batchId){
		log.debug("Adding week to batch: " + batchId);
		Batch batch = batchDAO.findOne(batchId);
		int weeks = batch.getWeeks();
		batch.setWeeks(++weeks);
		batchDAO.update(batch);
	}
	
	/**
	 * SAVE BATCH
	 * @param batch
	 */
	public void saveBatch(Batch batch){
		log.debug("Saving batch: " + batch);
		batchDAO.save(batch);
	}
	
	/**
	 * FIND ALL BATCHES
	 * @return
	 */
	public List<Batch> findAllBatches(){
		log.debug("Find all batches");
		return batchDAO.findAll();
	}
	
	/**
	 * FIND ALL CURRENT BATCHES
	 * @return
	 */
	public List<Batch> findAllCurrentBatches(){
		log.debug("Find all current batches");
		return batchDAO.findAllCurrent();
	}
	
	/**
	 * FIND ALL BATCHES BY TRAINER
	 * @param trainerId
	 * @return
	 */
	public List<Batch> findAllBatches(int trainerId){
		log.debug("Find all batches for trainer: " + trainerId);
		return batchDAO.findAllByTrainer(trainerId);
	}
	
	/**
	 * FIND ALL CURRENT BATCHES BY TRAINER
	 * @param trainerId
	 * @return
	 */
	public List<Batch> findAllCurrentBatches(int trainerId){
		log.debug("Find all current batches for trainer: " + trainerId);
		return batchDAO.findAllCurrent(trainerId);
	}
	
	/**
	 * FIND BATCH BY ID
	 * @param batchId
	 * @return
	 */
	public Batch findBatch(Integer batchId){
		log.debug("Finding batch with id: " + batchId);
		return batchDAO.findOne(batchId);
	}
	
	/**
	 * UPDATE BATCH
	 * @param batch
	 */
	public void updateBatch(Batch batch){
		log.debug("Update batch " + batch);
		batchDAO.update(batch);
	}
	
	/**
	 * DELETE BATCH
	 * @param batch
	 */
	public void deleteBatch(Batch batch){
		log.debug("Delete batch " + batch);
		batchDAO.delete(batch);
	}
	
	/*
	 *******************************************************
	 *	TRAINEE SERVICES
	 *
	 *******************************************************
	 */
	/**
	 * SAVE TRAINEE
	 * @param trainee
	 */
	public void save(Trainee trainee){
		log.debug("Save trainee: " + trainee);
		traineeDAO.save(trainee);
	}
	
	/**
	 * FIND ALL TRAINEES
	 * @return
	 */
	public List<Trainee> findAllTrainees(){
		log.debug("Find all trainees");
		return traineeDAO.findAll();
	}
	
	/**
	 * FIND ALL TRAINEES BY BATCH ID
	 * @param batchId
	 * @return
	 */
	public List<Trainee> findAllTraineesByBatch(Integer batchId){
		log.debug("Find trainees by batch");
		return traineeDAO.findAllByBatch(batchId);
	}
	
	/**
	 * FIND ALL TRAINEES BY TRAINER ID
	 * @param trainerId
	 * @return
	 */
	public List<Trainee> findAllTraineesByTrainer(int trainerId){
		log.debug("Find trainees by trainer id: " + trainerId);
		return traineeDAO.findAllByTrainer(trainerId);
	}
	
	/**
	 * FIND TRAINEE BY ID
	 * @param traineeId
	 * @return
	 */
	public Trainee findTrainee(Integer traineeId){
		log.debug("Find trainee by id: " + traineeId);
		return traineeDAO.findOne(traineeId);
	}
	
	/**
	 * FIND TRAINEE BY EMAIL ADDRESS
	 * @param email
	 * @return
	 */
	public Trainee findTraineeByEmail(String email){
		log.debug("Find trainee by email: " + email);
		return traineeDAO.findByEmail(email);
	}
	
	/**
	 * UPDATE TRAINEE
	 * @param trainee
	 */
	public void delete(Trainee trainee){
		log.debug("Delete trainee " + trainee);
		traineeDAO.delete(trainee);
	}
	
	/**
	 * DELETE TRAINEE
	 * @param trainee
	 */
	public void update(Trainee trainee){
		log.debug("Update trainee " + trainee);
		traineeDAO.update(trainee);
	}
	
	
}