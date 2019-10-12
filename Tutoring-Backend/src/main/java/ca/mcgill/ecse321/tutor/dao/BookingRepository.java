package main.java.ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import main.java.ca.mcgill.ecse321.tutor.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Integer>{

	Booking findBookingById(Integer bookingId);
}
