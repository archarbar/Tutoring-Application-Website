package ca.mcgill.ecse321.tutor.persistence;

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

import ca.mcgill.ecse321.tutor.model.*;

import ca.mcgill.ecse321.tutor.service.*;

import ca.mcgill.ecse321.tutor.dao.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingDaoTests {

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
	private RatingService ratingService;

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

	@After
	public void clearDatabase() {
		ratingRepository.deleteAll();
		tutoringSessionRepository.deleteAll();
		bookingRepository.deleteAll();
		tutorRepository.deleteAll();
		roomRepository.deleteAll();
		timeSlotRepository.deleteAll();
		managerRepository.deleteAll();
		studentRepository.deleteAll();
	}

	@Test
	public void testCreateRating() {
		assertEquals(0, ratingService.getAllRatings().size());

		Integer stars = 5;
		String comment = "Great!!!";
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

		Booking booking = bookingService.createBooking(tutorEmail, studentSet, sessionDate, timeSlot, course);
		Tutor tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password, manager);
		TutoringSession tutoringSession = tutoringSessionService.createTutoringSession(sessionDate, tutor, room, booking, timeSlot);

		try {
			ratingService.createRating(stars, comment, student, tutor, tutoringSession);
		}
		catch (IllegalArgumentException e) {
			fail();
		}

			List<Rating> allRatings = ratingService.getAllRatings();

			assertEquals(1, allRatings.size());
			assertEquals(stars, allRatings.get(0).getStars());
			assertEquals(comment, allRatings.get(0).getComment());
			assertEquals(student.getId(), allRatings.get(0).getStudent().getId());
			assertEquals(tutor.getId(), allRatings.get(0).getTutor().getId());
			assertEquals(tutoringSession.getId(), allRatings.get(0).getTutoringSession().getId());
	}



}



