package ca.mcgill.ecse321.tutor.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.BookingDto;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.Tutor;
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


    @GetMapping(value = "/bookings/{bookingId}")
    public BookingDto getBookingById(@PathVariable("bookingId") String bookingId) {
        return convertToDto(service.getBookingById(Integer.parseInt(bookingId)));
    }    

    @GetMapping(value = {"/bookings", "/bookings/"})
    public List<BookingDto> getAllBookings() {
    	List<BookingDto> bDtos = new ArrayList<BookingDto>();
    	for(Booking booking: service.getAllBookings()) {
    		bDtos.add(convertToDto(booking));
    	}
    	return bDtos;
    }

    @GetMapping("/bookings/date/{date}")
    public List<BookingDto> getBookingByDate(@PathVariable("date") String date) {
    	Date specificDate = Date.valueOf(date);
        List<BookingDto> bookingsByDate = new ArrayList<>();
        for (Booking booking : service.getBookingBySpecificDate(specificDate)) {
            bookingsByDate.add(convertToDto(booking));
        }
        return bookingsByDate;
    }
    
    @PostMapping("/bookings/new")
    public void createNotificationFromBooking(@RequestParam("tutorId") String tutorId, @RequestParam("bookingId") String bookingId) {
    	// Upon receiving a new booking, the tutor point of view creates a new notification.
    	Booking booking = service.getBookingById(Integer.parseInt(bookingId));
    	Tutor tutor = tutorService.getTutorById(Integer.parseInt(tutorId));
    	notificationService.createNotification(booking, tutor);    	
    }
   

    private BookingDto convertToDto(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("There is no booking!");
        }
        return new BookingDto(booking.getId(), booking.getTutorEmail(), booking.getSpecificDate(),
                booking.getTimeSlot(), (Student) booking.getStudent(), booking.getCourse());
    }


}
