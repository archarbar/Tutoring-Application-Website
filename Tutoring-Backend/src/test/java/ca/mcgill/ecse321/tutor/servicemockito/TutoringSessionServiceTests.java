package ca.mcgill.ecse321.tutor.servicemockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
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

import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.Student;
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

	private TutoringSession tutoringSession = new TutoringSession();

	private static final Integer SUCCESS_KEY = 1;
	private Tutor tutor;

	@Before
	public  void setMock(){
		tutor = mock(Tutor.class);
		tutor.setEmail("test@email.com");
		tutor.setFirstName("Test");
		tutor.setLastName("Tutor");
		tutor.setManager(mock(Manager.class));
	}

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

	@Test
	public void testGetTutoringSession(){
		assertEquals(SUCCESS_KEY, tutoringSessionService.getTutoringSessionById(SUCCESS_KEY).getId());
		assertEquals(tutor, tutoringSessionService.getTutoringSessionByTutor(tutor).get(0).getTutor());
		assertEquals(SUCCESS_KEY, tutoringSessionService.getAllTutoringSessions().get(0).getId());
	}

	@Test
	public void testCreateTutoringSession() {
		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());

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
		Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
		Tutor tutor = tutorService.createTutor(firstName, lastName, email, password, manager);

		try {
			tutoringSession = tutoringSessionService.createTutoringSession(sessionDate, tutor, room, booking, timeSlot);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(sessionDate, tutoringSession.getSessionDate());
	}

	@Test
	public void testCreateTutoringSessionNull() {
		Date sessionDate = null;
		Tutor tutor = null;
		Room room = null;
		Booking booking = null;
		TimeSlot timeSlot = null;
		String error = null;

		try {
			tutoringSessionService.createTutoringSession(sessionDate, tutor, room, booking, timeSlot);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A date needs to be specified! A tutor needs to be specified! A room needs to be specified! A booking needs to be specified! A timeSlot needs to be specified!", error);
	}

}
