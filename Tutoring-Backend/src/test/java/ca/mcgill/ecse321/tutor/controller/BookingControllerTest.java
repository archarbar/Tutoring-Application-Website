package ca.mcgill.ecse321.tutor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

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

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
import ca.mcgill.ecse321.tutor.dao.CourseRepository;
import ca.mcgill.ecse321.tutor.dao.StudentRepository;
import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.CourseService;
import ca.mcgill.ecse321.tutor.service.StudentService;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class BookingControllerTest {

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private StudentService studentService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TimeSlotService timeSlotService;
	@Autowired
	private BookingService bookingService;


	private MockMvc mockMvc;

	public void clearDatabase() {
		bookingRepository.deleteAll();
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
	public void clear() throws Exception{
		clearDatabase();
	}

	@Test
	public void testGetBooking() throws Exception {

		String tutorEmail = "arthurmorgan@redemption.com";
		Course course = courseService.createCourse("test", Level.CEGEP);
		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";
		Student student = studentService.createStudent(firstName, lastName, email);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);

		bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);

		this.mockMvc.perform(get("/booking")).andDo(print()).andExpect(status().isOk());
	}

}
