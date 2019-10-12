package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Notification;

public interface NotificationRepository extends CrudRepository<Notification, Integer> {
	Notification findNotificationById(Integer Id);
	Notification findByTutor(Tutor tutor);
	Notification findByBooking(Booking booking);
}
