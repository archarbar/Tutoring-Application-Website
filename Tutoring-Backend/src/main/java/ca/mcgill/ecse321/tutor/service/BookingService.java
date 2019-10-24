package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.BookingRepository; 
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Transactional
	public Booking createBooking(String tutorEmail, Set<Student> student, Date specificDate, 
			TimeSlot timeSlot, Course course) {
		String error = "";
		if (tutorEmail == null || tutorEmail.trim().length() == 0) {
			error = error + "A tutor email needs to be specified! ";
		}
		if (student == null) {
			error = error + "A student needs to be specified! ";
		}
		if (specificDate == null) {
			error = error + "A date needs to be specified! ";
		}
		if (timeSlot == null) {
			error = error + "A time slot needs to be specified! ";
		}
		if (course == null) {
			error = error + "A course needs to be specified!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Booking booking = new Booking();
		booking.setTutorEmail(tutorEmail);
		booking.setSpecificDate(specificDate);
		booking.setTimeSlot(timeSlot);
		booking.setStudent(student);
		booking.setCourse(course);
		bookingRepository.save(booking);
		return booking;
	}

	@Transactional
	public Booking getBookingById(Integer bookingId) {
		if (bookingId == null) {
			throw new IllegalArgumentException("A booking ID needs to be specified!");
		}
		Booking booking = bookingRepository.findBookingById(bookingId);
		return booking;
	}

	@Transactional
	public List<Booking> getBookingBySpecificDate(Date specificDate) {
		if (specificDate == null) {
			throw new IllegalArgumentException("A specific date needs to be specified!");
		}
		List<Booking> bookingsBySpecificDate = new ArrayList<>();
		for (Booking booking : bookingRepository.findBookingBySpecificDate(specificDate)) {
			bookingsBySpecificDate.add(booking);
		}
		return bookingsBySpecificDate;
	}

	@Transactional
	public List<Booking> getBookingByTutorEmail(String tutorEmail) {
		if (tutorEmail == null || tutorEmail.trim().length() == 0) {
			throw new IllegalArgumentException("A tutor email needs to be specified!");
		}
		List<Booking> bookingsByTutorEmail = new ArrayList<>();
		for (Booking booking : bookingRepository.findBookingByTutorEmail(tutorEmail)) {
			bookingsByTutorEmail.add(booking);
		}
		return bookingsByTutorEmail;
	}

	@Transactional
	public List<Booking> getAllBookings(){
		return toList(bookingRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
