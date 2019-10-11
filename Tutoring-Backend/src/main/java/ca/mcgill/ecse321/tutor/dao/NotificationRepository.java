package main.java.ca.mcgill.ecse321.tutor.dao;

import main.java.ca.mcgill.ecse321.tutor.model.Notification;
import main.java.ca.mcgill.ecse321.tutor.model.Tutor;
import main.java.ca.mcgill.ecse321.tutor.model.Booking;
import org.springframework.data.repository.CrudRepository;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	Notification findNotificationById(Integer Id);
	Notification findByTutor(Tutor tutor);
	Notification findByBooking(Booking booking);
}
