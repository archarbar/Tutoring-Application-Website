package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.After;
import org.junit.Test;

import ca.mcgill.ecse321.tutor.dao.*;
import ca.mcgill.ecse321.tutor.model.*;
import ca.mcgill.ecse321.tutor.service.*;


public class TestTutoringService {

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
	private RoomService roomService;
	@Autowired
	private NotificationService notificationService;
	@Autowired
	private RatingService ratingService;
	@Autowired
	private TutoringSessionService tutoringSessionService;
	@Autowired
	private TimeSlotService timeslotService;


	@After
	public void clearDatabase() {
		//we first clear bookings and tutoring sessions to avoid
		//exceptions due to inconsistencies
		bookingRepository.deleteAll();
		tutoringSessionRepository.deleteAll();
		//then, clear all other tables
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
	 * TUTOR TESTS
	 */
	@Test
	public void testCreateTutor() {
		assertEquals(0, tutorService.getAllTutors().size());
		String firstName = "Marcus";
		String lastName = "Fenix";
		String email = "marcusfenix@gears.com";
		String password = "locust";
		try {
			tutorService.createTutor(firstName, lastName, email, password);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}

		List<Tutor> allTutors = tutorService.getAllTutors();
		assertEquals(1, allTutors.size());
		assertEquals(firstName, allTutors.get(0).getFirstName());
		assertEquals(email, allTutors.get(0).getEmail());
	}


}
