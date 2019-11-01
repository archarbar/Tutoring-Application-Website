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
	TimeSlot timeSlot2;
	Manager manager;
	Tutor tutor;
	TutoringSession tutoringSession;
	Booking booking;
	Booking booking2;
	Notification notification;
	Course course;
	Rating rating;
	Room room; 
	Student student;
	
	private final static String startTime = "15:05:00";
	private final static String endTime = "17:05:00";
	private final static String dayOfTheWeek = "FRIDAY";
	
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
		tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password);
		
		// Create room
		int number = 30;
		int capacity = 15;
		room = roomService.createRoom(number, capacity , manager);

		// Create timeSlot 
		timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		timeSlot2 = timeSlotService.createTimeSlot(Time.valueOf("13:12:12"), Time.valueOf("14:12:12"), DayOfTheWeek.FRIDAY);
		
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
		booking2= bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-11"), timeSlot2, course);
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
		
		// Verify response is not null 
		assertNotNull(response);
		
		// Assure successful connection 
		assertEquals(response.getStatusCode(), HttpStatus.OK);
			}
	
//	@Test(priority=3, groups="Notification", dependsOnMethods="testCreateNotification")
//	public void testGetNotificationByTutor() {
//		int tutorId = tutor.getId();
//		LinkedMultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
//		params.add("tutorId", tutorId);
//		HttpEntity<LinkedMultiValueMap<String, Integer>> entity = new HttpEntity<LinkedMultiValueMap<String, Integer>>(params, headers);
//		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/notifications/tutor/" + tutorId), HttpMethod.GET, entity, String.class);
//
//		// Verify response is not null 
//		assertNotNull(response);
//		
//		// Assure successful connection 
//		assertEquals(response.getStatusCode(), HttpStatus.OK);	
//		
//	}
	
	@Test(priority=4, groups="TimeSlot")
	public void testCreateTimeSlot() {		
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("startTime", startTime);
		params.add("endTime", endTime);
		params.add("dayOfTheWeek", dayOfTheWeek);
		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(params, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/timeslot/new"), HttpMethod.POST, entity, String.class);
		
		// Make sure response code is 200
		assertEquals(response.getStatusCodeValue(),200);
				
		// Verify response is not null 
		assertNotNull(response);

	}
	
	@Test(priority=5, groups="TimeSlot")
	public void testGetTimeSlotById() {		
		int timeSlotId = timeSlotService.getAllTimeSlots().get(0).getId();
		// Due to domain model design, a booking and a tutor has be created in the database in order to perform tests on notification.
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("timeSlotId", timeSlotId);
		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(params, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/timeslot/"+timeSlotId), HttpMethod.GET, entity, String.class);
						
		// Make sure response code is 200
		assertEquals(response.getStatusCodeValue(),200);

		// Verify response is not null 
		assertNotNull(response);

	}
	
	@Test(priority = 6, groups = "Tutor")
	public void testCreateTutor() {
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("tutorFirstName", "Gang");
		params.add("tutorLastName", "Gucci");
		params.add("tutorEmail", "gucci@gang.com");
		params.add("tutorPassword", "gucciii");
		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
				params, headers); 
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/tutor/new"),
				HttpMethod.POST, entity, String.class);

		// Make sure response code is 200
		assertEquals(response.getStatusCodeValue(), 200);

		// Verify response is not null
		assertNotNull(response);
	}

	@Test(priority=7, groups="Tutor")
	public void testGetTutorById() {	
		int tutorId = tutorService.getAllTutors().get(0).getId();
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("tutorId", tutorId);
		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(params, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/tutor/"+tutorId), HttpMethod.GET, entity, String.class);
			
		// Verify response is not null 
		assertNotNull(response);
		
		// Assure successful connection 
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	
	@Test(priority=8, groups="TutoringSession")
	public void testCreateTutoringSession() {
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("bookingId", booking.getId());
		params.add("roomId", room.getId());
		params.add("tutorId", tutorService.getAllTutors().get(0).getId());
		System.out.println(booking.getId() + room.getId() + tutorService.getAllTutors().get(0).getId());;
				HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
				params, headers); 
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/tutoringsession/new"),
				HttpMethod.POST, entity, String.class);

		// Make sure response code is 200
		assertEquals(response.getStatusCodeValue(), 200);

		// Verify response is not null
		assertNotNull(response);
	}
	
	@Test(priority=9, dependsOnMethods = "testCreateTutoringSession")
	public void testGetTutoringSessionById() {
		int tutoringSessionId = tutoringSessionService.getAllTutoringSessions().get(0).getId();
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("tutoringSessionId", tutoringSessionId);
		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(params, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/tutoringsessions/"+tutoringSessionId), HttpMethod.GET, entity, String.class);
			
		// Verify response is not null 
		assertNotNull(response);
		
		// Assure successful connection 
		assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
//	@Test(priority=10, groups="Rating")
//	public void testCreateRating() {
//		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
//		String stars = "5";
//		String comment = "He was really attentive to my instructions";
//		int studentId = student.getId();
//		int tutoringSessionId = tutoringSessionService.getAllTutoringSessions().get(0).getId();
//		params.add("stars", stars);
//		params.add("comment", comment);
//		params.add("studentId", studentId);
//		params.add("tutoringSessionId", tutoringSessionId);
//	HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
//			params, headers); 
//	ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/rating/new"),
//			HttpMethod.POST, entity, String.class);
//
//	// Make sure response code is 200
//	assertEquals(response.getStatusCodeValue(), 200);
//
//	// Verify response is not null
//	assertNotNull(response);
//	
//	}
//	
//	@Test(priority=11, groups="Rating", dependsOnMethods="testCreateRating")
//	public void testGetRating() {
//		int ratingId = ratingService.getAllRatings().get(0).getId();
//		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
//		params.add("ratingId", ratingId);
//	HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(
//			params, headers); 
//	ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/rating/" + ratingId),
//			HttpMethod.GET, entity, String.class);
//
//	// Make sure response code is 200
//	assertEquals(response.getStatusCodeValue(), 200);
//
//	// Verify response is not null
//	assertNotNull(response);
//	
//	}
	
	/* <------ Queries ------> */
//	@Test(priority=11, groups="Queries")
//	public void testGetBookingById() {
//		String bookingId = booking2.getId().toString();
//		// Due to domain model design, a booking and a tutor has be created in the database in order to perform tests on notification.
//		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
//		params.add("bookingId", bookingId);
//		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(params, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
//		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/bookings/"+bookingId), HttpMethod.GET, entity, String.class);
//						
//		// Make sure response code is 200
//		assertEquals(response.getStatusCodeValue(),200);
//
//		// Verify response is not null 
//		assertNotNull(response);
//	}
	
	@Test(priority=11, groups="Queries")
	public void testGetCourse() {
		int courseId = course.getId();
		// Due to domain model design, a booking and a tutor has be created in the database in order to perform tests on notification.
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("courseId", courseId);
		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(params, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/course/"+courseId), HttpMethod.GET, entity, String.class);
						
		// Make sure response code is 200
		assertEquals(response.getStatusCodeValue(),200);

		// Verify response is not null 
		assertNotNull(response);
		
	}

	@Test(priority=11, groups="Queries")
	public void testGetManager() {
		int managerId = manager.getId();
		// Due to domain model design, a booking and a tutor has be created in the database in order to perform tests on notification.
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("managerId", managerId);
		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(params, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/manager/"+managerId), HttpMethod.GET, entity, String.class);
						
		// Make sure response code is 200
		assertEquals(response.getStatusCodeValue(),200);

		// Verify response is not null 
		assertNotNull(response);
	}

	@Test(priority=11, groups="Queries")
	public void testGetRoom() {
		int roomId = room.getId();
		// Due to domain model design, a booking and a tutor has be created in the database in order to perform tests on notification.
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("roomId", roomId);
		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(params, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/room/"+roomId), HttpMethod.GET, entity, String.class);
						
		// Make sure response code is 200
		assertEquals(response.getStatusCodeValue(),200);

		// Verify response is not null 
		assertNotNull(response);
	}

	@Test(priority=11, groups="Queries")
	public void testGetStudent() {
		int studentId = student.getId();
		// Due to domain model design, a booking and a tutor has be created in the database in order to perform tests on notification.
		LinkedMultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.add("studentId", studentId);
		HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<LinkedMultiValueMap<String, Object>>(params, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/student/"+studentId), HttpMethod.GET, entity, String.class);
						
		// Make sure response code is 200
		assertEquals(response.getStatusCodeValue(),200);

		// Verify response is not null 
		assertNotNull(response);
	}
	
	/* <-------- Use Case Tests ------> */
	// Use case 1: The system shall allow a potential tutor to submit their job application by submitting his first name, last name, highest level of education, phone number, email and resume.
	// Use case 2: The system shall allow a verified tutor to create an account with the approved courses from the application by setting his password, availabilities, and hourly rate.
	// Use case 3: The system shall allow a tutor with an account to modify his availabilities. 
	// Use case 4: The system shall allow a tutor with an account to modify his course offerings (adding courses requires manager approval). 
	// Use case 5: The system shall notify a tutor when he receives a booking.
	/**
	 * Testing implementation of fifth most important use caes 
	 */
	@Test(priority=12, groups="Use Cases")
	public void testNotifyTutor() {
		int numberOfTutorNotifications = notificationService.getNotificationsByTutor(tutor).size();
		LinkedMultiValueMap<String, Integer> params = new LinkedMultiValueMap<>();
		params.add("tutorId", tutor.getId());
		params.add("bookingId", booking2.getId());
		HttpEntity<LinkedMultiValueMap<String, Integer>> entity = new HttpEntity<LinkedMultiValueMap<String, Integer>>(params, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
		ResponseEntity<String> response = restTemplate.exchange(constructURLWithLocalPort("/bookings/new"), HttpMethod.POST, entity, String.class);
		
		// Make sure response code is 200
		assertEquals(response.getStatusCodeValue(),200);

		// Verify response is not null 
		assertNotNull(response);
		
		// Verify tutor has an additional notification
		assertEquals(notificationService.getNotificationsByTutor(tutor).size() - numberOfTutorNotifications, 1);
	}


	private String constructURLWithLocalPort(String URI) {
		return "http://localhost:" + port + URI;
	}

	
	
}
