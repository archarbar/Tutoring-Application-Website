package ca.mcgill.ecse321.tutor.servicemockito;

import ca.mcgill.ecse321.tutor.dao.NotificationRepository;
import ca.mcgill.ecse321.tutor.service.NotificationService;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTests {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

	private Student student;
	private Booking booking;
	private Tutor tutor;
	private Set<Student> studentSet = new HashSet<Student>();
	
    private Notification notification = new Notification();

    private static final Integer SUCCESS_KEY = 1;
    private static final String TUTOR_EMAIL = "marcusfenix@gears.com";
    private static final String TUTOR_FIRSTNAME = "Marcus";
    private static final String TUTOR_LASTNAME = "Fenix";
    private static final String TUTOR_PASSWORD = "locust";


    @Before
    public void setMockOutput(){
        when(notificationRepository.findNotificationById(anyInt())).thenAnswer( (InvocationOnMock invocation) ->{
            if (invocation.getArgument(0).equals(SUCCESS_KEY)){
                notification.setId(SUCCESS_KEY);
                return notification;
            } else {
                return null;
            }
        });
        when(notificationRepository.findAll()).thenAnswer( (InvocationOnMock invocation) ->{
            List<Notification> notifications = new ArrayList<>();
            notification.setId(SUCCESS_KEY);
            notifications.add(notification);
            return notifications;
        });
    }
    
	@Before
	public void setUpMocks() {
		student = mock(Student.class);
		studentSet.add(student);
		booking = mock(Booking.class);
		booking.setStudent(studentSet);
		tutor = mock(Tutor.class);
		tutor.setFirstName(TUTOR_FIRSTNAME);
		tutor.setLastName(TUTOR_LASTNAME);
		tutor.setPassword(TUTOR_PASSWORD);
		tutor.setEmail(TUTOR_EMAIL);
	}

	@Test
	public void testCreateNotification() { // test constructor method
//		assertEquals(0, notificationService.getAllNotifications().size());

		try {
			notification = notificationService.createNotification(booking, tutor);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(booking.getId(), notification.getBooking().getId());
		assertEquals(tutor.getId(), notification.getTutor().getId());
		assertEquals(tutor.getFirstName(), notification.getTutor().getFirstName());
		assertEquals(tutor.getPassword(), notification.getTutor().getPassword());
		assertEquals(booking.getStudent(), notification.getBooking().getStudent());
	}

	@Test
	public void testCreateNotificationNull() {
//		assertEquals(0, notificationService.getAllNotifications().size());

		String error = null;

		try {
			notificationService.createNotification(null, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A booking needs to be specified! A tutor needs to be specified!", error);
	}
	
	@Test
	public void testCreateNotificationNullBooking() {
//		assertEquals(0, notificationService.getAllNotifications().size());

		String error = null;

		try {
			notificationService.createNotification(null, tutor);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A booking needs to be specified!", error);
	}
	
	@Test
	public void testCreateNotificationNullTutor() {
//		assertEquals(0, notificationService.getAllNotifications().size());

		String error = null;

		try {
			notificationService.createNotification(booking, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A tutor needs to be specified!", error);
	}
    
    @Test
    public void testGetNotification(){
        assertEquals(SUCCESS_KEY, notificationService.getNotification(SUCCESS_KEY).getId());
        assertEquals(SUCCESS_KEY, notificationService.getAllNotifications().get(0).getId());
    }

}
