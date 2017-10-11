package com.revature.caliber.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.email.Mailer;
import com.revature.caliber.email.SubmitGradesReminder;

/**
 * 
 * @author Andrew Bonds
 *
 */

@Service
public class Email{

	@Autowired
	private AssessmentDAO assess;
	@Autowired
	private BatchDAO batch;
	@Autowired
	private TraineeDAO trainee;
	SubmitGradesReminder submit = new SubmitGradesReminder();

	
	public void setAssessmentDAO(AssessmentDAO assess) {
		this.assess = assess;
	}

	public void setBatch(BatchDAO batch) {
		this.batch = batch;
	}

	public void setTrainee(TraineeDAO trainee) {
		this.trainee = trainee;
	}

	@PostConstruct
	public void init() {
		System.out.println("Initializing Emails.");


		List<Batch> batchList = batch.findAll();
		List<Assessment> assessList = assess.findAll();
		List<Trainee> traineeList = trainee.findAll();
		checkGrades(batchList, assessList, traineeList);

	}


	public void checkGrades(List<Batch> batchList, List<Assessment> assessList, List<Trainee> traineeList) {
		Date currentDate = new Date();
		
		for(Batch b : batchList) {
			ArrayList<Long> list = new ArrayList<Long>();
			if (b.getEndDate().before(currentDate) && b.getWeeks() < 8) {
				for(Assessment a: assessList) {
					if(b.getBatchId() == a.getBatch().getBatchId()) {
						list.add(a.getAssessmentId());
					}
				}
				for(Trainee t : traineeList) {

			}
		}
		
		

	}

	

	
	
}
