package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Notification;

import java.sql.Date;
import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Integer>{

	Booking findBookingById(Integer bookingId);
	
	List<Booking> findBookingByDate(Date date);
	
}
