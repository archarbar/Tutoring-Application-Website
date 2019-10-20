package ca.mcgill.ecse321.tutor.dao;

import ca.mcgill.ecse321.tutor.model.Booking;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.sql.Date;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "bookings", path = "bookings")
public interface BookingRepository extends CrudRepository<Booking, Integer>{

	Booking findBookingById(Integer bookingId);
	
	List<Booking> findBookingBySpecificDate(Date specificDate);

	List<Booking> findBookingByTutorEmail (String tutorEmail);
	
}
