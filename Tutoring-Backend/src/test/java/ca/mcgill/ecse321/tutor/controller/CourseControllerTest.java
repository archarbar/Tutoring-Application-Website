package ca.mcgill.ecse321.tutor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import ca.mcgill.ecse321.tutor.dao.CourseRepository;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.service.CourseService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CourseControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private CourseRepository courseRepository;
	
	@Autowired
	private CourseService courseService;
	
	private MockMvc mockMvc;

	public void clearDatabase() {
		courseRepository.deleteAll();
	}

	@Before
	public void setup() throws Exception {
		clearDatabase();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}
	
	@After
	public void clear() throws Exception{
		clearDatabase();
	}
	
	@Test
	public void testGetCourse() throws Exception {
		courseService.createCourse("ECSE321", Level.UNIVERSITY);
		this.mockMvc.perform(get("/course")).andDo(print()).andExpect(status().isOk());
	}

}
