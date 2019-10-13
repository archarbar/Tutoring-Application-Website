package ca.mcgill.ecse321.tutor.dao;


import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.Booking;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	Notification findNotificationById(Integer Id);
	Notification findByTutor(Tutor tutor);
	Notification findByBooking(Booking booking);
}
