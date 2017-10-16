package com.revature.caliber.email;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TimerTask;

import javax.mail.*;
import javax.mail.internet.*;


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
import com.revature.caliber.data.TraineeDAO;
import com.revature.caliber.data.TrainerDAO;

/**
 * 
 * @author Will Underwood
 *
 */
@Component
public class Mailer extends TimerTask {
	
	private static final Logger logger = Logger.getLogger(Mailer.class);

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
	
	

	/*private static final String EMAIL_TEMPLATE_PATH =
			"../../../../../webapp/static/app/partials/email/emailTemplate.html";
	*/
	private static final String EMAIL_TEMPLATE_PATH =
			"C:\\Users\\apbon\\caliber\\caliber\\src\\main\\webapp\\static\\app\\partials\\email\\emailTemplate.html";

	private static final String EMAIL_TEMPLATE_FIRST_NAME_TOKEN = "$TRAINER_FIRST";
	private static final String EMAIL_TEMPLATE_LAST_NAME_TOKEN = "$TRAINER_LAST";

	// Will be autowired later when we're 
	// ready to send emails to specific users.
	// For now it will be hard coded.
	//@Autowired
//	public void setToEmail(String toEmail) {
//		this.toEmail = toEmail;
//	}

	@Autowired
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
		for (Trainer trainer : trainersToSubmitGrades) {
			try {
				MimeMessage message = new MimeMessage(session);
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
				//message.addRecipient(Message.RecipientType.TO, new InternetAddress(trainer.getEmail()));
				message.setSubject("Submit Grades Reminder");
				
				String email = getEmailString(trainer);
				if (email == null)
					return;
				
				message.setContent(email, "text/html");
				
				Transport.send(message);
				logger.info("Email sent");
			} catch (MessagingException e) {
				logger.warn(e);
				logger.warn("Email exception");
			}
		}
	}
	
	private String getEmailString(Trainer trainer) {
		try {
			String emailStr;
			emailStr = new String(Files.readAllBytes(Paths.get(EMAIL_TEMPLATE_PATH)));
			String firstName = trainer.getName().split(" ")[0];
			String lastName = trainer.getName().split(" ")[1];
			emailStr = emailStr.replace(EMAIL_TEMPLATE_FIRST_NAME_TOKEN, firstName);
			emailStr = emailStr.replace(EMAIL_TEMPLATE_LAST_NAME_TOKEN, lastName);
			return emailStr;
		} catch (IOException e) {
			logger.warn("Unable to read email template");
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
