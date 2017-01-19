package com.revature.caliber.data.implementations;


import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.data.BatchDAO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class BatchDAOImplementationTest {
    private static ApplicationContext context;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
    }

    @Test
    public void createBatch(){
        BatchDAO batchDAO = (BatchDAO)context.getBean("batchDAO");

    }

    @Test
    public void getAll(){
        BatchDAO batchDAO = (BatchDAO) context.getBean("batchDAO");
        List<Batch> batch = batchDAO.getAll();
        assertNotNull(batch);
    }
}
