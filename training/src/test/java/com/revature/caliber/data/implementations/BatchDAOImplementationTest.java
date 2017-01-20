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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BatchDAOImplementationTest {
    private static ApplicationContext context;
    private static Logger log;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        log = Logger.getRootLogger();
    }

    //Could not test
    @Test
    public void createBatch(){
        BatchDAO batchDAO = (BatchDAO)context.getBean("batchDAO");
        TrainerDAO trainerDAO = (TrainerDAO)context.getBean("trainerDAO");
        Trainer trainer = trainerDAO.getTrainer(1);
        Tier tier = new Tier();
        tier.setTierId((short)1);
        /*Trainer trainer = new Trainer("Dan Pickles","title", "email", "account",
                "token", "token", tier, null);*/
        Date startDate = new Date(1,500,993,945,323);
        Batch batch = new Batch("trainingName", trainer, null, "skillType", "trainingType",
                startDate, new Date(), "New York", (short) 60, (short)80,
                null, null);
        batchDAO.createBatch(batch);
        assertTrue(true);
    }

    // Works
    @Test
    public void getAll(){
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        List<Batch> batch = batchDAO.getAllBatch();
        System.out.println(batch);
        log.debug("In get all");
        assertNotNull(batch);
    }

    //Work
    @Test
    public void getTrainerBatch(){
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        List<Batch> batch = batchDAO.getTrainerBatch(1);
        System.out.println(batch);
        //log.debug("In get all");
        //assertNotNull(batch);
    }

    // Works
    @Test
    public void getCurrentBatch(){
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        List<Batch> batch = batchDAO.getCurrentBatch();
        System.out.println(batch);
    }

    //Work
    @Test
    public void getCurrentBatchWithId(){
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        List<Batch> batch = batchDAO.getCurrentBatch(1);
    }

    //Works
    @Test
    public void getBatch(){
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        Batch batch = batchDAO.getBatch(1);
    }

    //Works
    @Test
    public void updateBatch(){
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        Batch batch = batchDAO.getBatch(1);
        batch.setLocation("New York");
        batchDAO.updateBatch(batch);
    }

    @Test
    public void deleteBatch(){
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
    }
}