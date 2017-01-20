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

    //Works
    @Test
    public void createBatch(){
        log.debug("Create batch test.");

        BatchDAO batchDAO = context.getBean(BatchDAO.class);
        TrainerDAO trainerDAO = context.getBean(TrainerDAO.class);
        Trainer trainer = trainerDAO.getTrainer(1);
        Tier tier = new Tier();
        tier.setTierId((short)1);
        Date startDate = new Date(1481394352000L);
        Date endDate = new Date(1458757552000L);
        Batch batch = new Batch("trainingName", trainer, null, "skillType", "trainingType",
                startDate, endDate, "Virgina", (short) 60, (short)80,
                null, null);
        batchDAO.createBatch(batch);
        log.debug("Batch created");
    }

    // Works
    @Test
    public void getAll(){
        log.debug(" Get all Batch test");
        BatchDAO batchDAO = context.getBean(BatchDAO.class);
        List<Batch> batch = batchDAO.getAllBatch();
        log.debug("Got All");
    }

    //Work
    @Test
    public void getTrainerBatch(){
        log.debug("Get batch by Trainer id");
        BatchDAO batchDAO = context.getBean(BatchDAO.class);
        List<Batch> batch = batchDAO.getTrainerBatch(1);
        log.debug("got batches by trainer id");
    }

    // Works
    @Test
    public void getCurrentBatch(){
        log.debug("Get active batches");
        BatchDAO batchDAO = context.getBean(BatchDAO.class);
        List<Batch> batch = batchDAO.getCurrentBatch();
        log.debug("Got active batches");
    }

    //Work
    @Test
    public void getCurrentBatchWithId(){
        log.debug("Get active batches with trainer id");
        BatchDAO batchDAO = context.getBean(BatchDAO.class);
        List<Batch> batch = batchDAO.getCurrentBatch(1);
        log.debug("Got active batches with trainer id");
    }

    //Works
    @Test
    public void getBatch(){
        log.debug("Get batch by id");
        BatchDAO batchDAO = context.getBean(BatchDAO.class);
        Batch batch = batchDAO.getBatch(1);
        log.debug("got batch by id");
    }

    //Works
    @Test
    public void updateBatch(){
        log.debug("Updating batch");
        BatchDAO batchDAO = context.getBean(BatchDAO.class);
        Batch batch = batchDAO.getBatch(1);
        batch.setLocation("New York");
        batchDAO.updateBatch(batch);
        log.debug("updated batch");
    }

    //Works
   @Test
    public void deleteBatch(){
        log.debug("Deleting batch");
        BatchDAO batchDAO = context.getBean(BatchDAO.class);
        Batch batch = batchDAO.getBatch(1100);
        batchDAO.deleteBatch(batch);
        log.debug("Deleted batch");
    }
}