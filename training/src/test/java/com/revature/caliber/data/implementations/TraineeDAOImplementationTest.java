package com.revature.caliber.data.implementations;

import com.revature.caliber.training.beans.Batch;
import com.revature.caliber.training.beans.Trainee;
import com.revature.caliber.training.data.TraineeDAO;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 *  Test for TraineeDAOImplementation.
 *  Assumes that DB has at least one batch with id 1 and at least one trainee with id 1. (IMPORTANT)
 */
public class TraineeDAOImplementationTest {

    private static ApplicationContext context;
    private static SessionFactory sf;
    private static Logger logger;

    private static int newBatchId, newTrainerId, newCategoryId;

    @BeforeClass
    public static void preClass () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");


        /*
        CALIBER_TIER - > TIER_ID, RANKING, TIER;
        CALIBER_TRAINER - > TRAINER_ID, EMAIL, NAME, SF_ACCOUNT, SF_AUTHENTICATION_TOKEN, SF_REFRESH_TOKEN, TITLE, TIER(int)
        CALIBER_BATCH -> BATCH_ID, BORDERLINE_GRADE_THRESHOLD, END_DATE, GOOD_GRADE_THRESHOLD, LOCATION, SKILL_TYPE, START_DATE, TRAINING_NAME, TRAINING_TYPE, CO_TRAINER_ID, TRAINER_ID
         */
        //create transient SQL test data
        sf = (SessionFactory) context.getBean("sessionFactory");
        Session session = sf.openSession();
        String sql = "";
        int index = 0;
        int resultNum = 0;

        index = 1;
        do {
            sql = "SELECT count(*) from CALIBER_TIER where TIER_ID  = ?";
            Query q = session.createSQLQuery(sql);
            q.setInteger(1, index++);
            resultNum = q.getFirstResult();
            break;
        } while (resultNum > 0);

        newCategoryId = index - 1;

        sql = "INSERT INTO CALIBER_TIER(TIER_ID, RANKING, TIER) VALUES(?, ?, ?)";
        Query tierq = session.createSQLQuery(sql);
        tierq.setInteger(1, newCategoryId);
        tierq.setInteger(2, 1);
        tierq.setString(3, "Test tier (TraineeDAO Test)");

        resultNum = tierq.executeUpdate();

        if (resultNum != 1) {
            fail("Failed to create test tier");
        }

        index = 1;
        do {
            sql = "SELECT count(*) FROM CALIBER_TRAINER WHERE TRAINER_ID = ?";
            Query q = session.createSQLQuery(sql);
        } while (resultNum > 0);

        newTrainerId = index - 1;
        sql = "INSERT INTO CALIBER_CATEGORY() VALUES();" +
                    "INSERT INTO CALIBER_TRAINER() VALUES();" +
                    "INSERT INTO CALIBER_BATCH() VALUES();";
        Query query = session.createSQLQuery(sql);
        int rowsAffected = query.executeUpdate();
        if (rowsAffected < 1) {
            fail("Failed to create test data.");
        }
        session.close();

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

        TraineeDAO dao = context.getBean(TraineeDAO.class);

        Batch batch = new Batch();
        batch.setBatchId(1);

        Trainee trainee = new Trainee();
        trainee.setTraineeId(1);
        trainee.setName("Super Mario Bros");
        trainee.setEmail("tismario@mario.io");
        trainee.setTrainingStatus("Super Dope");
        trainee.setBatch(batch);

        dao.createTrainee(trainee);
       // assertTrue(true); //if doesn't throw Exception, means created

        logger.debug("     trainee created");
    }

    @Test
    public void getTraineeTestGetById() {
        logger.debug("   Get trainee by id test.");

        TraineeDAO dao = (TraineeDAO) context.getBean(TraineeDAO.class);

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

        TraineeDAO dao = (TraineeDAO) context.getBean(TraineeDAO.class);

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

        TraineeDAO dao = (TraineeDAO) context.getBean(TraineeDAO.class);

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

        TraineeDAO dao = (TraineeDAO) context.getBean(TraineeDAO.class);

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

        TraineeDAO dao = (TraineeDAO) context.getBean(TraineeDAO.class);

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
