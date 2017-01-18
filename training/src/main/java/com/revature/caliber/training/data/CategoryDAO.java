package com.revature.caliber.training.data;

import java.util.List;

import com.revature.caliber.training.beans.Category;

public interface CategoryDAO {

	Category getCategory(String category);
	List<Category> getAllCategories();
	
}
