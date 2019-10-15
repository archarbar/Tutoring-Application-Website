package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
public class RatingServiceTests {

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
  private RatingService ratingService;
  @Autowired
  private TutoringSessionService tutoringSessionService;
  @Autowired
  private TimeSlotService timeSlotService;

  @After
  public void clearDatabase() {
    bookingRepository.deleteAll();
    tutoringSessionRepository.deleteAll();
    tutorRepository.deleteAll();
    studentRepository.deleteAll();
    managerRepository.deleteAll();
    courseRepository.deleteAll();
    roomRepository.deleteAll();
    notificationRepository.deleteAll();
    ratingRepository.deleteAll();
    timeslotRepository.deleteAll();
  }

  @Test
  public void testCreateRating() {
    assertEquals(0, ratingService.getAllRatings().size());

    Integer stars = 5;
    String comment = "Great!!!";

    Date sessionDate = Date.valueOf("2019-10-14");

    Manager manager = managerService.createManager();

    Room room = roomService.createRoom(12, 30, manager);

    String firstName = "Michael";
    String lastName = "Li";
    String email = "mlej@live.com";
    Student student = studentService.createStudent(firstName, lastName, email);

    Set<Student> studentSet = new HashSet<Student>();
    studentSet.add(student);

    String tutorEmail = "arthurmorgan@redemption.com";

    TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);

    Course course = courseService.createCourse("test", Level.CEGEP);

    Booking booking = bookingService.createBooking(tutorEmail, studentSet, Date.valueOf("2019-10-10"), timeSlot, course);

    String firstNameT = "Marcus";
    String lastNameT = "Fenix";
    String emailT = "marcusfenix@gears.com";
    String password = "locust";
    Tutor tutor = tutorService.createTutor(firstNameT, lastNameT, emailT, password, manager);

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
    assertEquals(student, allRatings.get(0).getStudent());
    assertEquals(tutor, allRatings.get(0).getTutor());
    assertEquals(tutoringSession, allRatings.get(0).getTutoringSession());
  }

}
