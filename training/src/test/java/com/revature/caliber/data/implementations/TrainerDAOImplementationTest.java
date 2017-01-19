package com.revature.caliber.data.implementations;

import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Tier;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.data.BatchDAO;
import com.revature.caliber.training.data.TierDAO;
import com.revature.caliber.training.data.TraineeDAO;
import com.revature.caliber.training.data.TrainerDAO;

public class TrainerDAOImplementationTest {

	private static ApplicationContext context;

	@BeforeClass
	public static void preClass() {
		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
	}
	
	@Test
	public void createTrainerTest() throws ParseException{
		/*TrainerDAO trainerDao = (TrainerDAO) context.getBean("trainingTrainerDAOImplementation");
		TierDAO tierDao = (TierDAO) context.getBean("trainingTierDAOImplementation");
		TraineeDAO traineeDao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");
		BatchDAO batchDao = (BatchDAO) context.getBean("trainingBatchDAOImplementation");
		
		Tier tier = new Tier("Trainer", (short) 1);
		tierDao.createTier(tier);
		Trainer trainer = 
				new Trainer("Bob Miller", "Trainer at QC", "bmiller@gmail.com", "bob@salesforce.com", 
						"salesforceAuthenticationToken", "salesforceRefreshToken", tier);
		 
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
		Date startDate1 = dateFormat.parse("09-01-2016");
		Date endDate1 = dateFormat.parse("02-05-2017");
		Trainee trainee1 = new Trainee("Melissa Lin", "mlin@gmail.com", "Active", null);
		traineeDao.createTrainee(trainee1);
		Trainee trainee2 = new Trainee("Chris Flores", "cflores@gmail.com", "Active", null);
		traineeDao.createTrainee(trainee2);
		Set<Trainee> trainees = new HashSet<>();
		trainees.add(trainee1); trainees.add(trainee2);
		
		Batch batch1 = new Batch("QC", trainer, null, "Skills1", "trainingType", startDate1, endDate1, 
				"Flushing, NY", (short)60, (short)40, trainees, null);
		trainee1.setBatch(batch1);
		trainee2.setBatch(batch1);
		Set<Batch> trainerBatch = new HashSet<>();
		trainerBatch.add(batch1);
		batchDao.createBatch(batch1);
		trainer.setBatches(trainerBatch);
		trainerDao.createTrainer(trainer);
		
		assertTrue(true); */
		
		Tier tier = new Tier();
		tier.setTierId((short)1);
		
		Trainer trainer = new Trainer();
		trainer.setName("Bob Miller");
		trainer.setTitle("Trainer at QC");
		trainer.setEmail("bmiller@gmail.com");
		trainer.setSalesforceAccount("salesforceAccountEXAMPLE");
		trainer.setSalesforceAuthenticationToken("salesforceAuthenticationTokenEXAMPLE");
		trainer.setSalesforceRefreshToken("salesforceRefreshTokenEXAMPLE");
		trainer.setTier(tier);
	}
}
