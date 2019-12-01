package ca.mcgill.ecse321.tutor.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.BookingDto;
import ca.mcgill.ecse321.tutor.dto.TutoringSessionDto;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.BookingService;

/**
 * This class provides controller methods for the booking class
 * 
 * @author Tony Ou
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class BookingController {
	@Autowired
	private BookingService service;

	TimeSlotController timeSlotController = new TimeSlotController();
	TutoringSessionController tutoringSessionController = new TutoringSessionController();

	/**
	 * API call to retrieve a booking using its id
	 * 
	 * @param bookingId The ID of the booking
	 * @return A booking DTO
	 */

	@GetMapping(value = "/booking/{bookingId}")
	public BookingDto getBookingById(@PathVariable("bookingId") String bookingId) {
		return convertToDto(service.getBookingById(Integer.parseInt(bookingId)));
	}

	/**
	 * API call to retrieve all bookings
	 * 
	 * @return A booking DTO
	 */

	@GetMapping(value = {"/bookings", "/bookings/"})
	public List<BookingDto> getAllBookings() {
		ArrayList<BookingDto> bDtos = new ArrayList<BookingDto>();
		for(Booking booking: service.getAllBookings()) {
			bDtos.add(convertToDto(booking));
		}
		return bDtos;
	}

	/**
	 * API call to retrieve all bookings from a specific tutor
	 * 
	 * @param tutorId The ID of the tutor
	 * @return A booking DTO
	 */

	@GetMapping(value = {"/booking/tutor/{tutorId}", "/booking/tutor/{tutorId}/"})
	public List<BookingDto> getAllBookingsByTutor(@PathVariable("tutorId") String tutorId) {
		ArrayList<BookingDto> bDtos = new ArrayList<BookingDto>();
		ArrayList<Booking> bookings = service.getBookingByTutorId(tutorId);
		for(Booking booking: bookings) {
			bDtos.add(convertToDto(booking));
		}
		return bDtos;
	}

	/**
	 * API call to retrieve all bookings from a specific date
	 * 
	 * @param date A Java date object
	 * @return A booking DTO
	 */

	@GetMapping("/booking/date/{date}")
	public List<BookingDto> getBookingByDate(@PathVariable("date") String date) {
		Date specificDate = Date.valueOf(date);
		ArrayList<BookingDto> bookingsByDate = new ArrayList<>();
		for (Booking booking : service.getBookingBySpecificDate(specificDate)) {
			bookingsByDate.add(convertToDto(booking));
		}
		return bookingsByDate;
	}

	/**
	 * API call to decline a specific booking
	 * 
	 * @param bookingId The ID of the booking
	 */

	@DeleteMapping("/booking/decline/{bookingId}")
	public void declineBooking(@PathVariable("bookingId") String bookingId) {
		service.declineBookingById(Integer.parseInt(bookingId));
	}

	/**
	 * API call to accept a specific booking
	 * 
	 * @param bookingId The ID of the booking
	 * @return A booking DTO
	 */

	@DeleteMapping("/booking/accept/{bookingId}")
	public TutoringSessionDto acceptBooking(@PathVariable("bookingId") String bookingId) {
		TutoringSession tutoringSession = service.acceptBookingById(Integer.parseInt(bookingId));
		if(tutoringSession == null) {
			throw new IllegalArgumentException("There are no available rooms!");
		}
		return tutoringSessionController.convertToDto(tutoringSession);
	}

	/**
	 * Method to convert a booking object into a booking DTO
	 * 
	 * @param booking A booking object
	 * @return A booking DTO instance
	 */

	public BookingDto convertToDto(Booking booking) {
		BookingDto bookingDTO = null;

		if (booking == null) {
			throw new IllegalArgumentException("There is no booking!");
		}
		for (Student student : booking.getStudent()) {
			bookingDTO = new BookingDto(booking.getId(), booking.getTutorEmail(), booking.getSpecificDate(),
					timeSlotController.convertToDto(booking.getTimeSlot()),
					student.getFirstName()+ " " +student.getLastName(), 
					booking.getCourse().getCourseName());
		}
		return bookingDTO;

	}


}
