package ca.mcgill.ecse321.tutor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

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
	private BookingRepository bookingRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private StudentService studentService;
	@Autowired
	private TimeSlotService timeSlotService;
	@Autowired
	private CourseService courseService;

	private MockMvc mockMvc;

	public void clearDatabase() {
		bookingRepository.deleteAll();
		studentRepository.deleteAll();
		timeSlotRepository.deleteAll();
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
	public void testCreateBooking() throws Exception {
		Student student = studentService.createStudent("Victor", "Zhong", "archarbar@gmail.com");
		int studentId = student.getId();
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:35:11"), Time.valueOf("12:35:11"), DayOfTheWeek.MONDAY);
		int timeSlotId = timeSlot.getId();
		Course course = courseService.createCourse("English", Level.UNIVERSITY);
		int courseId = course.getId();
		Date date = Date.valueOf("2019-10-10");
		String dateString = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
		this.mockMvc.perform(post("/booking")
				.param("tutorEmail", "first.last@mail.mcgill.ca")
				.param("students", Integer.toString(studentId))
				.param("specificDate", dateString)
				.param("timeSlot", Integer.toString(timeSlotId))
				.param("course", Integer.toString(courseId))
				)
		.andExpect(status().isOk());
	}

}
