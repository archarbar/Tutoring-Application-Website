package ca.mcgill.ecse321.tutor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BookingControllerTest {

	private MockMvc mockMvc;

	@Test
	public void testCreateBooking() throws Exception {
		this.mockMvc.perform(get("/booking"));
	}

}
