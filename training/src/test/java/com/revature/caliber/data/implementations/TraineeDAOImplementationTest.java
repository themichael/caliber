package com.revature.caliber.data.implementations;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.data.TraineeDAO;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 *  Test for TraineeDAOImplementation. Assumes that DB has at least one batch with id 1.
 */
public class TraineeDAOImplementationTest {

    private static ApplicationContext context;
    private static Logger logger;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        logger = Logger.getRootLogger();

        logger.debug("\n--- TRAINEE DAO IMPLEMENTATION TEST START ---\n");
    }

    @AfterClass
    public static void afterClass() {
        logger.debug("\n--- TRAINEE DAO IMPLEMENTATION TEST END ---\n");
    }


    @Test
    public void createTraineeTest() {
        logger.debug("   Create trainee test.");

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

        logger.debug("     trainee created");
    }

    @Test
    public void getTraineeTestGetById() {
        logger.debug("   Get trainee by id test.");

        TraineeDAO dao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");

        logger.debug("     using id 1");
        Trainee trainee = dao.getTrainee(1);


        assertNotNull(trainee);
        assertEquals(1, trainee.getTraineeId());

        logger.debug("     trainee that I got:" + trainee);
        logger.debug("       trainee id: " + trainee.getTraineeId());
    }

    @Test
    public void getTraineeTestGetByName(){
        logger.debug("   Get trainee by name test.");
        logger.debug("     trying to get previously create trainee \"Super Mario Bros\"");

        TraineeDAO dao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");

        Trainee trainee = dao.getTrainee("Super Mario Bros");

        assertNotNull(trainee);
        assertEquals("Super Mario Bros", trainee.getName());

        logger.debug("     trainee that I got:" + trainee);
        logger.debug("       trainee name: " + trainee.getName());
    }

    @Test
    public void getTraineesInBatchTest(){
        logger.debug("   Get trainees in a batch test.");
        logger.debug("     \"Super Mario Bros\" trainee is in the batch with id 1");

        TraineeDAO dao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");

        List<Trainee> trainees = dao.getTraineesInBatch(1);

        assertNotNull(trainees);
        assertNotEquals(0, trainees.size());

        logger.debug("     trainees that I got " + trainees);
        logger.debug("     their size(should be at least 1): " + trainees.size());
    }

    @Test
    public void updateTraineeTest(){
        logger.debug("   Update trainee test.");
        logger.debug("     let's take \"Super Mario Bros\" and change it's name");

        TraineeDAO dao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");

        Trainee trainee = dao.getTrainee("Super Mario Bros");
        assertNotNull(trainee);

        String newName = "Trololo lolo lolo";
        trainee.setName(newName);

        int id = trainee.getTraineeId();

        dao.updateTrainee(trainee);

        logger.debug("     updated trainee");

        trainee = dao.getTrainee(id);
        assertNotNull(trainee);
        assertEquals(newName, trainee.getName());

        logger.debug("     checking trainee:");
        logger.debug("       trainee that I got: " + trainee);
        logger.debug("       it's name: " + trainee.getName());
    }

    @Test
    public void deleteTraineeTest() {
        logger.debug("   Delete trainee test.");
        logger.debug("     let's get trainee with id 1 and just wipe it!");

        TraineeDAO dao = (TraineeDAO) context.getBean("trainingTraineeDAOImplementation");

        Trainee trainee = dao.getTrainee("Trololo lolo lolo");
        assertNotNull(trainee);

        logger.debug("     trainee was read.");

        int id = trainee.getTraineeId();

        dao.deleteTrainee(trainee);

        trainee = dao.getTrainee(id);
        assertNull(trainee);

        logger.debug("     trainee with id [" + id + "] was deleted.");
    }


}
