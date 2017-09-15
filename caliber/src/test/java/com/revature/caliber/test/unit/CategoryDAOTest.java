package com.revature.caliber.test.unit;

import static org.junit.Assert.*;

import java.util.List;


import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.Category;
import com.revature.caliber.data.CategoryDAO;
import com.revature.caliber.test.integration.AssessmentTest;

public class CategoryDAOTest extends CaliberTest {
	private static final Logger log = Logger.getLogger(AssessmentTest.class);

	@Autowired
	private CategoryDAO dao;

	private static final String CATEGORY_COUNT = "select count(category_id) from caliber_category";
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.CategoryDAO.findAll()
	 */
	@Test
	public void findAll() {
		List<Category> myValues = dao.findAll();
		assertTrue("Test categories exist", myValues.size() > 0);
		//For all members of the myValues List except the last one, ensure the id is less than the id that follows it.
		for(int i = 0 ; i < (myValues.size() - 1); i++)
		{
			assertTrue("Test ordering by id, on id: " + i, (myValues.get(i).getCategoryId() < myValues.get(i + 1).getCategoryId()));
		}
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.CategoryDAO.findAllCategories()
	 * distinct from findAll in that it orders by Skill name rather than category ID.
	 */
	@Test
	public void findAllCategories() {
		List<Category> myValues = dao.findAllCategories();
		assertNotNull("Test that something exists", myValues);
		assertTrue("Test categories exist", myValues.size() > 0);
		//For all members of the myValues List except the last one, ensure the skill name is ordered correctly..
				for(int i = 0 ; i < (myValues.size() - 1); i++)
				{
					String categoryOne = myValues.get(i).getSkillCategory();
					String categoryTwo = myValues.get(i+1).getSkillCategory();
					assertTrue("Test ordering by skill name, on name: " + categoryOne + " and " + categoryTwo, (categoryOne.compareTo(categoryTwo) <= 0));
				}
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.CategoryDAO.fineOne()
	 */
	@Test
	public void findOne() {
		Category myCat = dao.findOne(1);
		assertNotNull(myCat);
		assertTrue("Test that findOne returns a Category",  (dao.findOne(1) instanceof Category));
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.CategoryDAO.update()
	 */
	@Test
	public void update() {
		log.info("UPDATE CATEGORY");
		String skillName = "Totally new name that no one has used before";
		Category myCat = dao.findAll().get(0);
		assertNotEquals(skillName, myCat.getSkillCategory());
		myCat.setSkillCategory(skillName);
		dao.update(myCat);
		assertEquals(skillName, myCat.getSkillCategory());
	}
	/**
	 * Tests methods:
	 * @see com.revature.caliber.data.CategoryDAO.save()
	 */
	@Test
	public void save() {
		// positive testing only, no constraints to check
		log.info("CREATE CATEGORY");
		Category newCat = new Category("Underwater Basket Weaving", false);
		Long before = jdbcTemplate.queryForObject(CATEGORY_COUNT, Long.class);
		dao.save(newCat);
		Long after = jdbcTemplate.queryForObject(CATEGORY_COUNT, Long.class);
		assertEquals(++before, after);
	}
}
