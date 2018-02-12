package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.model.SimpleCategory;
import com.revature.caliber.repository.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
    private TestEntityManager entityManager;
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	private static final Logger log = Logger.getLogger(CategoryRepositoryTest.class);
	
	private static final String CATEGORY_COUNT = "select count(category_id) from caliber_category";
	private static final String ACTIVE_CATEGORY = "select count(category_id) from caliber_category WHERE IS_ACTIVE = 1;";
	
	@Test
	public void test() {
		assertTrue(true);
	}
	
	@Test
	public void findOne() {
		log.info("Testing findOne method from CategoryDAO");
		SimpleCategory myCat = categoryRepository.findOne(1);
		assertNotNull(myCat);
		assertTrue(categoryRepository.findOne(1) instanceof SimpleCategory);
		assertEquals(categoryRepository.findOne(1).toString(), "Java");
	}
	
	@Test
	public void testAll() {
		int expected = categoryRepository.findAll().size();
		int actual = jdbcTemplate.queryForObject(CATEGORY_COUNT, Integer.class);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void testAllActive() {
		log.info("Testing findAllActive from CategoryDAO");
		int expected = categoryRepository.findByActiveOrderByCategoryIdAsc(true).size();
		int actual = jdbcTemplate.queryForObject(ACTIVE_CATEGORY,Integer.class);
		assertEquals(expected, actual);
	}
	
	@Test
	public void update() {
		log.info("Testing update from CategoryDAO");
		String skillName = "Revature Pro";
		SimpleCategory myCat = categoryRepository.findOne(1);
		myCat.setSkillCategory(skillName);
		categoryRepository.save(myCat);
		assertEquals(skillName, myCat.getSkillCategory());
	}
	
	@Test
	public void save() {
		log.info("Testing save method from CategoryDAO");
		SimpleCategory newCat = new SimpleCategory("Underwater Basket Weaving", false);
		Long before = jdbcTemplate.queryForObject(CATEGORY_COUNT, Long.class);
		categoryRepository.saveAndFlush(newCat);
		Long after = jdbcTemplate.queryForObject(CATEGORY_COUNT, Long.class);
		assertEquals(++before, after);
	}
	
	@Test
	public void delete() {
		log.info("Testing delete method from CategoryDAO");
		categoryRepository.delete(categoryRepository.findOne(1));
	}
}
