package com.revature.caliber.email;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
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

	private void sendEmail(Session session, List<Trainer> trainersToSubmitGrades) {
		//for (Trainer trainer : trainersToSubmitGrades) {
			try {
				MimeMessage message = new MimeMessage(session);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
				//message.addRecipient(Message.RecipientType.TO, new InternetAddress(trainer.getEmail()));
				message.setSubject("Submit Grades Reminder");
				message.setText("Insert pretty formatting here.");
				Transport.send(message);
				System.out.println("message sent successfully");
			} catch (MessagingException e) {
				System.out.println(e);
			}
		//}
	}

	public List<Trainer> getTrainersWhoNeedToSubmitGrades() {
		List<Trainer> trainersToSubmitGrades = new ArrayList<Trainer>();
		List<Trainer> trainers = this.trainerDAO.findAll();
		for (Trainer trainer : trainers) {
			List<Batch> trainerBatches = this.batchDAO.findAllByTrainer(trainer.getTrainerId());
			for (Batch batch : trainerBatches) {
				List<Assessment> batchAssessments = this.assessmentDAO.findByBatchId(batch.getBatchId());
				List<Trainee> batchTrainees = this.traineeDAO.findAllByBatch(batch.getBatchId());
				int expectedNumberOfGrades = batchAssessments.size() * batchTrainees.size();
				int actualNumberOfGrades = 0;
				for (Assessment assessment : batchAssessments) {
					List<Grade> assessmentGrades = gradeDAO.findByAssessment(assessment.getAssessmentId());
					actualNumberOfGrades += assessmentGrades.size();
				}
				if (actualNumberOfGrades < expectedNumberOfGrades) {
					//System.out.println("\n" + trainer.getName() + " needs to submit grades" + "\n");
					trainersToSubmitGrades.add(trainer);
				}/* else {
					System.out.println("\n" + "All grades submitted" + "\n");
				}*/
			}
		}
		return trainersToSubmitGrades;
	}

}
