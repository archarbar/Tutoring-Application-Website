package ca.mcgill.ecse321.tutor.integration;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.service.ManagerService;

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
	CourseRepository courseRepository; // Not needed query from Manager

	@Autowired
	ManagerRepository managerRepository; // supposed to be queried from other teams
	
	@Autowired
	ManagerService managerService;

	@Autowired
	NotificationRepository notificationRepository;

	@Autowired
	RatingRepository ratingRepository;

	@Autowired
	RoomRepository roomRepository; // Handled by manager

	@Autowired 
	StudentRepository studentRepository; // Query from student

	@Autowired 
	TimeSlotRepository timeSlotRepository; // Query from manager

	@Autowired
	TutoringSessionRepository tutoringSessionRepository;

	@Autowired
	TutorRepository tutorRepository;

	@BeforeClass
	public void clearManagerDatabase() {
		managerRepository.deleteAll();
	}
	
	
	@BeforeMethod
	@AfterMethod
	public void clearDatabase() {
		notificationRepository.deleteAll();
		ratingRepository.deleteAll();
		tutoringSessionRepository.deleteAll();
		tutorRepository.deleteAll();
	}

	@AfterClass
	public void tearDown() {
		managerRepository.deleteAll();
	}
	
	/**
	 * Preparing to test all RESTful services for services that modify data. 
	 */
	
	/* <----------- Utility methods to create HTTP Requests to the database using RestTemplate ---------------> */
		
	@Test (priority=1, groups="Test preparation")
	public void testCreateManager() {
		// Due to domain model design, a manager has be created in the database in order to perform tests on the Tutor and room classes.
		Manager manager = new Manager();
		HttpEntity<Manager> entity = new HttpEntity<Manager>(manager, headers); // HttpEntity<T> is a helper object which encapsulates header and body of an HTTP request or response.
		ResponseEntity<String> response = restTemplate.postForEntity(constructURLWithLocalPort("/manager/new"), entity, String.class);
		// Make sure response code is 200
		assertEquals(response.getStatusCodeValue(),200);
		
		// Make sure only one manager is present in the database
		assertEquals(managerService.getAllManagers().size(), 1);
		
		// Verify response is not null 
		assertNotNull(response);
	}
	
	
//	@Test(priority=1, groups="Test preparation")
//	public void testCreateRoom() {
//		// Due to domain model design, a Room has to be created in the database in order to perform tests on the TutoringSession.
//		
//		
//	}
//	
//	@Test(priority=1, groups="Test preparation")
//	public void testCreateTimeSlot() {
//		// Due to domain model design, a Room has to be created in the database in order to perform tests on the TutoringSession.
//		
//	}
//	
//	@Test(priority=2, groups="Rating")
//	public void testRatingPOST() {
//
//	}
//
//	@Test(priority=3, groups="Notification")
//	private void testNotificationPOST() {
//
//	}
//
//	@Test(priority=4, groups="TutoringSession")
//	private void createTutoringSession() {
//
//	}
//	
//	@Test(priority=5, groups="Tutor")
//	private void createTutor() {		
//	
//	}
	
//	@Test(dependsOnMethods = "testCreateTutoringSession")
//	public void testGetTutoringSession() {
//		
//	}


	private String constructURLWithLocalPort(String URI) {
		return "http://localhost:" + port + URI;
	}

	
	
}
