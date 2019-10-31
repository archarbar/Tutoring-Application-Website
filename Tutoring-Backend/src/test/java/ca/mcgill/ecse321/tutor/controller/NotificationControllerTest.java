package ca.mcgill.ecse321.tutor.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import ca.mcgill.ecse321.tutor.dao.ManagerRepository;
import ca.mcgill.ecse321.tutor.dao.NotificationRepository;
import ca.mcgill.ecse321.tutor.dao.RatingRepository;
import ca.mcgill.ecse321.tutor.dao.RoomRepository;
import ca.mcgill.ecse321.tutor.dao.StudentRepository;
import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutor.dao.TutorRepository;
import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.CourseService;
import ca.mcgill.ecse321.tutor.service.ManagerService;
import ca.mcgill.ecse321.tutor.service.StudentService;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;
import ca.mcgill.ecse321.tutor.service.TutorService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class NotificationControllerTest {

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
	private TimeSlotRepository timeslotRepository;

	@Autowired
	private TutorService tutorService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TimeSlotService timeSlotService;

	private MockMvc mockMvc;

	public void clearDatabase() {
		notificationRepository.deleteAll();
		bookingRepository.deleteAll();
		tutoringSessionRepository.deleteAll();
		tutorRepository.deleteAll();
		studentRepository.deleteAll();
		managerRepository.deleteAll();
		courseRepository.deleteAll();
		roomRepository.deleteAll();
		ratingRepository.deleteAll();
		timeslotRepository.deleteAll();
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
		
		String tutorEmail = "marcusfenix@gears.com";
		Course course = courseService.createCourse("test", Level.CEGEP);
		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";
		Student student = studentService.createStudent(firstName, lastName, email);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		int bookingId = booking.getId();
		
		String tutorFirstName = "Marcus";
		String tutorLastName = "Fenix";
		String password = "locust";
//		Manager manager = managerService.createManager();
		Tutor tutor = tutorService.createTutor(tutorFirstName, tutorLastName, tutorEmail, password);
		int tutorId = tutor.getId();
		
		this.mockMvc.perform(post("/notification")
				.param("booking", Integer.toString(bookingId))
				.param("tutor", Integer.toString(tutorId))
				)
		.andExpect(status().isOk());
	}

}
