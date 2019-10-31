package ca.mcgill.ecse321.tutor.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.LinkedMultiValueMap;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Rating;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.CourseService;
import ca.mcgill.ecse321.tutor.service.ManagerService;
import ca.mcgill.ecse321.tutor.service.NotificationService;
import ca.mcgill.ecse321.tutor.service.RatingService;
import ca.mcgill.ecse321.tutor.service.RoomService;
import ca.mcgill.ecse321.tutor.service.StudentService;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;
import ca.mcgill.ecse321.tutor.service.TutorService;
import ca.mcgill.ecse321.tutor.service.TutoringSessionService;

/**
 * Class implementing integration tests on all RESTFul services methods.
 * 
 * @author Michael Li
 *
 */
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RESTfulServicesTests extends AbstractTestNGSpringContextTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate; // Convenient alternative of RestTemplate that is suitable for integration
											// tests. They are fault tolerant, and optionally can carry Basic
											// authentication headers. 
	private HttpHeaders headers = new HttpHeaders(); // HTTP headers are the name or value pairs that are displayed in
														// the request and response messages.

	// Wiring all repository classes that modify data
	@Autowired
	BookingRepository bookingRepository; // Not needed because we query it from other teams
	@Autowired
	BookingService bookingService;

	@Autowired 
	CourseRepository courseRepository; // Not needed query from Manager
	@Autowired 
	CourseService courseService; // Not needed query from Manager

	@Autowired
	ManagerRepository managerRepository; // supposed to be queried from other teams
	@Autowired
	ManagerService managerService;

	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	NotificationService notificationService;

	
	@Autowired
	RatingRepository ratingRepository;
	@Autowired
	RatingService ratingService;

	@Autowired
	RoomRepository roomRepository; // Handled by manager
	@Autowired
	RoomService roomService; // Handled by manager

	@Autowired 
	StudentRepository studentRepository; // Query from student
	@Autowired 
	StudentService studentService; // Query from student

	@Autowired 
	TimeSlotRepository timeSlotRepository; // Query from manager
	@Autowired 
	TimeSlotService timeSlotService; // Query from manager

	@Autowired
	TutoringSessionRepository tutoringSessionRepository;
	@Autowired
	TutoringSessionService tutoringSessionService;
	
	@Autowired
	TutorRepository tutorRepository;
	@Autowired
	TutorService tutorService;

	// Test variables 
	TimeSlot timeSlot;
	Manager manager;
	Tutor tutor;
	TutoringSession tutoringSession;
	Booking booking;
	Notification notification;
	Course course;
	Rating rating;
	Room room; 
	Student student;
	
	@BeforeClass
	public void setup() {
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
		
		// Initialize complement objects in Database
		// Create manager
		manager = managerService.createManager();
		
		// Create tutor in database 
		String firstName = "John";
		String lastName = "Lennon";
		String tutorEmail = "duQuebec@poushon.com";
		String password = "123456";
		tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password, manager);

		// Create timeSlot 
		timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		
		// Create course 
		course = courseService.createCourse("Test", Level.CEGEP);
		String studentFirstName = "Michael";
		String studentLastName = "Li";
		String studentEmail = "mlej@live.com";
		
		// Create student
		student = studentService.createStudent(studentFirstName, studentLastName, studentEmail);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		
		// Create booking in database
		booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
	}
	

//	@AfterClass
//	public void tearDown() {
//		ratingRepository.deleteAll();
//		tutoringSessionRepository.deleteAll();
//		notificationRepository.deleteAll();
//		roomRepository.deleteAll();
//		bookingRepository.deleteAll();
//		tutorRepository.deleteAll();
//		managerRepository.deleteAll();
//		studentRepository.deleteAll();
//		courseRepository.deleteAll();
//		timeSlotRepository.deleteAll();
//	}

	/**
	 * Preparing to test all RESTful services for services that modify data. 
	 */
	
	/* <----------- Utility methods to create HTTP Requests to the database using RestTemplate ---------------> */
	
	@Test(priority=1, groups="Notification")
	public void testCreateNotification() {
		// Due to domain model design, a booking and a tutor has be created in the database in order to perform tests on notification.
		LinkedMultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
		params.add("tutorId", tutor.getId());
		params.add("bookingId", booking.getId());
		HttpEntity<LinkedMultiValueMap<String, Integer>> entity = new HttpEntity<LinkedMultiValueMap<String, Integer>>(params, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/notification/new"), HttpMethod.POST, entity, String.class);
		
		// Make sure response code is 200
		assertEquals(response.getStatusCodeValue(),200);
		
		// Make sure only one notification is present in the database
		assertEquals(notificationService.getAllNotifications().size(), 1);
		
		// Verify response is not null 
		assertNotNull(response);

	}
	
	@Test(priority=2, groups="Notification", dependsOnMethods="testCreateNotification")
	public void testGetNotificationByID() {
		int notificationId = notificationService.getAllNotifications().get(0).getId();
		LinkedMultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
		params.add("notificationId", notificationId);
		HttpEntity<LinkedMultiValueMap<String, Integer>> entity = new HttpEntity<LinkedMultiValueMap<String, Integer>>(params, headers);
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/notifications/" + notificationId), HttpMethod.GET, entity, String.class);
		String result = response.getBody().toString();
		
		// Verify response is not null 
		assertNotNull(response);
		
		// Assure successful connection 
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		
		// Verify URI 
		assertTrue(result.contains("/notifications/" + notificationId));
	}
	
	@Test(priority=3, groups="Notification", dependsOnMethods="testCreateNotification")
	public void testGetNotificationByTutor() {
		int notificationId = notificationService.getAllNotifications().get(0).getId();
		LinkedMultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
		params.add("tutorId", tutor.getId());
		HttpEntity<LinkedMultiValueMap<String, Integer>> entity = new HttpEntity<LinkedMultiValueMap<String, Integer>>(params, headers);
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/notifications/tutor" + notificationId), HttpMethod.GET, entity, String.class);
		String result = response.getBody().toString();
		
		// Verify response is not null 
		assertNotNull(response);
		
		// Assure successful connection 
		assertEquals(response.getStatusCode(), HttpStatus.OK);
		
		// Verify URI 
		assertTrue(result.contains("/notifications/tutor/" + notificationId));
		
		// Assert size of notifications
		
	}
	
//	@Test(priority=4, groups="TimeSlot")
//	public void testCreateTimeSlot() {
//		timeSlot = new TimeSlot();
//		
//	}
//	
//	@Test(priority=5, groups="Rating")
//	public void testCreateRating() {
//		rating = new Rating();
//	
//	}

	
//	@Test(priority=4, groups="TutoringSession")
//	public void createTutoringSession() {
//
//	}
//	
//	@Test(dependsOnMethods = "testCreateTutoringSession")
//	public void testGetTutoringSession() {
//		
//	}
	
//	@Test(priority=5, groups="Tutor")
//	public void createTutor() {		
//	
//	}
	
//	@Test(priority=5, groups="Tutor")
//	public void getTutor() {		
//	
//	}


	private String constructURLWithLocalPort(String URI) {
		return "http://localhost:" + port + URI;
	}

	
	
}
