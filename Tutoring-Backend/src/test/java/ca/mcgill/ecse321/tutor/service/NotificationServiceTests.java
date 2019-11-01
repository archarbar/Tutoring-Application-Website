package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceTests {

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
	private BookingService bookingService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private TimeSlotService timeSlotService;

	@After
	@Before
	public void clearDatabase() {
		//we first clear bookings and tutoring sessions to avoid
		//exceptions due to inconsistencies
		bookingRepository.deleteAll();
		tutoringSessionRepository.deleteAll();
		tutorRepository.deleteAll();
		studentRepository.deleteAll();
		managerRepository.deleteAll();
		courseRepository.deleteAll();
		roomRepository.deleteAll();
		notificationRepository.deleteAll();
		ratingRepository.deleteAll();
		timeslotRepository.deleteAll();
	}

	/*
	 *  NOTIFICATION TESTS
	 */

	@Test
	public void testCreateNotification() { // test constructor method
		assertEquals(0, notificationService.getAllNotifications().size());

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
		String tutorFirstName = "Marcus";
		String tutorLastName = "Fenix";
		String password = "locust";
		//		Manager manager = managerService.createManager();
		Tutor tutor = tutorService.createTutor(tutorFirstName, tutorLastName, tutorEmail, password);

		try {
			notificationService.createNotification(booking, tutor);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Notification> allNotifications = notificationService.getAllNotifications();

		assertEquals(1, allNotifications.size());
		assertEquals(booking.getId(), allNotifications.get(0).getBooking().getId());
		assertEquals(tutor.getId(), allNotifications.get(0).getTutor().getId());
	}

	@Test
	public void testCreateNotificationNull() {
		assertEquals(0, notificationService.getAllNotifications().size());

		Booking booking = null;
		Tutor tutor = null;
		String error = null;

		try {
			notificationService.createNotification(booking, tutor);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A booking needs to be specified! A tutor needs to be specified!", error);

		// check no change in memory
		assertEquals(0, notificationService.getAllNotifications().size());
	}

	@Test
	public void testCreateNotificationNullBooking() {
		assertEquals(0, notificationService.getAllNotifications().size());

		Booking booking = null;
		String tutorFirstName = "Marcus";
		String tutorLastName = "Fenix";
		String tutorEmail = "marcusfenix@gears.com";
		String password = "locust";
		Tutor tutor = tutorService.createTutor(tutorFirstName, tutorLastName, tutorEmail, password);
		String error = null;

		try {
			notificationService.createNotification(booking, tutor);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A booking needs to be specified!", error);

		// check no change in memory
		assertEquals(0, notificationService.getAllNotifications().size());
	}

	@Test
	public void testCreateNotificationNullTutor() {
		assertEquals(0, notificationService.getAllNotifications().size());

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
		Tutor tutor = null;
		String error = null;

		try {
			notificationService.createNotification(booking, tutor);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A tutor needs to be specified!", error);

		// check no change in memory
		assertEquals(0, notificationService.getAllNotifications().size());
	}

}