package com.revature.caliber;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.revature.caliber.model.Trainer;
import com.revature.caliber.service.TrainerCompositionService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrainerEndpointsTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private TrainerCompositionService trainerCompositionService;
	
	private MockMvc mvc;
	
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void createTrainerTest() throws Exception {
		JsonObject req = new JsonObject();
		req.addProperty("trainerId", "");
		req.addProperty("name", "test");
		req.addProperty("tier", "ROLE_TRAINER");
		req.addProperty("title", "Senior Trainer");
		req.addProperty("email", "test@test.com");
		ResultActions ra = mvc.perform(post("http://localhost:8081/trainer/vp/trainer/create").contentType(MediaType.APPLICATION_JSON_VALUE).content(req.toString())).andExpect(status().isCreated());
		Collection<String> headers = ra.andReturn().getResponse().getHeaderNames();
	} 
	
	@Test
	public void findTrainerTest() throws Exception {
		Trainer realTrainer  = trainerCompositionService.findByEmail("test@test.com");
		ResultActions ra = mvc.perform(get("http://localhost:8081/trainer/training/trainer/byemail/test@test.com")).andExpect(status().isOk());
		byte[] response = ra.andReturn().getResponse().getContentAsByteArray();
		ObjectMapper mapper = new ObjectMapper();
		Trainer trainer = mapper.readValue(response, Trainer.class);
		assertEquals(trainer, realTrainer);
	}
	
	@Test
	public void allTrainersTest() throws Exception {
		ResultActions ra = mvc.perform(get("http://localhost:8081/trainer/all/trainers/all")).andExpect(status().isOk());
		byte[] response = ra.andReturn().getResponse().getContentAsByteArray();
		ObjectMapper mapper = new ObjectMapper();
		List<Trainer> trainers = mapper.readValue(response, List.class);
		List<Trainer> allTrainers = trainerCompositionService.findAll();
		assertEquals(trainers, allTrainers);
	}
	
	@Test
	public void deleteTrainerTest() throws Exception {
		mvc.perform(delete("http://localhost:8081/trainer/all/trainers/all")).andExpect(status().isNoContent());
	}
	
	@Test
	public void allTrainerTypesTest() throws Exception {
		ResultActions ra = mvc.perform(get("http://localhost:8081/trainer/vp/trainer/titles")).andExpect(status().isOk());
		byte[] response = ra.andReturn().getResponse().getContentAsByteArray();
		ObjectMapper mapper = new ObjectMapper();
		List<String> types = mapper.readValue(response, List.class);
		List<String> actualTypes = trainerCompositionService.trainerRepository.findAllTrainerTitles();
	}

}
