package com.revature.caliber.data.implementations;


import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.beans.Trainer;
import com.revature.caliber.training.beans.Week;
import com.revature.caliber.training.data.BatchDAO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertNotNull;

public class BatchDAOImplementationTest {
    private static ApplicationContext context;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
    }

    @Test
    public void createBatch(){
        BatchDAO batchDAO = (BatchDAO)context.getBean(BatchDAO.class);
        Date startDate = new Date(1,500,993,945,323);
        Batch batch = new Batch("trainingName", new Trainer(), null, "skillType", "trainingType",
                startDate, new Date(), "New York", (short) 60, (short)80,
        null, null);

    }

    @Test
    public void getAll(){
        BatchDAO batchDAO = (BatchDAO) context.getBean(BatchDAO.class);
        List<Batch> batch = batchDAO.getAllBatch();
        assertNotNull(batch);
    }
    @Test
    public void getTrainerBatch(){
        BatchDAO batchDAO = (BatchDAO) context.getBean(BatchDAO.class);
    }

    @Test
    public void getCurrentBatch(){
        BatchDAO batchDAO = (BatchDAO) context.getBean(BatchDAO.class);
    }

    @Test
    public void getCurrentBatchWithName(){
        BatchDAO batchDAO = (BatchDAO) context.getBean(BatchDAO.class);
    }

    @Test
    public void getBatch(){
        BatchDAO batchDAO = (BatchDAO) context.getBean(BatchDAO.class);
    }

    @Test
    public void updateBatch(){
        BatchDAO batchDAO = (BatchDAO) context.getBean(BatchDAO.class);
    }

    @Test
    public void deleteBatch(){
        BatchDAO batchDAO = (BatchDAO) context.getBean(BatchDAO.class);
    }
}
