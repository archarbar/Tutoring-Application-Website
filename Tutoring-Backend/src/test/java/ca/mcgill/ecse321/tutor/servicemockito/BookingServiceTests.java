package ca.mcgill.ecse321.tutor.servicemockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
import ca.mcgill.ecse321.tutor.dao.CourseRepository;
import ca.mcgill.ecse321.tutor.dao.StudentRepository;
import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.CourseService;
import ca.mcgill.ecse321.tutor.service.StudentService;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;


@RunWith(MockitoJUnitRunner.class)
public class BookingServiceTests {

	@Mock
	private BookingRepository bookingDao;

	@InjectMocks
	private BookingService bookingService;
	@InjectMocks
	private CourseService courseService;
	@InjectMocks
	private TimeSlotService timeSlotService;
	@InjectMocks
	private StudentService studentService;

	private Student student;
	private TimeSlot timeSlot;
	private Course course;
	private Set<Student> studentSet = new HashSet<Student>();
	
	private Booking booking = new Booking();

	private static final Integer BOOKING_KEY = 1;
	private static final String TUTOR_EMAIL = "arthurmorgan@redemption.com";
	private static final Date BOOKING_DATE = Date.valueOf("2019-10-10");
	private static final String STUDENT_FIRSTNAME = "Michael";
	private static final String STUDENT_LASTNAME = "Li";
	private static final String STUDENT_EMAIL = "mlej@live.com";

	@Before
	public void setMockOutput() {
		when(bookingDao.findBookingById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(BOOKING_KEY)) {
				booking.setId(BOOKING_KEY);
				return booking;
			} else {
				return null;
			}
		});
		when(bookingDao.findBookingByTutorEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(TUTOR_EMAIL)) {
				List<Booking> bookings = new ArrayList<>();
				booking.setTutorEmail(TUTOR_EMAIL);
				bookings.add(booking);
				return bookings;
			} else {
				return null;
			}
		});
		when(bookingDao.findBookingBySpecificDate(any())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(BOOKING_DATE)) {
				List<Booking> bookings = new ArrayList<>();
				booking.setSpecificDate(BOOKING_DATE);
				bookings.add(booking);
				return bookings;
			} else {
				return null;
			}
		});
		when(bookingDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
			List<Booking> bookings = new ArrayList<>();
			booking.setId(BOOKING_KEY);
			bookings.add(booking);
			return bookings;
		});
	}
	
	@Before
	public void setUpMocks() {
		student = mock(Student.class);
		student.setFirstName(STUDENT_FIRSTNAME);
		student.setLastName(STUDENT_LASTNAME);
		student.setEmail(STUDENT_EMAIL);
		studentSet.add(student);
		timeSlot = mock(TimeSlot.class);
		course = mock(Course.class);
	}

	
	@Test
	public void testCreateBooking() {
//		assertEquals(0, bookingService.getAllBookings().size());

		try {
			booking = bookingService.createBooking(TUTOR_EMAIL, studentSet, BOOKING_DATE, timeSlot, course);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(TUTOR_EMAIL, booking.getTutorEmail());
		assertEquals(timeSlot.getId(), booking.getTimeSlot().getId());
		assertEquals(course.getId(), booking.getCourse().getId());
	}

	@Test
	public void testCreateBookingNull() {
//		assertEquals(0, bookingService.getAllBookings().size());

		String error = null;

		try {
			bookingService.createBooking(null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A tutor email needs to be specified! A student needs to be specified! A date needs to be specified! A time slot needs to be specified! A course needs to be specified!", error);
	}
	
	@Test
	public void testCreateBookingNullTutor() {
//		assertEquals(0, bookingService.getAllBookings().size());

		String error = null;

		try {
			bookingService.createBooking(null, studentSet, BOOKING_DATE, timeSlot, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A tutor email needs to be specified!", error);
	}
	
	@Test
	public void testCreateBookingNullStudent() {
//		assertEquals(0, bookingService.getAllBookings().size());

		String error = null;

		try {
			bookingService.createBooking(TUTOR_EMAIL, null, BOOKING_DATE, timeSlot, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A student needs to be specified!", error);
	}

	@Test
	public void testCreateBookingEmpty() {
//		assertEquals(0, bookingService.getAllBookings().size());

		String error = null;

		try {
			bookingService.createBooking("", studentSet, BOOKING_DATE, timeSlot, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A tutor email needs to be specified!", error);
	}

	@Test
	public void testCreateBookingSpaces() {
//		assertEquals(0, bookingService.getAllBookings().size());

		String error = null;

		try {
			bookingService.createBooking(" ", studentSet, BOOKING_DATE, timeSlot, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A tutor email needs to be specified!", error);
	}
	
	
	@Test
	public void testGetBooking() { // test getter method
		assertEquals(BOOKING_KEY, bookingService.getBookingById(BOOKING_KEY).getId());
		assertEquals(TUTOR_EMAIL, bookingService.getBookingByTutorEmail(TUTOR_EMAIL).get(0).getTutorEmail());
		assertEquals(BOOKING_DATE, bookingService.getBookingBySpecificDate(BOOKING_DATE).get(0).getSpecificDate());
		assertEquals(BOOKING_KEY, bookingService.getAllBookings().get(0).getId());
	}
}
