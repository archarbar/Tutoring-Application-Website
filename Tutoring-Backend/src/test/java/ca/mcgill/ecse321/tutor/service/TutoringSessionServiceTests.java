package ca.mcgill.ecse321.tutor.service;

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

import ca.mcgill.ecse321.tutor.model.*;

import ca.mcgill.ecse321.tutor.service.*;

import ca.mcgill.ecse321.tutor.dao.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TutoringSessionServiceTests {

  @Autowired
  private TutoringSessionService tutoringSessionService;
  
  @Autowired
  private BookingService bookingService;
  @Autowired
  private TutorService tutorService;
  @Autowired
  private RoomService roomService;
  @Autowired
  private TimeSlotService timeSlotService;
  @Autowired
  private ManagerService managerService;
  @Autowired
  private CourseService courseService;
  @Autowired
  private StudentService studentService;

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
  
  @After
  public void clearDatabase() {
    bookingRepository.deleteAll();
    tutoringSessionRepository.deleteAll();
    tutorRepository.deleteAll();
    roomRepository.deleteAll();
    timeSlotRepository.deleteAll();
    managerRepository.deleteAll();
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
      tutoringSessionService.createTutoringSession(sessionDate, tutor, room, booking, timeSlot);
    }
    catch (IllegalArgumentException e) {
      fail();
    }

    List<TutoringSession> allTutoringSessions = tutoringSessionService.getAllTutoringSessions();

    assertEquals(1, allTutoringSessions.size());
    assertEquals(sessionDate, allTutoringSessions.get(0).getSessionDate());
  }

//  @Test
//  public void testGetTutoringSession() {
//    assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());
//
//    Date sessionDate = Date.valueOf("2019-10-14");
//    TutoringSession tutoringSession = tutoringSessionService.createTutoringSession(sessionDate);
//    List<TutoringSession> allTutoringSessions = null;
//    try {
//      allTutoringSessions = tutoringSessionService.getAllTutoringSessions();
//    }
//    catch (IllegalArgumentException e) {
//      fail();
//    }
//
//    assertEquals(tutoringSession.getId(), allTutoringSessions.get(0).getId());
//  }

}
