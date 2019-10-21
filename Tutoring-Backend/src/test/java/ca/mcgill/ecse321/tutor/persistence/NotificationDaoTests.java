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
import org.junit.After;
import org.junit.Test;

import ca.mcgill.ecse321.tutor.dao.*;
import ca.mcgill.ecse321.tutor.model.*;
import ca.mcgill.ecse321.tutor.service.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationDaoTests {
  
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
  private TimeSlotService timeSlotService;


  @After
  public void clearDatabase() {
    //we first clear bookings and tutoring sessions to avoid
    //exceptions due to inconsistencies
    notificationRepository.deleteAll();
    ratingRepository.deleteAll();
    bookingRepository.deleteAll();
    tutorRepository.deleteAll();
    studentRepository.deleteAll();
    managerRepository.deleteAll();
    courseRepository.deleteAll();
    roomRepository.deleteAll();
    timeslotRepository.deleteAll();

  }
  
  /*
   *  NOTIFICATION TESTS
   */

  @Test
  public void testCreateNotification() { // test constructor method
    assertEquals(0, notificationService.getAllNotifications().size());
	String tutorEmail = "marcusfenix@gears.com";
	Course course = courseService.createCourse("test", Level.CEGEP);
	String firstName = "Michael";
	String lastName = "Li";
	String email = "mlej@live.com";
	Student student = studentService.createStudent(firstName, lastName, email);
	Set<Student> studentSet = new HashSet<Student>();
	studentSet.add(student);
	TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
	Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);
	String tutorFirstName = "Marcus";
	String tutorLastName = "Fenix";
	String password = "locust";
	Manager manager = managerService.createManager();
	Tutor tutor = tutorService.createTutor(tutorFirstName, tutorLastName, tutorEmail, password, manager);
    
    try {
      notificationService.createNotification(booking, tutor);
    } catch (IllegalArgumentException e) {
      fail();
    }
    
    List<Notification> allNotifications = notificationService.getAllNotifications();
    assertEquals(1, allNotifications.size());
    assertEquals(booking.getId(), allNotifications.get(0).getBooking().getId());
    assertEquals(tutor.getId(), allNotifications.get(0).getTutor().getId());
  }

//  @Test
//  public void testGetNotification() { // test getter method
//    assertEquals(0, notificationService.getAllNotifications().size());
//    String tutorEmail = "arthurmorgan@redemption.com";
//    String studentEmail = "johnmarston@redemption.com";
//    Course course = courseService.createCourse("test", Level.CEGEP);
//    TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
//    Booking booking = bookingService.createBooking(tutorEmail, studentEmail, Date.valueOf("2019-10-10"), timeSlot, course);
//    Notification notification = notificationService.createNotification(booking, tutorEmail);
//    List<Notification> allNotifications = null;
//    try {
//      allNotifications = notificationService.getAllNotifications();
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    assertEquals(notification.getId(), allNotifications.get(0).getId());
//  }

}
