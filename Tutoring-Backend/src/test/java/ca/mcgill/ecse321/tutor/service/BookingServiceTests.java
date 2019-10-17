package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
import ca.mcgill.ecse321.tutor.dao.CourseRepository;
import ca.mcgill.ecse321.tutor.dao.StudentRepository;
import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource("classpath:applications.properties")
public class BookingServiceTests {


	@Autowired
	private BookingService bookingService;
	@Autowired
	private CourseService courseService;

	@Autowired
	private TimeSlotService timeSlotService;
	
	@Autowired
	private StudentService studentService;

	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Autowired
	private CourseRepository courseRepository;


	@After
	public void clearDatabase() {

	    bookingRepository.deleteAll();
	    studentRepository.deleteAll();
	    timeSlotRepository.deleteAll();
	    courseRepository.deleteAll();
	}

	@Test
	public void testCreateBooking() { // test constructor method
		assertEquals(0, bookingService.getAllBookings().size());
		String tutorEmail = "arthurmorgan@redemption.com";
		Course course = courseService.createCourse("test", Level.CEGEP);
		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";
		Student student = studentService.createStudent(firstName, lastName, email);
		Set<Student> studentSet = new HashSet<Student>();
		studentSet.add(student);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		try {
			bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		} catch (IllegalArgumentException e) {
			fail();
		}
		List<Booking> allBookings = bookingService.getAllBookings();
		assertEquals(1, allBookings.size());
		assertEquals("arthurmorgan@redemption.com", allBookings.get(0).getTutorEmail());
		assertEquals(timeSlot.getId(), allBookings.get(0).getTimeSlot().getId());
		assertEquals(course.getId(), allBookings.get(0).getCourse().getId());
	}


	//	@Test
	//	public void testGetStudent() {
	//		String firstName = "Michael";
	//		String lastName = "Li";
	//		
	//		Student student = studentService.getStudentByName(firstName, lastName);
	//		
	//		assertEquals(firstName, student.getFirstName());
	//		assertEquals(lastName, student.getLastName());
	//	}

}
