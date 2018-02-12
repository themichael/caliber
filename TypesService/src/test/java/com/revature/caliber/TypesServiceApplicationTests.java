package com.revature.caliber;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.caliber.model.AssessmentType;
import com.revature.caliber.model.InterviewFormat;
import com.revature.caliber.model.NoteType;
import com.revature.caliber.model.PanelStatus;
import com.revature.caliber.model.QCStatus;
import com.revature.caliber.model.SkillType;
import com.revature.caliber.model.TrainerRole;
import com.revature.caliber.model.TrainingStatus;
import com.revature.caliber.model.TrainingType;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypesServiceApplicationTests {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(context).alwaysExpect(status().isOk()).build();
	}
	
	@Test
	public void getAllSkillsTypesTest() throws Exception {
		ResultActions ra = mvc.perform(get("http://localhost:8081/types/skill/all"));
		MockHttpServletResponse response = ra.andReturn().getResponse();
		ObjectMapper mapper = new ObjectMapper();
		List<String> types = mapper.readValue(response.getContentAsByteArray(), List.class);
		List<String> realTypes = Stream.of(SkillType.values()).map(Enum::toString).collect(Collectors.toList());
		assertEquals(types, realTypes);
	}
	
	@Test
	public void getAllTrainingTypesTest() throws Exception {
		ResultActions ra = mvc.perform(get("http://localhost:8081/types/training/all"));
		MockHttpServletResponse response = ra.andReturn().getResponse();
		ObjectMapper mapper = new ObjectMapper();
		List<String> types = mapper.readValue(response.getContentAsByteArray(), List.class);
		List<String> realTypes = Stream.of(TrainingType.values()).map(Enum::name).collect(Collectors.toList());
		assertEquals(types, realTypes);
	}
	
	@Test
	public void getAllTrainingStatusTest() throws Exception {
		ResultActions ra = mvc.perform(get("http://localhost:8081/types/trainingstatus/all"));
		MockHttpServletResponse response = ra.andReturn().getResponse();
		ObjectMapper mapper = new ObjectMapper();
		List<String> types = mapper.readValue(response.getContentAsByteArray(), List.class);
		List<String> realTypes = Stream.of(TrainingStatus.values()).map(Enum::name).collect(Collectors.toList());
		assertEquals(types, realTypes);
	}
	
	@Test
	public void getAllNoteTypesTest() throws Exception {
		ResultActions ra = mvc.perform(get("http://localhost:8081/types/note/all"));
		MockHttpServletResponse response = ra.andReturn().getResponse();
		ObjectMapper mapper = new ObjectMapper();
		List<String> types = mapper.readValue(response.getContentAsByteArray(), List.class);
		List<String> realTypes = Stream.of(NoteType.values()).map(Enum::name).collect(Collectors.toList());
		assertEquals(types, realTypes);
	}
	
	@Test
	public void getAllQCStatusTypesTest() throws Exception {
		ResultActions ra = mvc.perform(get("http://localhost:8081/types/qcstatus/all"));
		MockHttpServletResponse response = ra.andReturn().getResponse();
		ObjectMapper mapper = new ObjectMapper();
		List<String> types = mapper.readValue(response.getContentAsByteArray(), List.class);
		List<String> realTypes = Stream.of(QCStatus.values()).map(Enum::name).collect(Collectors.toList());
		assertEquals(types, realTypes);
	}
	
	@Test
	public void getAllAssessmentTypesTest() throws Exception {
		ResultActions ra = mvc.perform(get("http://localhost:8081/types/assessment/all"));
		MockHttpServletResponse response = ra.andReturn().getResponse();
		ObjectMapper mapper = new ObjectMapper();
		List<String> types = mapper.readValue(response.getContentAsByteArray(), List.class);
		List<String> realTypes = Stream.of(AssessmentType.values()).map(Enum::name).collect(Collectors.toList());
		assertEquals(types, realTypes);
	}
	
	@Test
	public void getAllTrainerTierTest() throws Exception {
		ResultActions ra = mvc.perform(get("http://localhost:8081/types/trainer/role/all"));
		MockHttpServletResponse response = ra.andReturn().getResponse();
		ObjectMapper mapper = new ObjectMapper();
		List<String> types = mapper.readValue(response.getContentAsByteArray(), List.class);
		List<String> realTypes = Stream.of(TrainerRole.values()).map(Enum::name).collect(Collectors.toList());
		assertEquals(types, realTypes);
	}
	
	@Test
	public void getAllPanelStatusTest() throws Exception {
		ResultActions ra = mvc.perform(get("http://localhost:8081/types/panelstatus/all"));
		MockHttpServletResponse response = ra.andReturn().getResponse();
		ObjectMapper mapper = new ObjectMapper();
		List<String> types = mapper.readValue(response.getContentAsByteArray(), List.class);
		List<String> realTypes = Stream.of(PanelStatus.values()).map(Enum::name).collect(Collectors.toList());
		assertEquals(types, realTypes);
	}
	
	@Test
	public void getAllInterviewFormatTest() throws Exception {
		ResultActions ra = mvc.perform(get("http://localhost:8081/types/interviewformat/all"));
		MockHttpServletResponse response = ra.andReturn().getResponse();
		ObjectMapper mapper = new ObjectMapper();
		List<String> types = mapper.readValue(response.getContentAsByteArray(), List.class);
		List<String> realTypes = Stream.of(InterviewFormat.values()).map(Enum::name).collect(Collectors.toList());
		assertEquals(types, realTypes);
	}
	
	
	

}
