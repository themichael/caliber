package com.revature.caliber.test.unit;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.CaliberTest;
import com.revature.caliber.beans.TrainerTask;
import com.revature.caliber.data.TaskDAO;

public class TaskDAOTest extends CaliberTest{
	private static final Logger log = Logger.getLogger(TaskDAO.class);

	@Autowired
	private TaskDAO dao;
	
	private static final String TASK_COUNT = "select count(task_id) from caliber_task";							//Finds all categories, active and inactive.

	@Test
	public void save() {
		log.info("Testing save method from TaskDAO");
		TrainerTask newTask = new TrainerTask("Gets Revature logo inner lip tattoo",2);
		Integer before = jdbcTemplate.queryForObject(TASK_COUNT, Integer.class);
		dao.saveOrUpdateTask(newTask);
		Integer after = jdbcTemplate.queryForObject(TASK_COUNT, Integer.class);
		assertEquals(++before, after);
	}

}
	

