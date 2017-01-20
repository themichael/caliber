package com.revature.caliber.data.implementations;


import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Tier;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.data.BatchDAO;
import com.revature.caliber.training.data.TrainerDAO;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Date;
import java.util.List;

public class BatchDAOImplementationTest {
    private static ApplicationContext context;
    private static Logger log;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        log = Logger.getRootLogger();
    }

    //Could not test
/*    @Test
    public void createBatch(){
        log.debug("Create batch test.");

        BatchDAO batchDAO = (BatchDAO)context.getBean("batchDAO");
        TrainerDAO trainerDAO = (TrainerDAO)context.getBean("trainerDAO");
        Trainer trainer = trainerDAO.getTrainer(1);
        Tier tier = new Tier();
        tier.setTierId((short)1);
        *//*Trainer trainer = new Trainer("Dan Pickles","title", "email", "account",
                "token", "token", tier, null);*//*
        Date startDate = new Date(1,500,993,945,323);
        Batch batch = new Batch("trainingName", trainer, null, "skillType", "trainingType",
                startDate, new Date(), "New York", (short) 60, (short)80,
                null, null);
        batchDAO.createBatch(batch);
        log.debug("Batch created");
    }*/

    // Works
    @Test
    public void getAll(){
        log.debug(" Get all Batch test");
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        List<Batch> batch = batchDAO.getAllBatch();
        log.debug("Got All");
    }

    //Work
    @Test
    public void getTrainerBatch(){
        log.debug("Get batch by Trainer id");
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        List<Batch> batch = batchDAO.getTrainerBatch(1);
        log.debug("got batches by trainer id");
    }

    // Works
    @Test
    public void getCurrentBatch(){
        log.debug("Get active batches");
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        List<Batch> batch = batchDAO.getCurrentBatch();
        log.debug("Got active batches");
    }

    //Work
    @Test
    public void getCurrentBatchWithId(){
        log.debug("Get active batches with trainer id");
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        List<Batch> batch = batchDAO.getCurrentBatch(1);
        log.debug("Got active batches with trainer id");
    }

    //Works
    @Test
    public void getBatch(){
        log.debug("Get batch by id");
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        Batch batch = batchDAO.getBatch(1);
        log.debug("got batch by id");
    }

    //Works
    @Test
    public void updateBatch(){
        log.debug("Updating batch");
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        Batch batch = batchDAO.getBatch(1);
        batch.setLocation("New York");
        batchDAO.updateBatch(batch);
        log.debug("updated batch");
    }

    //Could not test
/*    @Test
    public void deleteBatch(){
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
    }*/
}