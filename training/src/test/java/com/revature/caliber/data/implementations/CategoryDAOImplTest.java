package com.revature.caliber.data.implementations;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.training.beans.Category;
import com.revature.caliber.training.data.CategoryDAO;
import com.revature.caliber.training.data.Facade;

public class CategoryDAOImplTest {

	private static ApplicationContext contxt;
	private Logger logger;

    @BeforeClass
    public static void preClass () {
        contxt = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
    }
	
	@Before
	public void configureLogger(){
		this.logger = Logger.getRootLogger();
	}
	
    //Testing the CategoryDAOImplementation
    @Test
    public void getCategory(){
    	Category category = contxt.getBean(CategoryDAO.class).getCategory(1);
    	logger.info(category + "First Test");
    }
    
    @Test
    public void getAllCategories(){
    	List<Category> categories = new ArrayList<Category>();
    	categories = contxt.getBean(CategoryDAO.class).getAllCategories();
    	logger.info(categories + "Second Test");
    }
    
    //Testing the category methods in the Facade
    @Test
    public void getCategoryFacade(){
    	logger.info(contxt.getBean(Facade.class).getCategory(1) + "Third Test");
    }
    
    @Test
    public void getAllCategoryFacade(){
    	logger.info(contxt.getBean(Facade.class).getAllCategories() + "Fourth Test");
    }
}
