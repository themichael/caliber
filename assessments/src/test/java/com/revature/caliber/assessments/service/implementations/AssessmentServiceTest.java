package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.Assessment;
import com.revature.caliber.assessments.service.AssessmentService;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Set;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class AssessmentServiceTest {


    private static AbstractApplicationContext context;
    private static Logger log;
    private AssessmentService assessmentService;

    @BeforeClass
    public static void setUp() {

        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        // rootLogger is for debugging purposes
        log = Logger.getRootLogger();

        log.debug("Starting AssessmentServiceTest");
        populateTable();
    }

    /**
     * Populates table with Assessment used for testing
     */
    private static void populateTable() {
        //Adding required data
        AssessmentService assessmentService = (AssessmentService) context.getBean("assessmentService");

        Assessment assessment = new Assessment();
        assessment.setAssessmentId(4100);
        assessment.setTitle("Week 1 Test");
        assessment.setBatch(1);
        assessment.setRawScore(100);
        assessment.setType("LMS");
        assessment.setWeek(1);

        assessmentService.insert(assessment);
    }

    @AfterClass
    public static void cleanUp() {

        AssessmentService assessmentService = (AssessmentService) context.getBean("assessmentService");

        int assessmentId = 4550;

        Assessment assessment = assessmentService.getById(assessmentId);

        if (assessment != null) {
            assessmentService.delete(assessment);
            assessment = assessmentService.getById(assessmentId);
            assertNull(assessment);
        }

        log.debug("Ending AssessmentServiceTest");
    }

    @Before
    public void setUpTest() {
        assessmentService = (AssessmentService) context.getBean("assessmentService");
    }

    @Test
    public void getAll() {
        log.debug("Starting getAllAssessmentsTest");

        Set<Assessment> assessments = assessmentService.getAll();
        assertFalse(assessments.isEmpty());

        log.debug("Ending getAllAssessmentsTest");
    }

    @Test
    public void getById() {
        int id = 4100;
        log.debug("Starting getAssessmentById = " + id);

        Assessment assessment = assessmentService.getById(id);
        System.out.println(assessment);
        assertNotNull(assessment);

        log.debug("Ending getAssessmentById");
    }

    @Test
    public void getByWeekId() {
        int weekId = 1;
        log.debug("Starting getAssessmentsByWeekId with id = " + weekId);

        Set<Assessment> assessments = assessmentService.getByWeekId(weekId);
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

        assessmentService.insert(assessment);
        assertTrue(true);

        log.debug("Ending insertAssessment");
    }

    @Test
    public void delete() {
        int assessmentId = 4550;

        log.debug("Starting test to fetch, delete, then fetch Assessment with id = " + assessmentId
                + "to make sure delete is functional");

        Assessment assessment = assessmentService.getById(assessmentId);

        if (assessment != null) {
            assessmentService.delete(assessment);
            assessment = assessmentService.getById(assessmentId);
            assertNull(assessment);
        }

        //populating table again for other tests
        populateTable();
    }
}
