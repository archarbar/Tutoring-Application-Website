package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.*;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class TutoringSessionServiceTests {

	@Autowired
	private TutoringSessionService tutoringSessionService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private TutorService tutorService;
	@Autowired
	private RoomService roomService;
	@Autowired
	private TimeSlotService timeSlotService;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private StudentService studentService;

	@Autowired
	private TutoringSessionRepository tutoringSessionRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private TutorRepository tutorRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private RatingRepository ratingRepository;
	@Autowired
	private NotificationRepository notificationRepository;

	@After
	public void clearDatabase() {
		
		ratingRepository.deleteAll();
		notificationRepository.deleteAll();
		tutoringSessionRepository.deleteAll();
		bookingRepository.deleteAll();
		tutorRepository.deleteAll();
		roomRepository.deleteAll();
		timeSlotRepository.deleteAll();
		managerRepository.deleteAll();
		studentRepository.deleteAll();
	}

	@Test
	public void testCreateTutoringSession() {
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());

		Date sessionDate = Date.valueOf("2019-10-14");
		Manager manager = managerService.createManager();
		Room room = roomService.createRoom(12, 30, manager);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		String password = "locust";
		String tutorEmail = "arthurmorgan@redemption.com";
		Course course = courseService.createCourse("test", Level.CEGEP);
		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";
		Student student = studentService.createStudent(firstName, lastName, email);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		Tutor tutor = tutorService.createTutor(firstName, lastName, email, password);

		try {
			tutoringSessionService.createTutoringSession(sessionDate, tutor, room, booking, timeSlot);
		}
		catch (IllegalArgumentException e) {
			fail();
		}

		List<TutoringSession> allTutoringSessions = tutoringSessionService.getAllTutoringSessions();

		assertEquals(1, allTutoringSessions.size());
		assertEquals(sessionDate, allTutoringSessions.get(0).getSessionDate());
	}

	@Test
	public void testCreateTutoringSessionNull() {
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());

		Date sessionDate = null;
		Tutor tutor = null;
		Room room = null;
		Booking booking = null;
		TimeSlot timeSlot = null;
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(sessionDate, tutor, room, booking, timeSlot);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A date needs to be specified! A tutor needs to be specified! A room needs to be specified! A booking needs to be specified! A timeSlot needs to be specified!", error);

		// check no change in memory
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());
	}

	@Test
	public void testCreateTutoringSessionNullDate() {
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());

		Date sessionDate = null;
		Manager manager = managerService.createManager();
		Room room = roomService.createRoom(12, 30, manager);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		String password = "locust";
		String tutorEmail = "arthurmorgan@redemption.com";
		Course course = courseService.createCourse("test", Level.CEGEP);
		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";
		Student student = studentService.createStudent(firstName, lastName, email);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		Tutor tutor = tutorService.createTutor(firstName, lastName, email, password);
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(sessionDate, tutor, room, booking, timeSlot);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A date needs to be specified!", error);

		// check no change in memory
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());
	}

	@Test
	public void testCreateTutoringSessionNullTutor() {
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());

		Date sessionDate = Date.valueOf("2019-10-14");
		Manager manager = managerService.createManager();
		Room room = roomService.createRoom(12, 30, manager);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		String tutorEmail = "arthurmorgan@redemption.com";
		Course course = courseService.createCourse("test", Level.CEGEP);
		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";
		Student student = studentService.createStudent(firstName, lastName, email);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		Tutor tutor = null;
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(sessionDate, tutor, room, booking, timeSlot);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A tutor needs to be specified!", error);

		// check no change in memory
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());
	}

	@Test
	public void testCreateTutoringSessionNullRoom() {
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());

		Date sessionDate = Date.valueOf("2019-10-14");
		Room room = null;
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		String password = "locust";
		String tutorEmail = "arthurmorgan@redemption.com";
		Course course = courseService.createCourse("test", Level.CEGEP);
		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";
		Student student = studentService.createStudent(firstName, lastName, email);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		Tutor tutor = tutorService.createTutor(firstName, lastName, email, password);
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(sessionDate, tutor, room, booking, timeSlot);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A room needs to be specified!", error);

		// check no change in memory
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());
	}

	@Test
	public void testCreateTutoringSessionNullBooking() {
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());

		Date sessionDate = Date.valueOf("2019-10-14");
		Manager manager = managerService.createManager();
		Room room = roomService.createRoom(12, 30, manager);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		String password = "locust";
		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";
		Student student = studentService.createStudent(firstName, lastName, email);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		Booking booking = null;
		Tutor tutor = tutorService.createTutor(firstName, lastName, email, password);
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(sessionDate, tutor, room, booking, timeSlot);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A booking needs to be specified!", error);

		// check no change in memory
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());
	}

	@Test
	public void testCreateTutoringSessionNullTimeSlot() {
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());

		Date sessionDate = Date.valueOf("2019-10-14");
		Manager manager = managerService.createManager();
		Room room = roomService.createRoom(12, 30, manager);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		String password = "locust";
		String tutorEmail = "arthurmorgan@redemption.com";
		Course course = courseService.createCourse("test", Level.CEGEP);
		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";
		Student student = studentService.createStudent(firstName, lastName, email);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		Tutor tutor = tutorService.createTutor(firstName, lastName, email, password);
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(sessionDate, tutor, room, booking, null);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A timeSlot needs to be specified!", error);

		// check no change in memory
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());
	}

}