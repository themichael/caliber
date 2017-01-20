package com.revature.caliber.data.implementations;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.training.beans.Category;
import com.revature.caliber.training.data.CategoryDAO;
import com.revature.caliber.training.data.Facade;

public class CategoryDAOImplTest {

	private static ApplicationContext contxt;

    @BeforeClass
    public static void preClass () {
        contxt = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
    }
    
    //Testing the CategoryDAOImplementation
    @Test
    public void getCategory(){
    	Category category = contxt.getBean(CategoryDAO.class).getCategory(1);
    	System.out.println(category + "First Test");
    }
    
    @Test
    public void getAllCategories(){
    	List<Category> categories = new ArrayList<Category>();
    	categories = contxt.getBean(CategoryDAO.class).getAllCategories();
    	System.out.println(categories + "Second Test");
    }
    
    //Testing the category methods in the Facade
    @Test
    public void getCategoryFacade(){
    	System.out.println(contxt.getBean(Facade.class).getCategory(1) + "Third Test");
    }
    
    @Test
    public void getAllCategoryFacade(){
    	System.out.println(contxt.getBean(Facade.class).getAllCategories() + "Fourth Test");
    }
}
