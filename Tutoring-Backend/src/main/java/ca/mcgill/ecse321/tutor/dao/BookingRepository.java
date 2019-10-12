package main.java.ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import main.java.ca.mcgill.ecse321.tutor.model.Booking;
import main.java.ca.mcgill.ecse321.tutor.model.Student;
import main.java.ca.mcgill.ecse321.tutor.model.Notification;

public interface BookingRepository extends CrudRepository<Booking, Integer>{

	Booking findBookingById(Integer bookingId);
	
	List<Booking> findByStudent(Student student);
	
	Booking findByNotification(Notification notification);
	
	
}
