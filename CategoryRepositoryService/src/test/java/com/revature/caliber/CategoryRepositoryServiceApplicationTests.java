package com.revature.caliber;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryServiceApplicationTests {
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockmvc;
	
	@Before
	public void setup(){
		mockmvc = MockMvcBuilders.webAppContextSetup(context).alwaysExpect(status().isOk()).build();
	}
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void returnActiveCategories() throws Exception {
		ResultActions ra = mockmvc.perform(get("http://localhost:8081/category/all"));
		//MockHttpServletResponse 
	}

}
