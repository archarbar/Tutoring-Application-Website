package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.Notification;

public interface BookingRepository extends CrudRepository<Booking, Integer>{

	Booking findBookingById(Integer bookingId);
	
	List<Booking> findByStudent(Student student);
	
	Booking findByNotification(Notification notification);
	
	
}
