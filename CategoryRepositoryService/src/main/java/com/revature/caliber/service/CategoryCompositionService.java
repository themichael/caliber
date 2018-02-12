package com.revature.caliber.service;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.model.Category;
import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.repository.CategoryRepository;


public class CategoryCompositionService {
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private CategoryCompositionMessagingService categoryCompositionMessagingService;
	private static final Logger log = Logger.getLogger(CategoryCompositionService.class);
	
	/**. 
	 * Finds one simplified bean from the service database and
	 * composes it into a complex bean required by the front end.
	 *
	 * @param int the id of a Category 
	 * @return A Category object
	 */
	public Category findOne(int categoryId) {
		log.info("Finding one simple category");
		SimpleCategory basis = categoryRepository.findOne(categoryId);
		Category result = composeCategory(basis);
		return result;
	}
	
	/**. 
	 * Finds all simplified beans from the service database and
	 * composes them into complex beans required by the front end.
	 *
	 * @return A List of Category objects ordered by skill category Ascending
	 */
	public List<Category> findAll() {
		List<SimpleCategory> basis = categoryRepository.findAllByOrderBySkillCategoryAsc();
		List<Category> result = composeListOfCategory(basis);
		
		return result;
	}
	
	/**. 
	 * Finds all simplified beans from the service database and
	 * composes them into complex beans required by the front end.
	 *
	 * @return A List of Category objects ordered by their id Ascending
	 */
	public List<Category> findAllActive() {
		List<SimpleCategory> basis = categoryRepository.findByActiveOrderByCategoryIdAsc(true);
		List<Category> result = composeListOfCategory(basis);
		
		return result;
	}

	/**. 
	 * Given a Category create a simple version
	 * and saves it
	 *
	 * @param A Category object to save
	 */
	public void save(Category category) {
		SimpleCategory toSave = new SimpleCategory(category);
		categoryRepository.save(toSave);
	}
	
	/**. 
	 * Given a Category create a simple version
	 * and use it to update
	 *
	 * @param A Category object to update
	 */
	public void update(Category category) {
		SimpleCategory toSave = new SimpleCategory(category);
		categoryRepository.save(toSave);
	}
	
	/**. 
	 * Delete a Category
	 *
	 * @param int the id of a Category
	 */
	public void delete(int categoryId) {
		categoryRepository.delete(categoryId);
	}
	
	private List<Category> composeListOfCategory(List<SimpleCategory> src) {
		List<Category> dest = new LinkedList<Category>();
		
		for(SimpleCategory curr : src) {
			dest.add(new Category(curr));
		}
		
		return dest;
	}
	
	private Category composeCategory(SimpleCategory src) {
		Category dest = new Category(src);
		return dest;
	}
}
