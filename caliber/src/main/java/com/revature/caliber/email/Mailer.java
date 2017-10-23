package com.revature.caliber.email;

import java.io.IOException;

import java.net.URI;

import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
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

/**
 * This class sends reminder emails to trainers who have not submitted all grades for their batch.
 * @author Will Underwood
 * @author Andrew Bonds
 * @author Vladimir Yevseenko
 *
 */
@Component
public class Mailer implements Runnable {
	
	private static final Logger logger = Logger.getLogger(Mailer.class);

	@Autowired
	private AssessmentDAO assessmentDAO;

	@Autowired
	private BatchDAO batchDAO;
	
	@Autowired
	private GradeDAO gradeDAO;

	@Autowired
	private EmailAuthenticator authenticator;

	private static final String EMAIL_TEMPLATE_NAME_TOKEN = "$TRAINER_NAME";

	public void setAuthenticator(EmailAuthenticator authenticator) {
		this.authenticator = authenticator;
	}

	public void setAssessmentDAO(AssessmentDAO assessmentDAO) {
		this.assessmentDAO = assessmentDAO;
	}

	public void setBatch(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}

	public void setGradeDAO(GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
	}

	/**
	 * Starts the thread that sends the emails.
	 * @precondition None.
	 * @param None.
	 * @postcondition Email thread is running on server
	 */
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

	/**
	 * 
	 * @param session
	 * @param trainersToSubmitGrades
	 */
	private void sendEmail(Session session, Set<Trainer> trainersToSubmitGrades) {
		logger.info("Trainers being sent emails: "+ trainersToSubmitGrades);
		String email = getEmailString();
		for (Trainer trainer : trainersToSubmitGrades) {		
			try {
				logger.info("In the try block for sending emails");
				MimeMessage message = new MimeMessage(session);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(trainer.getEmail()));
				
				message.setSubject("Submit Grades Reminder");
				String trainerName = trainer.getName();
				String emailStr = email.replace(EMAIL_TEMPLATE_NAME_TOKEN, trainerName);
				if (emailStr == null)
					return;
				message.setContent(emailStr, "text/html");
				
				Transport.send(message);
				logger.info("Email sent");
			} catch (MessagingException e) {
				logger.warn(e);
				logger.warn("Email exception");
			}
		}
	}
	
	/**
	 * 
	 * @return
	 */
	private String getEmailString() {
		try {

			String emailTemplate = "emailTemplate.html";
			String emailStr = new String(Files.readAllBytes(Paths.get(this.getClass().getResource(emailTemplate).toURI())));
			logger.info(emailStr);

			logger.info("loaded template");
			return emailStr;
		} catch (IOException e) {
			logger.warn("Unable to read email template");
			logger.warn(e);
			return null;
		} catch (Exception e) {
			logger.warn("General exception occurred when trying to read email template");
			logger.warn(e);
			return null;
		}
		
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
		List<Batch> batches = getBatches();
		for (Batch batch : batches) {
			Set<Trainee> trainees = batch.getTrainees();
			List<Assessment> assessments = getAssessments(batch.getBatchId());
			int expectedNumberOfGrades = trainees.size() * assessments.size();
			int actualNumberOfGrades = 0;
			actualNumberOfGrades = getActualNumberOfGrades(assessments, batch.getBatchId());
			if (actualNumberOfGrades < expectedNumberOfGrades) {
				trainersToSubmitGrades.add(batch.getTrainer());
			}
		}
		return trainersToSubmitGrades;
	}
	
	private List<Batch> getBatches(){
		return batchDAO.findAllCurrent();
	}
	
	private List<Assessment> getAssessments(int batchID) {
		return this.assessmentDAO.findByBatchId(batchID);
	}
	
	private int getActualNumberOfGrades(List<Assessment> expectedAssessments, int batchID){
		List<Grade> allGrades = gradeDAO.findByBatch(batchID);
		int gradeCounter = 0;
		for(Grade grade: allGrades) {
			for(Assessment assessment: expectedAssessments) {
				if(grade.getAssessment().getAssessmentId() == assessment.getAssessmentId()) {
					gradeCounter++;
				}
			}
		}
		return gradeCounter;
	}
}