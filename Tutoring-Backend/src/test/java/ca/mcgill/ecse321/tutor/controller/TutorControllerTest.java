package ca.mcgill.ecse321.tutor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.testng.Assert.assertEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jayway.jsonpath.JsonPath;

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
import ca.mcgill.ecse321.tutor.dao.CourseRepository;
import ca.mcgill.ecse321.tutor.dao.ManagerRepository;
import ca.mcgill.ecse321.tutor.dao.NotificationRepository;
import ca.mcgill.ecse321.tutor.dao.RatingRepository;
import ca.mcgill.ecse321.tutor.dao.RoomRepository;
import ca.mcgill.ecse321.tutor.dao.StudentRepository;
import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutor.dao.TutorRepository;
import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.ManagerService;
import ca.mcgill.ecse321.tutor.service.TutorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TutorControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private TutorRepository tutorRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	private TutoringSessionRepository tutoringSessionRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;

	@Autowired
	private TutorService tutorService;
	@Autowired
	private ManagerService managerService;

	private MockMvc mockMvc;

	public void clearDatabase() {
		// this should be enough because of the composition
		ratingRepository.deleteAll();
		tutoringSessionRepository.deleteAll();
		notificationRepository.deleteAll();
		roomRepository.deleteAll();
		bookingRepository.deleteAll();
		tutorRepository.deleteAll();
		managerRepository.deleteAll();
		studentRepository.deleteAll();
		courseRepository.deleteAll();
		timeSlotRepository.deleteAll();
	}

	@Before
	public void setup() throws Exception {
		clearDatabase();
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@After
	public void clear() throws Exception {
		clearDatabase();
	}

	@Test
	public void testCreateTutor() throws Exception {

		this.mockMvc
				.perform(post("/tutor").param("tutorFirstName", "first1").param("tutorLastName", "last2")
						.param("tutorEmail", "first.last@mail.mcgill.ca").param("tutorPassword", "123456"))
				.andExpect(status().isOk());
	}

	@Test
	public void testGetAllTutors() throws Exception {
//		tutorService.createTutor("first_name", "last_name", "first@last.com", "password");
		MvcResult response = this.mockMvc.perform(get("/tutors")).andExpect(status().isOk()).andReturn();

//		CODE TO GET NUMBER OF TUTORS. BUT NOT NECESSARY.

//		String content = response.getResponse().getContentAsString();
//		System.out.println("---------------------");
//		System.out.println(content);
//		JSONObject myObject = new JSONObject(content);
//		JSONObject tutors = myObject.getJSONObject("_embedded");
//		JSONArray tutor = tutors.getJSONArray("tutors");
//		System.out.println(tutor.length());

	}

	@Test
	public void testGetTutor() throws Exception {
		Tutor tutor = tutorService.createTutor("first_name", "last_name", "first@last.com", "password");
		int tutorId = tutor.getId();
		MvcResult response = this.mockMvc.perform(get("/tutor/" + Integer.toString(tutorId))).andExpect(status().isOk())
				.andReturn();

//		System.out.println(response);
	}

	@Test
	public void testGetTutorBadId() throws Exception {
		Tutor tutor = tutorService.createTutor("first_name", "last_name", "first@last.com", "password");
		int tutorId = tutor.getId();
		MvcResult response = null;
		try {
			response = this.mockMvc.perform(get("/tutor/" + Integer.toString(tutorId + 999999)))
					.andExpect(status().isOk()).andReturn();

//			System.out.println(response);
		} catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals(null, response);
	}

	@Test
	public void testGetTutorNoId() throws Exception {
		Tutor tutor = tutorService.createTutor("first_name", "last_name", "first@last.com", "password");
		int tutorId = tutor.getId();
		MvcResult response = null;
		response = this.mockMvc.perform(get("/tutor/")).andExpect(status().is4xxClientError()).andReturn();

//			System.out.println(response);
	}

	@Test
	public void testLogin() throws Exception {

		Tutor tutor = tutorService.createTutor("first_name", "last_name", "first@last.com", "password");
		int tutorId = tutor.getId();
		MvcResult response = this.mockMvc
				.perform(get("/login/").param("Email", tutor.getEmail()).param("Password", tutor.getPassword()))
				.andExpect(status().isOk()).andReturn();

//		System.out.println(response);
	}

	@Test
	public void testLoginBadPassword() throws Exception {

		Tutor tutor = tutorService.createTutor("first_name", "last_name", "first@last.com", "password");
		int tutorId = tutor.getId();
		MvcResult response = null;
		try {
			response = this.mockMvc.perform(get("/login/").param("Email", tutor.getEmail()).param("Password",
					tutor.getPassword() + "BAD PASSWORD"))
//					.andExpect(status().is4xxClientError())
					.andReturn();

			System.out.println(response);
		} catch (Exception e) {

		}
		assertEquals(null, response);

	}

	@Test
	public void testLoginBadEmail() throws Exception {

		Tutor tutor = tutorService.createTutor("first_name", "last_name", "first@last.com", "password");
		int tutorId = tutor.getId();
		MvcResult response = null;
		try {
			response = this.mockMvc.perform(get("/login/").param("Email", tutor.getEmail() + "BAD EMAIL")
					.param("Password", tutor.getPassword()))
//					.andExpect(status().is4xxClientError())
					.andReturn();

			System.out.println(response);
		} catch (Exception e) {

		}
		assertEquals(null, response);

	}

	@Test
	public void testLoginBadEmailAndPassword() throws Exception {

		Tutor tutor = tutorService.createTutor("first_name", "last_name", "first@last.com", "password");
		int tutorId = tutor.getId();
		MvcResult response = null;
		try {
			response = this.mockMvc.perform(get("/login/").param("Email", tutor.getEmail() + "BAD EMAIL")
					.param("Password", tutor.getPassword() + "BAD PASSWORD"))
//					.andExpect(status().is4xxClientError())
					.andReturn();

			System.out.println(response);
		} catch (Exception e) {

		}
		assertEquals(null, response);

	}

}
