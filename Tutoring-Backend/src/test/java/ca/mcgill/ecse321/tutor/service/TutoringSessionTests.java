package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.TutoringSessionService;



@RunWith(SpringRunner.class)
@SpringBootTest
public class TutoringSessionTests {

	@Autowired
	private TutoringSessionService tutoringSessionService;
	
	@Autowired
	private TutoringSessionRepository tutoringSessionRepository;
	
//	@Test
//	public void testCreateTutoringSession() {
//
//		assertEquals(0, tutoringSessionService.getAllTutoringSessions().size());
//		Integer tutoringSessionId
//		Date date
//		TimeSlot timeSlot
//		Tutor tutor
//		Room room
//		Booking booking = new Booking();
//		try {
//			tutoringSessionService.createTutoringSession(tutoringSessionId, date, timeSlot, tutor, room, booking);
//		} catch (IllegalArgumentException e) {
//			fail();
//		}
//		
//		List<Student> allStudents = studentService.getAllStudents();
//		
//		assertEquals(1, allStudents.size());
//		assertEquals(firstName, allStudents.get(0).getFirstName());
//		assertEquals(lastName, allStudents.get(0).getLastName());
//		assertEquals(email, allStudents.get(0).getEmail());	
//		
//	}

//	@Test
//	public void testGetTutoringSession() {
//		Booking booking = new Booking();
//		
//		TutoringSession tutoringSession = tutoringSessionService.getTutoringSessionByBooking(booking);
//		
//		assertEquals(booking, tutoringSession.getBooking());
//	}
	
}
