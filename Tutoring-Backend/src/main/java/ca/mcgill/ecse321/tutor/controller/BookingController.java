package ca.mcgill.ecse321.tutor.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.BookingDto;
import ca.mcgill.ecse321.tutor.dto.TimeSlotDto;
import ca.mcgill.ecse321.tutor.dto.TutoringSessionDto;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.service.BookingService;
import ca.mcgill.ecse321.tutor.service.NotificationService;
import ca.mcgill.ecse321.tutor.service.TutorService;

@CrossOrigin(origins = "*")
@RestController
public class BookingController {
    @Autowired
    private BookingService service;
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private TutorService tutorService;
    
    TimeSlotController timeSlotController = new TimeSlotController();
    TutoringSessionController tutoringSessionController = new TutoringSessionController();

    @GetMapping(value = "/booking/{bookingId}")
    public BookingDto getBookingById(@PathVariable("bookingId") String bookingId) {
        return convertToDto(service.getBookingById(Integer.parseInt(bookingId)));
    }    

    @GetMapping(value = {"/bookings", "/bookings/"})
    public List<BookingDto> getAllBookings() {
    	ArrayList<BookingDto> bDtos = new ArrayList<BookingDto>();
    	for(Booking booking: service.getAllBookings()) {
    		bDtos.add(convertToDto(booking));
    	}
    	return bDtos;
    }
    
    @GetMapping(value = {"/booking/tutor/{tutorId}", "/booking/tutor/{tutorId}/"})
    public List<BookingDto> getAllBookingsByTutor(@PathVariable("tutorId") String tutorId) {
    	ArrayList<BookingDto> bDtos = new ArrayList<BookingDto>();
    	ArrayList<Booking> bookings = service.getBookingByTutorId(tutorId);
    	for(Booking booking: bookings) {
    		bDtos.add(convertToDto(booking));
    	}
    	return bDtos;
    }

    @GetMapping("/booking/date/{date}")
    public List<BookingDto> getBookingByDate(@PathVariable("date") String date) {
    	Date specificDate = Date.valueOf(date);
    	ArrayList<BookingDto> bookingsByDate = new ArrayList<>();
        for (Booking booking : service.getBookingBySpecificDate(specificDate)) {
            bookingsByDate.add(convertToDto(booking));
        }
        return bookingsByDate;
    }
    
    //PART 2 USE CASE 8
    @DeleteMapping("/booking/decline/{bookingId}")
    public void declineBooking(@PathVariable("bookingId") String bookingId) {
    	service.declineBookingById(Integer.parseInt(bookingId));
    }   
    
    @DeleteMapping("/booking/accept/{bookingId}")
    public TutoringSessionDto acceptBooking(@PathVariable("bookingId") String bookingId) {
    	TutoringSession tutoringSession = service.acceptBookingById(Integer.parseInt(bookingId));
    	if(tutoringSession == null) {
    		throw new IllegalArgumentException("There are no available rooms!");
    	}
    	return tutoringSessionController.convertToDto(tutoringSession);
    }    

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
