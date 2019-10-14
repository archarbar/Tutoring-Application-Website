package ca.mcgill.ecse321.tutor.dao;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.model.Notification;

@Repository
public class TutoringApplicationRepository {
	
	@Autowired
	EntityManager entityManager;
	
	@Transactional
	public Notification createNotification(Tutor tutor, Booking booking) {
		Notification notification = new Notification();
		notification.setTutor(tutor);
		notification.setBooking(booking);
		entityManager.persist(notification);
		return notification;
	}

	@Transactional
	public Notification getNotification(Integer notificationId) {
		Notification notification = entityManager.find(Notification.class, notificationId);
		return notification;
	}

}
