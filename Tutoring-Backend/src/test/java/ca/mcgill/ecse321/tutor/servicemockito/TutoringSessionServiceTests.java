package ca.mcgill.ecse321.tutor.servicemockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.CourseService;
import ca.mcgill.ecse321.tutor.service.ManagerService;
import ca.mcgill.ecse321.tutor.service.RoomService;
import ca.mcgill.ecse321.tutor.service.StudentService;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;
import ca.mcgill.ecse321.tutor.service.TutorService;
import ca.mcgill.ecse321.tutor.service.TutoringSessionService;


@RunWith(MockitoJUnitRunner.class)
public class TutoringSessionServiceTests {

	@Mock
	private TutoringSessionRepository tutoringSessionRepository;

	@InjectMocks
	private TutoringSessionService tutoringSessionService;
	@InjectMocks
	private BookingService bookingService;
	@InjectMocks
	private TutorService tutorService;
	@InjectMocks
	private RoomService roomService;
	@InjectMocks
	private TimeSlotService timeSlotService;
	@InjectMocks
	private ManagerService managerService;
	@InjectMocks
	private CourseService courseService;
	@InjectMocks
	private StudentService studentService;
	
	private Tutor tutor;
	private Room room;
	private Booking booking;
	private TimeSlot timeSlot;

	private TutoringSession tutoringSession = new TutoringSession();

	private static final Integer SUCCESS_KEY = 1;
	private static final Date SESSION_DATE = Date.valueOf("2019-10-14");

	@Before
	public void setMockOutput(){
		when(tutoringSessionRepository.findTutoringSessionById(anyInt())).thenAnswer( (InvocationOnMock invocation) ->{
			if (invocation.getArgument(0).equals(SUCCESS_KEY)){
				tutoringSession.setId(SUCCESS_KEY);
				return tutoringSession;
			} else {
				return null;
			}
		});
		when(tutoringSessionRepository.findTutoringSessionByTutor(any(Tutor.class))).thenAnswer( (InvocationOnMock invocation) ->{
			if (invocation.getArgument(0).equals(tutor)){
				List<TutoringSession> tutoringSessions = new ArrayList<>();
				tutoringSession.setTutor(tutor);
				tutoringSessions.add(tutoringSession);
				return tutoringSessions;
			} else {
				return null;
			}
		});
		when(tutoringSessionRepository.findAll()).thenAnswer( (InvocationOnMock invocation) ->{
			List<TutoringSession> tutoringSessions = new ArrayList<>();
			tutoringSession.setId(SUCCESS_KEY);
			tutoringSessions.add(tutoringSession);
			return tutoringSessions;
		});
	}
	
	@Before
	public  void setMock(){
		tutor = mock(Tutor.class);
		room = mock(Room.class);
		booking = mock(Booking.class);
		timeSlot = mock(TimeSlot.class);
	}

	@Test
	public void testGetTutoringSession(){
		assertEquals(SUCCESS_KEY, tutoringSessionService.getTutoringSessionById(SUCCESS_KEY).getId());
		assertEquals(tutor, tutoringSessionService.getTutoringSessionByTutor(tutor).get(0).getTutor());
		assertEquals(SUCCESS_KEY, tutoringSessionService.getAllTutoringSessions().get(0).getId());
	}

	@Test
	public void testCreateTutoringSession() {

		try {
			tutoringSession = tutoringSessionService.createTutoringSession(SESSION_DATE, tutor, room, booking, timeSlot);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(SESSION_DATE, tutoringSession.getSessionDate());
		assertEquals(tutor.getId(), tutoringSession.getTutor().getId());
		assertEquals(room.getId(), tutoringSession.getRoom().getId());
		assertEquals(booking.getId(), tutoringSession.getBooking().getId());
		assertEquals(timeSlot.getId(), tutoringSession.getTimeSlot().getId());
	}

	@Test
	public void testCreateTutoringSessionNull() {
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(null, null, null, null, null);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A date needs to be specified! A tutor needs to be specified! A room needs to be specified! A booking needs to be specified! A timeSlot needs to be specified!", error);
	}

	@Test
	public void testCreateTutoringSessionNullDate() {
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(null, tutor, room, booking, timeSlot);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A date needs to be specified!", error);
	}

	@Test
	public void testCreateTutoringSessionNullTutor() {
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(SESSION_DATE, null, room, booking, timeSlot);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A tutor needs to be specified!", error);
	}

	@Test
	public void testCreateTutoringSessionNullRoom() {
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(SESSION_DATE, tutor, null, booking, timeSlot);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A room needs to be specified!", error);
	}

	@Test
	public void testCreateTutoringSessionNullBooking() {
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(SESSION_DATE, tutor, room, null, timeSlot);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A booking needs to be specified!", error);
	}

	@Test
	public void testCreateTutoringSessionNullTimeSlot() {
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(SESSION_DATE, tutor, room, booking, null);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A timeSlot needs to be specified!", error);
	}

}
