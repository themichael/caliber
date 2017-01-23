package com.revature.caliber.web.controller.implementations;


import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Tier;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.data.TrainerDAO;
import com.revature.caliber.training.web.controllers.BatchController;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.HttpEntity;

import java.util.Date;
import java.util.List;

public class BatchControllerImplementationTest {
    private static ApplicationContext context;
    private static Logger log;
    private static BatchController controller;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        controller = context.getBean(BatchController.class);
        log = Logger.getRootLogger();
    }

    //Works
    @Test
    public void createBatch(){
        log.debug("Create batch test.");

        TrainerDAO trainerDAO = context.getBean(TrainerDAO.class);
        Trainer trainer = trainerDAO.getTrainer(1);
        Tier tier = new Tier();
        tier.setTierId((short)1);
        Date startDate = new Date(1481394352000L);
        Date endDate = new Date(1458757552000L);
        Batch batch = new Batch(9999, "Batch5423", trainer, null, "Junior Level", "Java2EE",
                startDate, endDate, "Queens College", (short) 60, (short)80,
                null, null);
        controller.createBatch(batch);

        log.debug("Batch created");
    }

    // Works
    @Test
    public void getAll(){
        log.debug(" Get all Batch test");

        HttpEntity<List<Batch>> entity = controller.getAllBatches();
        List<Batch> batches = entity.getBody();

        log.debug("Got All " + batches);
    }

    //Work
    @Test
    public void getTrainerBatch(){
        log.debug("Get batch by Trainer id");

        HttpEntity<List<Batch>> entity = controller.getTrainerBatch(1);
        List<Batch> batches = entity.getBody();

        log.debug("got batches by trainer id " + batches);
    }

    // Works
    @Test
    public void getCurrentBatch(){
        log.debug("Get active batches");

        HttpEntity<List<Batch>> entity = controller.getCurrentBatch();
        List<Batch> batches = entity.getBody();
        System.out.println(batches);

        log.debug("Got active batches " +  batches);
    }

    //Work
    @Test
    public void getCurrentBatchWithId(){
        log.debug("Get active batches with trainer id");

        HttpEntity<List<Batch>> entity = controller.getCurrentBatch(1);
        List<Batch> batches = entity.getBody();

        log.debug("Got active batches with trainer id " + batches);
    }

    //Works
    @Test
    public void getBatch(){
        log.debug("Get batch by id");

        HttpEntity<Batch> entity = controller.getBatch(2);
        Batch batch = entity.getBody();

        log.debug("got batch by id " + batch);
    }

    //Works
    @Test
    public void updateBatch(){
        log.debug("Updating batch");

        HttpEntity<Batch> entity = controller.getBatch(6);
        Batch toUpdate = entity.getBody();
        toUpdate.setTrainingName("Batch3425");
        toUpdate.setTrainingType("Java2EE");
        toUpdate.setLocation("Revature");
        toUpdate.setSkillType("Junior Level");
        controller.updateBatch(toUpdate);

        log.debug("updated batch");
    }
}
