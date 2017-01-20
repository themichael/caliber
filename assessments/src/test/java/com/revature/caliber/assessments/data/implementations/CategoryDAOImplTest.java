package com.revature.caliber.assessments.data.implementations;

import com.revature.caliber.assessments.beans.Category;
import com.revature.caliber.assessments.data.CategoryDAO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class CategoryDAOImplTest {

    private static AbstractApplicationContext context;
    private static Logger log;
    private CategoryDAO categoryDAO;

    @BeforeClass
    public static void setUp() {

        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        // rootLogger is for debugging purposes
        log = Logger.getRootLogger();
    }

    @Before
    public void setUpTest() {
        categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
    }

    @Test
    public void getAll() throws Exception {
        log.debug("Starting getAllAssessmentsTest");
        Set<Category> categories = categoryDAO.getAll();
        assertFalse(categories.isEmpty());
    }

    @Test
    public void getById() throws Exception {
        int id = 1;
        log.debug("Starting getCategoryByIdTest = " + id);
        Category category = categoryDAO.getById(id);
        assertNotNull(category);
    }

}