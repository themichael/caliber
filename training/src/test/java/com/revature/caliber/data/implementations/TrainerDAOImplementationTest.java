package com.revature.caliber.data.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.training.beans.Tier;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.data.TrainerDAO;

/**
 *  Test for TrainerDAOImplementation.
 */
public class TrainerDAOImplementationTest {

	private static ApplicationContext context;
	private static Logger logger;

	@BeforeClass
	public static void preClass() {
		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
		logger = Logger.getRootLogger();
        logger.debug("\n--- TRAINER DAO IMPLEMENTATION TEST START ---\n");
	}
	
	@AfterClass
    public static void afterClass() {
        logger.debug("\n--- TRAINER DAO IMPLEMENTATION TEST END ---\n");
    }
	
	@Test
	public void createTrainerTest(){
		logger.debug("   Create trainer test.");
		TrainerDAO trainerDao = (TrainerDAO) context.getBean(TrainerDAO.class);
		
		Tier tier = new Tier();
		tier.setTierId((short)1);
		
		Trainer trainer = new Trainer();
		trainer.setName("Bob Millerrrrrrr");
		trainer.setTitle("Trainer at QC");
		trainer.setEmail("bmiller@gmail.com");
		trainer.setSalesforceAccount("salesforceAccountEXAMPLE");
		trainer.setSalesforceAuthenticationToken("salesforceAuthenticationTokenEXAMPLE");
		trainer.setSalesforceRefreshToken("salesforceRefreshTokenEXAMPLE");
		trainer.setTier(tier);
		
		trainerDao.createTrainer(trainer);
		assertTrue(true);
		
		logger.debug("     trainer created");
	}
	
	@Test
    public void getTrainerGetByIdTest() {
		logger.debug("   Get trainer by id test.");
		
        TrainerDAO trainerDao = (TrainerDAO) context.getBean(TrainerDAO.class);

        Trainer trainer = trainerDao.getTrainer(1);

        assertNotNull(trainer);
        assertEquals(1, trainer.getTrainerId());
        
        logger.debug("     trainer that I got:" + trainer);
        logger.debug("       trainer id: " + trainer.getTrainerId());
    }
	
	@Test
    public void getTrainerGetByEmailTest() {
		logger.debug("   Get trainer by email test.");
		
        TrainerDAO trainerDao = (TrainerDAO) context.getBean(TrainerDAO.class);

        Trainer trainer = trainerDao.getTrainer("testrevature@gmail.com");

        assertNotNull(trainer);
        logger.debug("     trainer that I got:" + trainer);
        logger.debug("       trainer name: " + trainer.getName());
    }
	
	@Test
    public void getAllTrainersTest() {
		logger.debug("   Get all trainers test.");
		
        TrainerDAO trainerDao = (TrainerDAO) context.getBean(TrainerDAO.class);

        List<Trainer> trainers = trainerDao.getAllTrainers();

        assertNotNull(trainers);
        logger.debug("     trainers that I got " + trainers);
        logger.debug("     their size(should be at least 1): " + trainers.size());
    }
	
	@Test
	public void updateTrainerTest() {
		logger.debug("   Update trainer test.");
		
		 TrainerDAO trainerDao = (TrainerDAO) context.getBean(TrainerDAO.class);
		 Trainer trainer = trainerDao.getTrainer(300);
	     assertNotNull(trainer);

	     String newName = "Lila Miller";
	     trainer.setName(newName);

	     int id = trainer.getTrainerId();
	     trainerDao.updateTrainer(trainer);
	     logger.debug("     updated trainer");
	     trainer = trainerDao.getTrainer(id);
	     assertNotNull(trainer);
	     
	     logger.debug("     checking trainer:");
	     logger.debug("       trainer that I got: " + trainer);
	     logger.debug("       it's name: " + trainer.getName());
	 }
}