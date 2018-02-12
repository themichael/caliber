package com.revature.caliber.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.revature.caliber.model.SimpleCategory;



@Repository
public interface CategoryRepository extends JpaRepository<SimpleCategory, Integer> {


	List<SimpleCategory> findAllByOrderBySkillCategoryAsc();
	
	List<SimpleCategory> findByActiveOrderByCategoryIdAsc(boolean active);
	
	SimpleCategory findOneBySkillCategory(String skillCategory);
	
}
