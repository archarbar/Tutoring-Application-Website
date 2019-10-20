//package ca.mcgill.ecse321.tutor.service;
//
//import static org.junit.Assert.*;
//
//import java.sql.Date;
//import java.sql.Time;
//import java.util.List;
//
//import org.junit.After;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.junit.After;
//import org.junit.Test;
//
//import ca.mcgill.ecse321.tutor.dao.*;
//import ca.mcgill.ecse321.tutor.model.*;
//import ca.mcgill.ecse321.tutor.service.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TestTutoringService {
//
//  @Autowired
//  private TutorRepository tutorRepository;
//  @Autowired
//  private StudentRepository studentRepository;
//  @Autowired
//  private ManagerRepository managerRepository;
//  @Autowired
//  private BookingRepository bookingRepository;
//  @Autowired
//  private CourseRepository courseRepository;
//  @Autowired
//  private RoomRepository roomRepository;
//  @Autowired
//  private NotificationRepository notificationRepository;
//  @Autowired
//  private RatingRepository ratingRepository;
//  @Autowired
//  private TutoringSessionRepository tutoringSessionRepository;
//  @Autowired
//  private TimeSlotRepository timeslotRepository;
//
//  @Autowired
//  private TutorService tutorService;
//  @Autowired
//  private StudentService studentService;
//  @Autowired
//  private ManagerService managerService;
//  @Autowired
//  private BookingService bookingService;
//  @Autowired
//  private CourseService courseService;
//  @Autowired
//  private RoomService roomService;
//  @Autowired
//  private NotificationService notificationService;
//  @Autowired
//  private RatingService ratingService;
//  @Autowired
//  private TutoringSessionService tutoringSessionService;
//  @Autowired
//  private TimeSlotService timeSlotService;
//
//
//  @After
//  public void clearDatabase() {
//    //we first clear bookings and tutoring sessions to avoid
//    //exceptions due to inconsistencies
//    bookingRepository.deleteAll();
//    tutoringSessionRepository.deleteAll();
//    //then, clear all other tables
//    tutorRepository.deleteAll();
//    studentRepository.deleteAll();
//    managerRepository.deleteAll();
//    courseRepository.deleteAll();
//    roomRepository.deleteAll();
//    notificationRepository.deleteAll();
//    ratingRepository.deleteAll();
//    timeslotRepository.deleteAll();
//  }
//  
//  
//  /*
//   * TUTOR TESTS
//   */
//  @Test
//  public void testCreateTutor() { //test constructor methods
//    assertEquals(0, tutorService.getAllTutors().size());
//    String firstName = "Marcus";
//    String lastName = "Fenix";
//    String email = "marcusfenix@gears.com";
//    String password = "locust";
//    try {
//      tutorService.createTutor(firstName, lastName, email, password);
//    } catch (IllegalArgumentException e) {
//      // Check that no error occurred
//      fail();
//    }
//    List<Tutor> allTutors = tutorService.getAllTutors();
//    assertEquals(1, allTutors.size());
//    assertEquals(firstName, allTutors.get(0).getFirstName());
//    assertEquals(email, allTutors.get(0).getEmail());
//  }
//
//  @Test
//  public void testCreateTutorNull() {
//    assertEquals(0, tutorService.getAllTutors().size());
//    String firstName = null;
//    String lastName = null;
//    String email = "marcusfenix@gears.com";
//    String password = "locust";
//    String error = null;
//    try {
//      tutorService.createTutor(firstName, lastName, email, password);
//    } catch (IllegalArgumentException e) {
//      error = e.getMessage();
//    }
//
//    // check error
//    assertEquals("Tutor name, email and password need to be specified!", error);
//
//    // check no change in memory
//    List<Tutor> allTutors = tutorService.getAllTutors();
//    assertEquals(0, allTutors.size());
//  }
//
//  @Test
//  public void testCreateTutorEmpty() {
//    assertEquals(0, tutorService.getAllTutors().size());
//    String firstName = "";
//    String lastName = "";
//    String email = "marcusfenix@gears.com";
//    String password = "locust";
//    String error = null;
//    try {
//      tutorService.createTutor(firstName, lastName, email, password);
//    } catch (IllegalArgumentException e) {
//      error = e.getMessage();
//    }
//
//    // check error
//    assertEquals("Tutor name, email and password need to be specified!", error);
//
//    // check no change in memory
//    List<Tutor> allTutors = tutorService.getAllTutors();
//    assertEquals(0, allTutors.size());
//  }
//
//  @Test
//  public void testCreateTutorWhiteSpace() {
//    assertEquals(0, tutorService.getAllTutors().size());
//    String firstName = "   ";
//    String lastName = "";
//    String email = "marcusfenix@gears.com";
//    String password = "locust";
//    String error = null;
//    try {
//      tutorService.createTutor(firstName, lastName, email, password);
//    } catch (IllegalArgumentException e) {
//      error = e.getMessage();
//    }
//
//    // check error
//    assertEquals("Tutor name, email and password need to be specified!", error);
//
//    // check no change in memory
//    List<Tutor> allTutors = tutorService.getAllTutors();
//    assertEquals(0, allTutors.size());
//  }
//
//  @Test
//  public void testChangeTutorName() { // test setter methods
//    assertEquals(0, tutorService.getAllTutors().size());
//    String firstName = "Marcus";
//    String lastName = "Fenix";
//    String email = "marcusfenix@gears.com";
//    String password = "locust";
//    String newFirstName = "JD";
//    String newEmail = "jdfenix@gears.com";
//    try {
//      Tutor t = tutorService.createTutor(firstName, lastName, email, password);
//      t.setFirstName(newFirstName);
//      t.setEmail(newEmail);
//      tutorRepository.save(t);
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    List<Tutor> allTutors = tutorService.getAllTutors();
//    assertEquals(newFirstName, allTutors.get(0).getFirstName());
//    assertEquals(newEmail, allTutors.get(0).getEmail());
//  }
//
//  @Test
//  public void testGetTutor() { // test getter method
//    assertEquals(0, tutorService.getAllTutors().size());
//    String firstName = "Marcus";
//    String lastName = "Fenix";
//    String email = "marcusfenix@gears.com";
//    String password = "locust";
//    Tutor tutor = tutorService.createTutor(firstName, lastName, email, password);
//    List<Tutor> allTutors = null;
//    try {
//      allTutors = tutorService.getAllTutors();
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    assertEquals(tutor.getId(), allTutors.get(0).getId());
//  }
//
//  /*
//   * BOOKING TESTS
//   */
//
//  @Test
//  public void testCreateBooking() { // test constructor method
//    assertEquals(0, bookingService.getAllBookings().size());
//    String tutorEmail = "arthurmorgan@redemption.com";
//    String studentEmail = "johnmarston@redemption.com";
//    Course course = courseService.createCourse("test", Level.CEGEP);
//    TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
//    try {
//      bookingService.createBooking(tutorEmail, studentEmail, Date.valueOf("2019-10-10"), timeSlot, course);
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    List<Booking> allBookings = bookingService.getAllBookings();
//    assertEquals(1, allBookings.size());
//    assertEquals("arthurmorgan@redemption.com", allBookings.get(0).getTutorEmail());
//    assertEquals(timeSlot, allBookings.get(0).getTimeSlot());
//    assertEquals(course, allBookings.get(0).getCourse());
//  }
//
//  @Test
//  public void testCreateBookingNullTimeSlot() { // test constructor method
//    assertEquals(0, bookingService.getAllBookings().size());
//    String error = null;
//    String tutorEmail = "arthurmorgan@redemption.com";
//    String studentEmail = "johnmarston@redemption.com";
//    Course course = courseService.createCourse("test", Level.CEGEP);
//    TimeSlot timeSlot = null;
//    Booking booking = bookingService.createBooking(tutorEmail, studentEmail, Date.valueOf("2019-10-10"), timeSlot, course);
//    List<Booking> allBookings = null;
//    try {
//      allBookings = bookingService.getAllBookings();
//    } catch (IllegalArgumentException e) {
//      error = e.getMessage();
//    }
//    // check error
//    assertEquals("A time slot needs to be specified!", error);
//    // check no change in memory
//    assertEquals(0, allBookings.size());
//    assertEquals(booking.getId(), allBookings.get(0).getId());
//  }
//
//  @Test
//  public void testGetBooking() { // test getter method
//    assertEquals(0, bookingService.getAllBookings().size());
//    String tutorEmail = "arthurmorgan@redemption.com";
//    String studentEmail = "johnmarston@redemption.com";
//    Course course = courseService.createCourse("test", Level.CEGEP);
//    TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
//    Booking booking = bookingService.createBooking(tutorEmail, studentEmail, Date.valueOf("2019-10-10"), timeSlot, course);
//    List<Booking> allBookings = null;
//    try {
//      allBookings = bookingService.getAllBookings();
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    assertEquals(booking.getId(), allBookings.get(0).getId());
//  }
//
//  /*
//   *  COURSE TESTS
//   */
//
//  @Test
//  public void testCreateCourse() { // test constructor method
//    assertEquals(0, courseService.getAllCourses().size());
//    String name = "English";
//    Level level = Level.UNIVERSITY;
//    try {
//      courseService.createCourse(name, level);
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    List<Course> allCourses = courseService.getAllCourses();
//    assertEquals(1, allCourses.size());
//    assertEquals("English", allCourses.get(0).getCourseName());
//    assertEquals(Level.UNIVERSITY, allCourses.get(0).getCourseLevel());
//  }
//
//  @Test
//  public void testGetCourse() { // test getter method
//    assertEquals(0, courseService.getAllCourses().size());
//    String name = "English";
//    Level level = Level.UNIVERSITY;
//    Course course = courseService.createCourse(name, level);
//    List<Course> allCourses = null;
//    try {
//      allCourses = courseService.getAllCourses();
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    assertEquals(course.getId(), allCourses.get(0).getId());
//  }
//
//  /*
//   *  ROOM TESTS
//   */
//
//  @Test
//  public void testCreateRoom() { // test constructor method
//    assertEquals(0, roomService.getAllRooms().size());
//    Integer number = 12;
//    Integer capacity = 30;
//    try {
//      roomService.createRoom(number, capacity);
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    List<Room> allRooms = roomService.getAllRooms();
//    assertEquals(1, allRooms.size());
//    assertEquals((Integer) 12, allRooms.get(0).getRoomNumber());
//    assertEquals((Integer) 30, allRooms.get(0).getRoomCapacity());
//  }
//
//  @Test
//  public void testGetRoom() { // test getter method
//    assertEquals(0, roomService.getAllRooms().size());
//    Integer number = 23;
//    Integer capacity = 20;
//    Room room = roomService.createRoom(number, capacity);
//    List<Room> allRooms = null;
//    try {
//      allRooms = roomService.getAllRooms();
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    assertEquals(room.getId(), allRooms.get(0).getId());
//  }
//
//  /*
//   *  NOTIFICATION TESTS
//   */
//
//  @Test
//  public void testCreateNotification() { // test constructor method
//    assertEquals(0, notificationService.getAllNotifications().size());
//    String firstName = "Arthur";
//    String lastName = "Morgan";
//    String tutorEmail = "arthurmorgan@redemption.com";
//    String password = "tahiti";
//    Tutor tutor = tutorService.createTutor(firstName, lastName, tutorEmail, password);
//    String studentEmail = "johnmarston@redemption.com";
//    Course course = courseService.createCourse("test", Level.CEGEP);
//    TimeSlot timeSlot = timeSlotService.createTimeSlot(Time.valueOf("10:12:12"), Time.valueOf("12:12:12"), DayOfTheWeek.THURSDAY);
//    Booking booking = bookingService.createBooking(tutorEmail, studentEmail, Date.valueOf("2019-10-10"), timeSlot, course);
//    try {
//      notificationService.createNotification(booking, tutorEmail);
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    List<Notification> allNotifications = notificationService.getAllNotifications();
//    assertEquals(1, allNotifications.size());
//    assertEquals(booking, allNotifications.get(0).getBooking());
//    assertEquals(tutor, allNotifications.get(0).getTutor());
//  }
//
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
//
//  /*
//   *  RATING TESTS
//   */
//
//  @Test
//  public void testCreateRating() { // test constructor method
//    assertEquals(0, ratingService.getAllRatings().size());
//    Integer stars = 4;
//    String comment = "Best tutor!";
//    try {
//      ratingService.createRating(stars, comment);
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    List<Rating> allRatings = ratingService.getAllRatings();
//    assertEquals(1, allRatings.size());
//    assertEquals((Integer) 12, allRatings.get(0).getStars());
//    assertEquals((Integer) 30, allRatings.get(0).getComment());
//  }
//
//  @Test
//  public void testGetRating() { // test getter method
//    assertEquals(0, ratingService.getAllRatings().size());
//    Integer stars = 4;
//    String comment = "Best tutor!";
//    Rating rating = ratingService.createRating(stars, comment);
//    List<Rating> allRatings = null;
//    try {
//      allRatings = ratingService.getAllRatings();
//    } catch (IllegalArgumentException e) {
//      fail();
//    }
//    assertEquals(rating.getId(), allRatings.get(0).getId());
//  }
//
//}
