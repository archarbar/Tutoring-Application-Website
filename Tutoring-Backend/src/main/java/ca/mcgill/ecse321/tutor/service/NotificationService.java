package ca.mcgill.ecse321.tutor.service;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.dao.NotificationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@Transactional
	public Notification createNotification(Integer notificationId, Tutor tutor, Booking booking) {
		Notification notification = new Notification();
		notification.setNotificationId(notificationId);
		notification.setTutor(tutor);
		notification.setBooking(booking);
		return notification;
	}
	
	@Transactional
	public Notification getNotification(Integer notificationId) {
		Notification notification = notificationRepository.findNotificationById(notificationId);
		return notification;
	}


}
