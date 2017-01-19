package com.revature.caliber.assessments.data.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.data.AssessmentDAO;
import org.apache.log4j.Logger;
import org.junit.*;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.HashSet;

import static org.junit.Assert.*;

public class AssessmentDAOImplTest {

    private static AbstractApplicationContext context;
    private AssessmentDAO assessmentDAO;
    private static Logger log;

    @BeforeClass
    public static void setUp () {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        // rootLogger is for debugging purposes
        log = Logger.getRootLogger();
    }
    @Before
    public void setUpTest() {
        assessmentDAO = (AssessmentDAO) context.getBean("assessmentDAO");
    }

    @Test
    public void getAll() {
        HashSet<Assessment> assessments = assessmentDAO.getAll();
        assertNotNull(assessments);
    }

    @Test
    public void getByTrainerId() {
        int trainerId = 1;
        HashSet<Assessment> assessments = assessmentDAO.getByTrainerId(trainerId);
        assertNotNull(assessments);
    }

    @Test
    public void getByWeekId() {
        int weekId = 1;
        HashSet<Assessment> assessments = assessmentDAO.getByWeekId(weekId);
        assertNotNull(assessments);
    }

    @Test
    public void getByBatchId() {
        int batchId = 1;
        HashSet<Assessment> assessments = assessmentDAO.getByWeekId(batchId);
        assertNotNull(assessments);
    }

    @Test
    public void insert() {
        Assessment assessment = new Assessment();
        assessment.setAssessmentId(1);
        assessment.setTitle("Week 1 Test");
        assessment.setBatch(1);
        assessment.setRawScore(100);
        assessment.setType("LMS");
        assessment.setWeek(1);

        assessmentDAO.insert(assessment);
        assertTrue(true);
    }

    @Test
    public void delete() {
        int assessmentId = 1;
        Assessment assessment = assessmentDAO.getById(assessmentId);
        assertNotNull(assessment);

        assessmentDAO.delete(assessment);

        assessment = assessmentDAO.getById(assessmentId);
        assertNotNull(assessment);
    }

}