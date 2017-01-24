package com.revature.caliber.training.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.training.beans.Category;
import com.revature.caliber.training.data.Facade;
import com.revature.caliber.training.service.CategoryService;
import org.springframework.stereotype.Component;

@Component
public class CategoryServiceImpl implements CategoryService {

	Facade facade;
    @Autowired
    public void setFacade(Facade facade) { this.facade = facade; }
    
	public Category getCategory(int categoryId) {return facade.getCategory(categoryId);}
	public List<Category> getAllCategories() {return facade.getAllCategories();}
}
