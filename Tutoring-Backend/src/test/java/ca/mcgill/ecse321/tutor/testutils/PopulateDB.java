package ca.mcgill.ecse321.tutor.testutils;

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
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Notification;
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

@RunWith(SpringRunner.class)
@SpringBootTest
public class PopulateDB {
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
	@Autowired
	private CourseRepository courseRepository;	

	@Autowired
	BookingService bookingService;

	@Autowired 
	CourseService courseService; // Not needed query from Manager

	@Autowired
	ManagerService managerService;

	@Autowired
	NotificationService notificationService;

	@Autowired
	RatingService ratingService;

	@Autowired
	RoomService roomService; // Handled by manager

	@Autowired 
	StudentService studentService; // Query from student

	@Autowired 
	TimeSlotService timeSlotService; // Query from manager

	@Autowired
	TutoringSessionService tutoringSessionService;

	@Autowired
	TutorService tutorService;
	
	@Before
	public void deleteDatabase() {
		ratingRepository.deleteAll();
		bookingRepository.deleteAll();
		tutoringSessionRepository.deleteAll();
		notificationRepository.deleteAll();
		roomRepository.deleteAll();
		tutorRepository.deleteAll();
		managerRepository.deleteAll();
		studentRepository.deleteAll();
		courseRepository.deleteAll();
		timeSlotRepository.deleteAll();
	}
	
	
	@After
	public void doNothing() {
		
	}
	
	@Test
	public void setupDatabase() {

			
		Manager manager = managerService.createManager();
		
		// Create tutor in database 
		String firstName = "John";
		String lastName = "Lennon";
		String tutorEmail = "duQuebec@poushon.com";
		String password = "123456";
		Tutor tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password);
		
		String firstNameWill = "William";
		String lastNameWill = "Zhang";
		String emailWill = "william.zhang2@mail.mcgill.ca";
		String passwordWill = "12345678";
		boolean isApprovedWill = true;
		Tutor tutorWill = tutorService.createTutor(firstNameWill, lastNameWill, emailWill, passwordWill);
		tutorWill.setIsApproved(isApprovedWill);
		tutorWill.setHourlyRate(15);
		
		tutorRepository.save(tutorWill);
		
//		tutorService.changeHourlyRate(tutorWill.getId(), 15);
		
		System.out.println(tutorWill.getHourlyRate());
		System.out.println(tutorService.getTutorById(tutorWill.getId()).getHourlyRate());
//		tutorWill.setIsApproved(isApprovedWill);
//		tutorWill.setHourlyRate(15);
		
		String firstNameVictor = "Victor";
		String lastNameVictor = "Zhong";
		String emailVictor = "victor.zhong@mail.mcgill.ca";
		Student studentVic = studentService.createStudent(firstNameVictor, lastNameVictor, emailVictor);
		studentRepository.save(studentVic);
		String courseName321 = "ECSE 321";
		Level level321 = Level.UNIVERSITY;
		Course ECSE321 = courseService.createCourse(courseName321, level321);
		
		courseRepository.save(ECSE321);
		String courseName211 = "ECSE 211";
		Level level211 = Level.UNIVERSITY;
		Course ECSE211 = courseService.createCourse(courseName211, level211);
//		courseRepository.save(ECSE211);
		
		Set<Course> courseSet = new HashSet<Course>();
		courseSet.add(ECSE321);
		courseSet.add(ECSE211);
		
		
		tutorWill.setCourse(courseSet);
		
		TimeSlot timeSlot3 = timeSlotService.createTimeSlot(Time.valueOf("10:00:00"), Time.valueOf("12:00:00"), DayOfTheWeek.FRIDAY);
		TimeSlot timeSlot2 = timeSlotService.createTimeSlot(Time.valueOf("13:00:00"), Time.valueOf("15:00:00"), DayOfTheWeek.MONDAY);
		TimeSlot timeSlot1 = timeSlotService.createTimeSlot(Time.valueOf("10:00:00"), Time.valueOf("12:00:00"), DayOfTheWeek.MONDAY);
		

		Set<TimeSlot> timeSlotSet = new HashSet<TimeSlot>();
		timeSlotSet.add(timeSlot1);
		timeSlotSet.add(timeSlot2);
		
		tutorWill.setTimeSlot(timeSlotSet);
		tutorRepository.save(tutorWill);
//		TimeSlot timeSlot1 = tutorService.addTimeSlotForTutor(tutorWill, "10:00:00", "12:00:00", "MONDAY");
		timeSlotRepository.save(timeSlot1);
		timeSlotRepository.save(timeSlot2);

		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(studentVic);
		
		Booking booking1 = bookingService.createBooking(emailWill, studentSet, Date.valueOf("2019-11-29"), timeSlot3, ECSE321);
		Booking booking2 = bookingService.createBooking(emailWill, studentSet, Date.valueOf("2019-12-2"), timeSlot1, ECSE321);
		Booking booking3 = bookingService.createBooking(emailWill, studentSet, Date.valueOf("2019-12-2"), timeSlot2, ECSE321);

//		bookingRepository.save(booking1);
//		bookingRepository.save(booking2);
//		bookingRepository.save(booking3);

		Room room1 = roomService.createRoom(1, 2, manager);
//		roomRepository.save(room1);
		Room room2 = roomService.createRoom(2, 2, manager);
//		roomRepository.save(room2);
		
		
		TutoringSession tutoringSession = tutoringSessionService.createTutoringSession(booking1.getSpecificDate(), 
																						tutorWill, room1, booking1,
																						booking1.getTimeSlot());
		tutoringSessionRepository.save(tutoringSession);
		System.out.println(tutoringSession);
//		notificationRepository.delete(booking1.getNotification());
		System.out.println(tutorWill.getTutoringSession());
//		List<Booking> allBookings = bookingService.getAllBookings();
//		System.out.println(allBookings.get(0).getTutorEmail());
		
//		bookingService.declineBookingById(booking.getId());
//		studentVic.setBooking(bookingSet);
//		System.out.println(timeSlot1.getBooking().get(0));
//		studentRepository.save(studentVic);
//		tutorRepository.save(tutorWill);
//		bookingRepository.save(booking);
		
		
		
	}
}
