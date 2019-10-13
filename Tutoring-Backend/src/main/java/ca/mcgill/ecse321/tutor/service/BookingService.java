package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.BookingRepository; 
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Course;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Transactional
	public Booking createBooking(Integer bookingId, String tutorEmail, Date specificDate, TimeSlot timeSlot, TutoringSession tutoringSession,
			Set<Student> students, Notification notification, Course course) {
		Booking booking = new Booking();
		booking.setBookingId(bookingId);
		booking.setTutorEmail(tutorEmail);
		booking.setSpecificDate(specificDate);
		booking.setTimeSlot(timeSlot);
		booking.setTutoringSession(tutoringSession);
		booking.setStudent(students);
		booking.setNotification(notification);
		booking.setCourse(course);
		bookingRepository.save(booking);
		return booking;
	}

	@Transactional
	public Booking getBooking(Integer bookingId) {
		Booking booking = bookingRepository.findBookingById(bookingId);
		return booking;
	}

}
