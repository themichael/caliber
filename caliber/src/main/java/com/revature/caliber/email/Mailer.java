package com.revature.caliber.email;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TimerTask;

import javax.mail.*;
import javax.mail.internet.*;

import org.codehaus.jackson.map.ext.CoreXMLDeserializers.GregorianCalendarDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.revature.caliber.beans.Assessment;
import com.revature.caliber.beans.Batch;
import com.revature.caliber.beans.Grade;
import com.revature.caliber.beans.Trainee;
import com.revature.caliber.beans.Trainer;
import com.revature.caliber.data.AssessmentDAO;
import com.revature.caliber.data.BatchDAO;
import com.revature.caliber.data.GradeDAO;
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

/**
 * 
 * @author Will Underwood
 *
 */
@Component
public class Mailer extends TimerTask {

	@Autowired
	private AssessmentDAO assessmentDAO;

	@Autowired
	private BatchDAO batchDAO;

	@Autowired
	private TraineeDAO traineeDAO;

	@Autowired
	private GradeDAO gradeDAO;
	
	@Autowired
	private TrainerDAO trainerDAO;

	private String toEmail = "mscott@mailinator.com";
	
	private EmailAuthenticator authenticator;

	// Will be autowired later when we're 
	// ready to send emails to specific users.
	// For now it will be hard coded.
	//@Autowired
//	public void setToEmail(String toEmail) {
//		this.toEmail = toEmail;
//	}

//	@Autowired
	public void setAuthenticator(EmailAuthenticator authenticator) {
		this.authenticator = authenticator;
	}

	public void setGrade(GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
	}

	public void setAssessmentDAO(AssessmentDAO assessmentDAO) {
		this.assessmentDAO = assessmentDAO;
	}

	public void setBatch(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}

	public void setTrainee(TraineeDAO traineeDAO) {
		this.traineeDAO = traineeDAO;
	}
	
	public void setTrainer(TrainerDAO trainerDAO) {
		this.trainerDAO = trainerDAO;
	}

	@Override
	public void run() {
		this.send();
	}

	private void send() {
		Properties properties = this.setProperties();
		Session session = this.getSession(properties);
		this.sendEmail(session, this.getTrainersWhoNeedToSubmitGrades());
	}

	private Properties setProperties() {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "587");
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.starttls.enable", "true");
		return properties;
	}

	private Session getSession(Properties properties) {
		return Session.getDefaultInstance(properties, this.authenticator);
	}

	private void sendEmail(Session session, Set<Trainer> trainersToSubmitGrades) {
		//for (Trainer trainer : trainersToSubmitGrades) {
			try {
				MimeMessage message = new MimeMessage(session);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
				//message.addRecipient(Message.RecipientType.TO, new InternetAddress(trainer.getEmail()));
				message.setSubject("Submit Grades Reminder");
				message.setText("Please submit grades.");
				Transport.send(message);
				System.out.println("message sent successfully");
			} catch (MessagingException e) {
				System.out.println(e);
			}
		//}
	}
	
	/**
	 * Returns a Set of Trainers who have not submitted all grades for their batch's assessments.
	 * Only considers current batches.
	 * @precondition None.
	 * @param None.
	 * @return A Set of Trainers who need to submit grades
	 */
	public Set<Trainer> getTrainersWhoNeedToSubmitGrades() {
		Set<Trainer> trainersToSubmitGrades = new HashSet<Trainer>();
		Set<Assessment> assessments = this.getAssessments();
		Set<Trainee> trainees = this.getTrainees();

		// Keep logic below here in this method, but make a new method to call DAOs
		for (Assessment assessment : assessments) {
			int expectedNumberOfGrades = assessments.size() * trainees.size();
			int actualNumberOfGrades = 0;
			Set<Grade> assessmentGrades = assessment.getGrades();
			actualNumberOfGrades += assessmentGrades.size();
			if (actualNumberOfGrades < expectedNumberOfGrades) {
				trainersToSubmitGrades.add(assessment.getBatch().getTrainer());
			}
		}
		return trainersToSubmitGrades;
	}
	
	private Set<Assessment> getAssessments() {
		List<Batch> batches = this.batchDAO.findAllCurrent();
		Set<Assessment> assessments = new HashSet<Assessment>();
		for (Batch batch : batches) {
			assessments.addAll(this.assessmentDAO.findByBatchId(batch.getBatchId()));
		}
		return assessments;
	}
	
	private Set<Trainee> getTrainees() {
		List<Batch> batches = this.batchDAO.findAllCurrent();
		Set<Trainee> trainees = new HashSet<Trainee>();
		for (Batch batch : batches) {
			trainees.addAll(batch.getTrainees());
		}
		return trainees;
	}

}
