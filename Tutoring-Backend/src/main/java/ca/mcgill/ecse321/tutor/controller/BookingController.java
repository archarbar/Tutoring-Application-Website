package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.BookingDto;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BookingController {
    @Autowired
    private BookingService service;

    /*  /**
     * Method to create booking through RESTful service call
     *
     * @param book
     * @return
     */

    /* ***Tutor shouldn't be able to create a booking***
      @PostMapping("/booking")
        public BookingDto createBooking (@RequestBody BookingDto book) throws IllegalArgumentException {
              Booking booking = service.createBooking(book.getTutorEmail(), (Set<Student>) book.getStudent(),
              book.getSpecificDate(), book.getTimeSlot(), book.getCourse());
              return convertToDto(booking);
        }*/


    @GetMapping(value = "/booking{bookingId}")
    public BookingDto getBookingById(@PathVariable int bookingId) {
        return convertToDto(service.getBookingById(bookingId));
    }

    @GetMapping("/bookings{date}")
    public List<BookingDto> getBookingByDate(@PathVariable Date date) {
        List<BookingDto> bookingsByDate = new ArrayList<>();
        for (Booking booking : service.getBookingBySpecificDate(date)) {
            bookingsByDate.add(convertToDto(booking));
        }
        return bookingsByDate;
    }

    @GetMapping("/booking/allBookings")
    public List<BookingDto> getAllTutorBookings(@PathVariable String tutorEmail) {
        List<BookingDto> bookingsByTutorEmail = new ArrayList<>();
        for (Booking booking : service.getBookingByTutorEmail(tutorEmail)) {
            bookingsByTutorEmail.add(convertToDto(booking));
        }
        return bookingsByTutorEmail;
    }

    private BookingDto convertToDto(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("There is no booking!");
        }
        return new BookingDto(booking.getId(), booking.getTutorEmail(), booking.getSpecificDate(),
                booking.getTimeSlot(), (Student) booking.getStudent(), booking.getCourse());
    }


}
