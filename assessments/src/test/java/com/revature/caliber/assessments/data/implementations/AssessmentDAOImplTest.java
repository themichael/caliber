package com.revature.caliber.assessments.data.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.data.AssessmentDAO;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Set;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class AssessmentDAOImplTest {

    private static AbstractApplicationContext context;
    private static Logger log;
    private AssessmentDAO assessmentDAO;

    @BeforeClass
    public static void setUp() {
        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        // rootLogger is for debugging purposes
        log = Logger.getRootLogger();

        log.debug("Starting AssessmentDAOImplTest");
        populateTable();
    }

    /**
     * Populates table with Assessment used for testing
     */
    public static void populateTable() {
        //Adding required data
        AssessmentDAO assessmentDAO = (AssessmentDAO) context.getBean("assessmentDAO");

        Assessment assessment = new Assessment();
        assessment.setAssessmentId(4100);
        assessment.setTitle("Week 1 Test");
        assessment.setBatch(1);
        assessment.setRawScore(100);
        assessment.setType("LMS");
        assessment.setWeek(1);

        assessmentDAO.insert(assessment);
    }

    @Before
    public void setUpTest() {
        assessmentDAO = (AssessmentDAO) context.getBean("assessmentDAO");
    }

    @Test
    public void getAll() {
        log.debug("Starting getAllAssessmentsTest");

        Set<Assessment> assessments = assessmentDAO.getAll();
        assertFalse(assessments.isEmpty());

        log.debug("Ending getAllAssessmentsTest");
    }

    @Test
    public void getById() {
        long id = 4100;
        log.debug("Starting getAssessmentById = " + id);
        System.out.println("Starting getAssessmentById = " + id);

        Assessment assessment = assessmentDAO.getById(id);
        System.out.println(assessment);
        assertNotNull(assessment);

        log.debug("Ending getAssessmentById");
    }

    @Test
    public void getByWeekId() {
        int weekId = 1;
        log.debug("Starting getAssessmentsByWeekId with id = " + weekId);

        Set<Assessment> assessments = assessmentDAO.getByWeekId(weekId);
        assertFalse(assessments.isEmpty());

        log.debug("Ending getByWeekId");
    }

    @Test
    public void insert() {
        log.debug("Starting insertAssessment");

        Assessment assessment = new Assessment();
        assessment.setAssessmentId(1);
        assessment.setTitle("Week 1 Test");
        assessment.setBatch(1);
        assessment.setRawScore(100);
        assessment.setType("LMS");
        assessment.setWeek(1);

        assessmentDAO.insert(assessment);
        assertTrue(true);

        log.debug("Ending insertAssessment");
    }

    @Test
    public void delete() {
        int assessmentId = 4550;

        log.debug("Starting test to fetch, delete, then fetch Assessment with id = " + assessmentId
                + "to make sure delete is functional");

        Assessment assessment = assessmentDAO.getById(assessmentId);

        if (assessment != null) {
            assessmentDAO.delete(assessment);
            assessment = assessmentDAO.getById(assessmentId);
            assertNull(assessment);
        }

        //populating table again for other tests
        populateTable();
    }

    @AfterClass
    public static void cleanUp() {

        AssessmentDAO assessmentDAO = (AssessmentDAO) context.getBean("assessmentDAO");

        int assessmentId = 4550;

        Assessment assessment = assessmentDAO.getById(assessmentId);

        if (assessment != null) {
            assessmentDAO.delete(assessment);
            assessment = assessmentDAO.getById(assessmentId);
            assertNull(assessment);
        }

        log.debug("Ending AssessmentDAOImplTest");
    }

}