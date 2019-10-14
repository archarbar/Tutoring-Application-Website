package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.BookingRepository; 
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.TimeSlot;

@Service
public class BookingService {

  @Autowired
  BookingRepository bookingRepository;

  @Transactional
  public Booking createBooking(String tutorEmail, String studentEmail, Date specificDate, TimeSlot timeSlot, Course course) {
    if (tutorEmail == null) {
      throw new IllegalArgumentException("A tutor email needs to be specified!");
    }
    if (studentEmail == null) {
      throw new IllegalArgumentException("A student email needs to be specified!");
    }
    if (specificDate == null) {
      throw new IllegalArgumentException("A date needs to be specified!");
    }
    if (timeSlot == null) {
      throw new IllegalArgumentException("A time slot needs to be specified!");
    }
    if (course == null) {
      throw new IllegalArgumentException("A course needs to be specified!");
    }
    Booking booking = new Booking();
    booking.setTutorEmail(tutorEmail);
    booking.setSpecificDate(specificDate);
    bookingRepository.save(booking);
    return booking;
  }

  @Transactional
  public Booking getBookingById(Integer bookingId) {
    Booking booking = bookingRepository.findBookingById(bookingId);
    return booking;
  }

  @Transactional
  public List<Booking> getBookingBySpecificDate(Date specificDate) {
    List<Booking> bookingsBySpecificDate = new ArrayList<>();
    for (Booking booking : bookingRepository.findBookingBySpecificDate(specificDate)) {
      bookingsBySpecificDate.add(booking);
    }
    return bookingsBySpecificDate;
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
