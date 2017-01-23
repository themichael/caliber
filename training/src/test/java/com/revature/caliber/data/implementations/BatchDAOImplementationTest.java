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
    private static  BatchDAO batchDAO;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        batchDAO = context.getBean(BatchDAO.class);
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
        Batch batch = new Batch(9999, "trainingName", trainer, null, "skillType", "trainingType",
                startDate, endDate, "Virgina", (short) 60, (short)80,
                null, null);

        batchDAO.createBatch(batch);
        log.debug("Batch created");
    }

    // Works
    @Test
    public void getAll(){
        log.debug(" Get all Batch test");
        List<Batch> batch = batchDAO.getAllBatch();
        log.debug("Got All " + batch );
    }

    //Work
    @Test
    public void getTrainerBatch(){
        log.debug("Get batch by Trainer id");
        List<Batch> batch = batchDAO.getTrainerBatch(1);
        log.debug("got batches by trainer id " + batch);
    }

    // Works
    @Test
    public void getCurrentBatch(){
        log.debug("Get active batches");
        List<Batch> batch = batchDAO.getCurrentBatch();
        log.debug("Got active batches " + batch);
    }

    //Work
    @Test
    public void getCurrentBatchWithId(){
        log.debug("Get active batches with trainer id");

        List<Batch> batch = batchDAO.getCurrentBatch(1);

        log.debug("Got active batches with trainer id " + batch);
    }

    //Works
    @Test
    public void getBatch(){
        log.debug("Get batch by id");

        Batch batch = batchDAO.getBatch(1);

        log.debug("got batch by id " + batch);
    }

    //Works
    @Test
    public void updateBatch(){
        log.debug("Updating batch");

        Batch batch = batchDAO.getBatch(1);
        batch.setLocation("New York");
        batchDAO.updateBatch(batch);

        log.debug("updated batch");
    }

    //Work on delete method
}