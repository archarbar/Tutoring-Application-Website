package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.BookingRepository; 
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Student;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Transactional
	public Booking createBooking(Integer bookingId, String tutorEmail, Date specificDate) {
		Booking booking = new Booking();
		booking.setBookingId(bookingId);
		booking.setTutorEmail(tutorEmail);
		booking.setSpecificDate(specificDate);
		bookingRepository.save(booking);
		return booking;
	}

	@Transactional
	public Booking getBooking(Integer bookingId) {
		Booking booking = bookingRepository.findBookingById(bookingId);
		return booking;
	}

}
