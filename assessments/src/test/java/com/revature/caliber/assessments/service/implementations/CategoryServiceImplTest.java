package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.Category;
import com.revature.caliber.assessments.service.CategoryService;
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
    private CategoryService categoryService;

    @BeforeClass
    public static void setUp() {

        context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
        // rootLogger is for debugging purposes
        log = Logger.getRootLogger();

        log.debug("Starting CategoryServiceTest");
    }

    @AfterClass
    public static void cleanUp() {
        log.debug("Ending CategoryServiceTest");
    }

    @Before
    public void setUpTest() {
        categoryService = (CategoryService) context.getBean("categoryService");
    }

    //    Waiting for additional data
    @Test
    public void getAll() throws Exception {
        log.debug("Starting getAllCategoriesTest");

        Set<Category> categories = categoryService.getAll();
        assertFalse(categories.isEmpty());

        log.debug("Ending getAllCategoriesTest");
    }

    @Test
    public void getById() throws Exception {
        int id = 1;
        log.debug("Starting getCategoryByIdTest = " + id);

        Category category = categoryService.getById(id);
        assertNotNull(category);

        log.debug("Ending getCategoryById");
    }
}