package com.revature.caliber.data.implementations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.training.beans.Tier;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.data.TrainerDAO;

/**
 *  Test for TrainerDAOImplementation
 *  
 *  @author Karina
 */
public class TrainerDAOImplementationTest {

	private static ApplicationContext context;

	@BeforeClass
	public static void preClass() {
		context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
	}
	
	@Test
	public void createTrainerTest(){
		TrainerDAO trainerDao = (TrainerDAO) context.getBean("trainerDao");
		
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
		
		trainerDao.createTrainer(trainer);
		assertTrue(true);
		
		System.out.println("Created trainer: " + trainer);
	}
	
	@Test
    public void getTrainerGetByIdTest() {
        TrainerDAO trainerDao = (TrainerDAO) context.getBean("trainerDao");

        Trainer trainer = trainerDao.getTrainer(1);

        assertNotNull(trainer);
        assertEquals(1, trainer.getTraineeId());
        
        System.out.println("Got trainer by id: " + trainer);
    }
	
	@Test
    public void getTrainerGetByNameTest() {
        TrainerDAO trainerDao = (TrainerDAO) context.getBean("trainerDao");

        List<Trainer> trainer = trainerDao.getTrainer("Bob Miller");

        assertNotNull(trainer);
        System.out.println("Got trainer by name: " + trainer);
    }
	
	@Test
    public void getAllTrainersTest() {
        TrainerDAO trainerDao = (TrainerDAO) context.getBean("trainerDao");

        List<Trainer> trainer = trainerDao.getAllTrainers();

        assertNotNull(trainer);
        System.out.println("Got all trainers: " + trainer);
    }
}
