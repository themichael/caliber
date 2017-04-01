package com.revature.caliber.services;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


	public Trainer getByEmail(String email){
		log.debug("Find trainer by email " + email);
		return trainerDAO.getByEmail(email);
	}
	
}
