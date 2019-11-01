package ca.mcgill.ecse321.tutor.controller;


import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import org.springframework.test.web.servlet.MvcResult;
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
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.CourseService;
import ca.mcgill.ecse321.tutor.service.ManagerService;
import ca.mcgill.ecse321.tutor.service.NotificationService;
import ca.mcgill.ecse321.tutor.service.RoomService;
import ca.mcgill.ecse321.tutor.service.StudentService;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;
import ca.mcgill.ecse321.tutor.service.TutorService;
import ca.mcgill.ecse321.tutor.service.TutoringSessionService;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class TutoringSessionControllerTest {
	
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
	private ManagerService managerService;
	@Autowired 
	private TutorService tutorService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private StudentService studentService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private TimeSlotService timeSlotService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private TutoringSessionService tutoringSessionService;
	

	private MockMvc mockMvc;

	public void clearDatabase() {
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
	public void clear() throws Exception{
		clearDatabase();
	}
	
	@Test
	public void testCreateTutoringSession() throws Exception {
		Manager manager = managerService.createManager();
//		int managerId = manager.getId();
		
		String firstName = "Marcus";
		String lastName = "Fenix";
		String tutorEmail = "marcusfenix@gears.com";
		String password = "locust";
		Tutor tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password);

		Course course = courseService.createCourse("test", Level.CEGEP);
		String studentFirstName = "Michael";
		String studentLastName = "Li";
		String studentEmail = "mlej@live.com";
		Student student = studentService.createStudent(studentFirstName, studentLastName, studentEmail);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		String bookingId = Integer.toString(booking.getId());
		Integer number = 12;
		Integer capacity = 30;
		Room room = roomService.createRoom(number, capacity , manager);
		
		String roomId = Integer.toString(room.getId());
 		this.mockMvc.perform(post("/tutoringSession/new")
				.param("bookingId", bookingId)
				.param("booking2", bookingId)
				.param("room", roomId)
				.param("tutor", Integer.toString(tutor.getId()))
				)
		.andExpect(status().isOk());
	}
	
	@Test
	public void testGetTutoringSession() throws Exception {
		Manager manager = managerService.createManager();
//		int managerId = manager.getId();
		
		String firstName = "Marcus";
		String lastName = "Fenix";
		String tutorEmail = "marcusfenix@gears.com";
		String password = "locust";
		Tutor tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password);

		Course course = courseService.createCourse("test", Level.CEGEP);
		String studentFirstName = "Michael";
		String studentLastName = "Li";
		String studentEmail = "mlej@live.com";
		Student student = studentService.createStudent(studentFirstName, studentLastName, studentEmail);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		String bookingId = Integer.toString(booking.getId());
		Integer number = 12;
		Integer capacity = 30;
		Room room = roomService.createRoom(number, capacity , manager);
		TutoringSession tutoringSession = tutoringSessionService.createTutoringSession(booking.getSpecificDate(), tutor, room, booking, timeSlot);
		MvcResult response = this.mockMvc.perform(get("/tutoringSession/" + Integer.toString(tutoringSession.getId())))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void testGetTutoringSessionBadId() throws Exception {
		Manager manager = managerService.createManager();
//		int managerId = manager.getId();
		
		String firstName = "Marcus";
		String lastName = "Fenix";
		String tutorEmail = "marcusfenix@gears.com";
		String password = "locust";
		Tutor tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password);

		Course course = courseService.createCourse("test", Level.CEGEP);
		String studentFirstName = "Michael";
		String studentLastName = "Li";
		String studentEmail = "mlej@live.com";
		Student student = studentService.createStudent(studentFirstName, studentLastName, studentEmail);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		String bookingId = Integer.toString(booking.getId());
		Integer number = 12;
		Integer capacity = 30;
		Room room = roomService.createRoom(number, capacity , manager);
		TutoringSession tutoringSession = tutoringSessionService.createTutoringSession(booking.getSpecificDate(), tutor, room, booking, timeSlot);
		
		MvcResult response = null;
		try {
			response = this.mockMvc.perform(get("/tutoringSession/" + Integer.toString(tutoringSession.getId() + 99999)))
					.andExpect(status().isOk())
					.andReturn();
		} catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals(null, response);
	}
	
	@Test
	public void testGetTutoringSessionNoId() throws Exception {
		Manager manager = managerService.createManager();
//		int managerId = manager.getId();
		
		String firstName = "Marcus";
		String lastName = "Fenix";
		String tutorEmail = "marcusfenix@gears.com";
		String password = "locust";
		Tutor tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password);

		Course course = courseService.createCourse("test", Level.CEGEP);
		String studentFirstName = "Michael";
		String studentLastName = "Li";
		String studentEmail = "mlej@live.com";
		Student student = studentService.createStudent(studentFirstName, studentLastName, studentEmail);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		String bookingId = Integer.toString(booking.getId());
		Integer number = 12;
		Integer capacity = 30;
		Room room = roomService.createRoom(number, capacity , manager);
		TutoringSession tutoringSession = tutoringSessionService.createTutoringSession(booking.getSpecificDate(), tutor, room, booking, timeSlot);
		
		MvcResult response = this.mockMvc.perform(get("/tutoringSession/"))
				.andExpect(status().is4xxClientError())
				.andReturn();
	}
	
	@Test
	public void testGetTutoringSessionByTutor() throws Exception {
		Manager manager = managerService.createManager();
//		int managerId = manager.getId();
		
		String firstName = "Marcus";
		String lastName = "Fenix";
		String tutorEmail = "marcusfenix@gears.com";
		String password = "locust";
		Tutor tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password);

		Course course = courseService.createCourse("test", Level.CEGEP);
		String studentFirstName = "Michael";
		String studentLastName = "Li";
		String studentEmail = "mlej@live.com";
		Student student = studentService.createStudent(studentFirstName, studentLastName, studentEmail);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		String bookingId = Integer.toString(booking.getId());
		Integer number = 12;
		Integer capacity = 30;
		Room room = roomService.createRoom(number, capacity , manager);
		TutoringSession tutoringSession = tutoringSessionService.createTutoringSession(booking.getSpecificDate(), tutor, room, booking, timeSlot);
		MvcResult response = this.mockMvc.perform(get("/tutoringSession/tutor/" + Integer.toString(tutoringSession.getTutor().getId())))
				.andExpect(status().isOk())
				.andReturn();
	}
	
	@Test
	public void testGetTutoringSessionByTutorBadId() throws Exception {
		Manager manager = managerService.createManager();
//		int managerId = manager.getId();
		
		String firstName = "Marcus";
		String lastName = "Fenix";
		String tutorEmail = "marcusfenix@gears.com";
		String password = "locust";
		Tutor tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password);

		Course course = courseService.createCourse("test", Level.CEGEP);
		String studentFirstName = "Michael";
		String studentLastName = "Li";
		String studentEmail = "mlej@live.com";
		Student student = studentService.createStudent(studentFirstName, studentLastName, studentEmail);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		String bookingId = Integer.toString(booking.getId());
		Integer number = 12;
		Integer capacity = 30;
		Room room = roomService.createRoom(number, capacity , manager);
		TutoringSession tutoringSession = tutoringSessionService.createTutoringSession(booking.getSpecificDate(), tutor, room, booking, timeSlot);
		
		MvcResult response = null;
		try {
			response = this.mockMvc.perform(get("/tutoringSession/tutor/" + Integer.toString(tutoringSession.getTutor().getId() + 99999)))
					.andExpect(status().isOk())
					.andReturn();
		} catch (Exception e) {
			// TODO: handle exception
		}
		assertEquals(null, response);
	}
	
	@Test
	public void testGetTutoringSessionByTutorNoId() throws Exception {
		Manager manager = managerService.createManager();
//		int managerId = manager.getId();
		
		String firstName = "Marcus";
		String lastName = "Fenix";
		String tutorEmail = "marcusfenix@gears.com";
		String password = "locust";
		Tutor tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password);

		Course course = courseService.createCourse("test", Level.CEGEP);
		String studentFirstName = "Michael";
		String studentLastName = "Li";
		String studentEmail = "mlej@live.com";
		Student student = studentService.createStudent(studentFirstName, studentLastName, studentEmail);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		String bookingId = Integer.toString(booking.getId());
		Integer number = 12;
		Integer capacity = 30;
		Room room = roomService.createRoom(number, capacity , manager);
		TutoringSession tutoringSession = tutoringSessionService.createTutoringSession(booking.getSpecificDate(), tutor, room, booking, timeSlot);
		
		MvcResult response = this.mockMvc.perform(get("/tutoringSession/tutor/"))
				.andExpect(status().is4xxClientError())
				.andReturn();
	}
	
	

}
