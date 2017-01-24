package com.revature.caliber.web.controller.implementations;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.training.beans.Tier;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.data.TrainerDAO;
import com.revature.caliber.training.web.controllers.TrainerController;

public class TrainerControllerImplementationTest {
	private static ApplicationContext context;
    private static Logger log;
    private static TrainerController controller;
    private static Trainer trainer = null;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        controller = context.getBean(TrainerController.class);
        log = Logger.getRootLogger();
        log.debug("\n--- TRAINER CONTROLLER IMPLEMENTATION TEST START ---\n");
    }
	
    public static void populateData(){
		Tier tier = new Tier();
		tier.setTierId((short)1);
		trainer = new Trainer();
		trainer.setName("Bob Miller");
		trainer.setTitle("Trainer at QC");
		trainer.setEmail("bmiller@revature.com");
		trainer.setSalesforceAccount("salesforceAccountEXAMPLE");
		trainer.setSalesforceAuthenticationToken("salesforceAuthenticationTokenEXAMPLE");
		trainer.setSalesforceRefreshToken("salesforceRefreshTokenEXAMPLE");
		trainer.setTier(tier);
	}
    
    @Before
    public void before(){
    	populateData();
    }
    
    public static void deleteData(){
		TrainerDAO trainerDao = (TrainerDAO) context.getBean(TrainerDAO.class);
		trainerDao.deleteTrainer(trainer);
	}
	
	@After
	public void after(){
		deleteData();
	}
	
	@AfterClass
    public static void afterClass() {
        log.debug("\n--- TRAINER CONTROLLER IMPLEMENTATION TEST END ---\n");
    }
    
    @Test
    public void createBatch(){
        log.debug("Create trainer test.");
        controller.createTrainer(trainer);
        log.debug("Trainer created");
    }

}
