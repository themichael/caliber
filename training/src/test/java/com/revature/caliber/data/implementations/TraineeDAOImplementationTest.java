package com.revature.caliber.data.implementations;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.data.TraineeDAO;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 *  Test for TraineeDAOImplementation
 */
public class TraineeDAOImplementationTest {

    private static ApplicationContext context;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
    }

    @Test
    public void createTraineeTest() {
        TraineeDAO dao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");

        Batch batch = new Batch();
        batch.setBatchId(1);

        Trainee trainee = new Trainee();
        trainee.setTraineeId(1);
        trainee.setName("Super Mario Bros");
        trainee.setEmail("tismario@mario.io");
        trainee.setTrainingStatus("Super Dope");
        trainee.setBatch(batch);

        dao.createTrainee(trainee);
        assertTrue(true); //if doesn't throw Exception, means created
    }

    @Test
    public void getTraineeTestGetById() {
        TraineeDAO dao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");

        Trainee trainee = dao.getTrainee(1);

        assertNotNull(trainee);
    }

    @Test
    public void getTraineeTestGetByName(){
        TraineeDAO dao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");

        Trainee trainee = dao.getTrainee("Super Mario Bros");

        assertNotNull(trainee);
    }

    @Test
    public void getTraineesInBatchTest(){
        TraineeDAO dao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");

        List<Trainee> trainees = dao.getTraineesInBatch(1);

        assertNotNull(trainees);
        assertNotEquals(0, trainees.size());
    }

    @Test
    public void updateTraineeTest(){
        TraineeDAO dao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");

        Trainee trainee = dao.getTrainee(1);
        assertNotNull(trainee);

        String newName = "Trololo lolo lolo";
        trainee.setName(newName);

        dao.updateTrainee(trainee);

        trainee = dao.getTrainee(1);
        assertNotNull(trainee);
        assertEquals(newName, trainee.getName());
    }

    @Test
    public void deleteTraineeTest() {
        TraineeDAO dao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");

        Trainee trainee = dao.getTrainee(1);
        assertNotNull(trainee);

        dao.deleteTrainee(trainee);

        trainee = dao.getTrainee(1);
        assertNull(trainee);
    }


}
