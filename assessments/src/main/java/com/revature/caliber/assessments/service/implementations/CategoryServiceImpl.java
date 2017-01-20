package com.revature.caliber.assessments.service.implementations;

import com.revature.caliber.assessments.beans.Category;
import com.revature.caliber.assessments.data.Facade;
import com.revature.caliber.assessments.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    Facade facade;

    @Autowired
    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Category> getAll() {
        return facade.getAllCategories();
    }

    @Override
    public Category getById(int id) {
        return facade.getCategoryById(id);
    }

}
