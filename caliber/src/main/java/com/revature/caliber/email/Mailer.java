package com.revature.caliber.email;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

/**
 * 
 * @author Will Underwood
 * @author Andrew Bonds
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

	private EmailAuthenticator authenticator;

	private static final String EMAIL_TEMPLATE_PATH =
			"C:\\Users\\apbon\\caliber\\caliber\\src\\main\\webapp\\static\\app\\partials\\email\\emailTemplate.html";

	private static final String EMAIL_TEMPLATE_FIRST_NAME_TOKEN = "$TRAINER_FIRST";
	private static final String EMAIL_TEMPLATE_LAST_NAME_TOKEN = "$TRAINER_LAST";

	@Autowired
	public void setAuthenticator(EmailAuthenticator authenticator) {
		this.authenticator = authenticator;
	}

	public void setAssessmentDAO(AssessmentDAO assessmentDAO) {
		this.assessmentDAO = assessmentDAO;
	}

	public void setBatch(BatchDAO batchDAO) {
		this.batchDAO = batchDAO;
	}

	public void setTraineeDAO(TraineeDAO traineeDAO) {
		this.traineeDAO = traineeDAO;
	}

	public void setGradeDAO(GradeDAO gradeDAO) {
		this.gradeDAO = gradeDAO;
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
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(trainer.getEmail()));
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
		List<Batch> batches = getBatches();
		for (Batch batch : batches) {
			Set<Assessment> expectedAssessments = getAssessments(batch.getBatchId());
			Set<Trainee> trainees = getTrainees(batch.getBatchId());
			int actualAssessments = getActual(expectedAssessments, batch.getBatchId());
			int expectedNumberOfGrades = trainees.size() * expectedAssessments.size();
			int actualNumberOfGrades = 0;
			actualNumberOfGrades += actualAssessments;
			if (actualNumberOfGrades < expectedNumberOfGrades) {
				trainersToSubmitGrades.add(batch.getTrainer());
			}
		}
		return trainersToSubmitGrades;
	}
	
	private List<Batch> getBatches(){
		return batchDAO.findAllCurrent();
	}
	
	private Set<Assessment> getAssessments(int batchID) {
		Set<Assessment> assessments = new HashSet<Assessment>();
		assessments.addAll(this.assessmentDAO.findByBatchId(batchID));
		return assessments;
	}
	
	private int getActual(Set<Assessment> expectedAssessments, int batchID){
		List<Grade> allGrades = gradeDAO.findByBatch(batchID);
		int assessmentCounter = 0;
		for(Grade grade: allGrades) {
			for(Assessment assessment: expectedAssessments) {
				if(grade.getAssessment().getAssessmentId() == assessment.getAssessmentId()) {
					assessmentCounter++;
				}
			}
		}
		return assessmentCounter;
	}

	private Set<Trainee> getTrainees(int batchID) {
		Set<Trainee> trainees = new HashSet<Trainee>();
		trainees.addAll(this.traineeDAO.findAllByBatch(batchID));
		return trainees;
	}

}