package ca.mcgill.ecse321.tutor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Time;

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

import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TimeSlotControllerTest {
	
	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private TimeSlotRepository timeSlotRepository;

	private MockMvc mockMvc;

	public void clearDatabase() {
		timeSlotRepository.deleteAll();
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
	public void testCreateTimeSlot() throws Exception {
		Time startTime = Time.valueOf("10:35:11");
		Time endTime = Time.valueOf("12:35:11");
		this.mockMvc.perform(post("/timeslot")
				.param("name", "ECSE321")
				.param("level", "UNIVERSITY")
				.param("dayoftheweek", "monday")
				)
		.andExpect(status().isOk());
	}

}
