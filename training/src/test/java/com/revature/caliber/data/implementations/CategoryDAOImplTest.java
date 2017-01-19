package com.revature.caliber.data.implementations;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.revature.caliber.training.beans.Category;
import com.revature.caliber.training.data.CategoryDAO;

public class CategoryDAOImplTest {

	private static ApplicationContext contxt;

    @BeforeClass
    public static void preClass () {
        contxt = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/beans.xml");
    }
    
    @Test
    public void getCategory(){
    	Category category = contxt.getBean(CategoryDAO.class).getCategory(1);
    	System.out.println(category);
    }
    
    @Test
    public void getAllCategories(){
    	List<Category> categories = new ArrayList<Category>();
    	categories = contxt.getBean(CategoryDAO.class).getAllCategories();
    }
}
