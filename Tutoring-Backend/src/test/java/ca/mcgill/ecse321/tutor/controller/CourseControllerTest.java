package ca.mcgill.ecse321.tutor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CourseControllerTest {
	
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private CourseRepository courseRepository;

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
	public void testCreateCourse() throws Exception {
		this.mockMvc.perform(post("/course")
				.param("name", "ECSE321")
				.param("level", "UNIVERSITY")
				)
		.andExpect(status().isOk());
	}

}
