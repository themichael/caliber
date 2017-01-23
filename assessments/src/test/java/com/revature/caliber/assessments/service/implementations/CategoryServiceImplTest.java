package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.Category;
import com.revature.caliber.assessments.data.Facade;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class CategoryServiceImplTest {

    private static AbstractApplicationContext context;
    private static Logger log;
    private Facade facade;

    @BeforeClass
    public static void setUp() {

        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        // rootLogger is for debugging purposes
        log = Logger.getRootLogger();

        log.debug("Starting CategoryDAOImplTest");
    }

    @AfterClass
    public static void cleanUp() {
        log.debug("Ending AssessmentDAOImplTest");
    }

    @Before
    public void setUpTest() {
        facade = (Facade) context.getBean("assessmentFacadeImplementation");
    }

    //    Waiting for additional data
    @Test
    public void getAll() throws Exception {
        log.debug("Starting getAllCategoriesTest");

        Set<Category> categories = facade.getAllCategories();
        assertFalse(categories.isEmpty());

        log.debug("Ending getAllCategoriesTest");
    }

    @Test
    public void getById() throws Exception {
        int id = 1;
        log.debug("Starting getCategoryByIdTest = " + id);

        Category category = facade.getCategoryById(id);
        assertNotNull(category);

        log.debug("Ending getCategoryById");
    }
}