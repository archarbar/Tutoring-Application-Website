package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.After;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.TimeSlot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutor.dao.BookingRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTests {


	@Autowired
	private BookingService bookingService;
	@Autowired
	private CourseService courseService;

	@Autowired
	private TimeSlotService timeSlotService;

	@Autowired
	private BookingRepository bookingRepository;


	@After
	public void clearDatabase() {

	    bookingRepository.deleteAll();

	}

	@Test
	public void testCreateBooking() { // test constructor method
		assertEquals(0, bookingService.getAllBookings().size());
		String tutorEmail = "arthurmorgan@redemption.com";
		String studentEmail = "johnmarston@redemption.com";
		Course course = courseService.createCourse("test", Level.CEGEP);
		TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
		try {
			bookingService.createBooking(tutorEmail, studentEmail, Date.valueOf("2019-10-10"), timeSlot, course);
		} catch (IllegalArgumentException e) {
			fail();
		}
		List<Booking> allBookings = bookingService.getAllBookings();
		assertEquals(1, allBookings.size());
//		assertEquals("arthurmorgan@redemption.com", allBookings.get(0).getTutorEmail());
//		assertEquals(timeSlot, allBookings.get(0).getTimeSlot());
//		assertEquals(course, allBookings.get(0).getCourse());
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
