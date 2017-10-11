package com.revature.caliber.services;

import java.util.List;
import java.util.TimerTask;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.data.AssessmentDAO;
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
	
	SubmitGradesReminder submit = new SubmitGradesReminder();
	
	public void setAssessmentDAO(AssessmentDAO assess) {
		this.assess = assess;
	}


	@PostConstruct
	public void init() {
//		submit.startReminderJob();
		List<Assessment> list = assess.findAll();
		System.out.println(list.toString());
	}



	

	
	
}
