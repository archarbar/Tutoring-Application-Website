package ca.mcgill.ecse321.tutor.service;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.dao.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

	@Autowired
	NotificationRepository notificationRepository;

	@Transactional
	public Notification createNotification(Booking booking, Tutor tutor) {
		if (booking == null) {
			throw new IllegalArgumentException("A booking needs to be specified!");
		}

		Notification notification = new Notification();
		notification.setBooking(booking);
		notification.setTutor(tutor);
		notificationRepository.save(notification);
		return notification;
	}

	@Transactional
	public Notification getNotification(Integer notificationId) {
		return notificationRepository.findNotificationById(notificationId);
	}

	@Transactional
	public List<Notification> getNotificationsByTutor(Tutor tutor){
		return toList(notificationRepository.findNotificationByTutor(tutor));
	}

	@Transactional
	public List<Notification> getAllNotifications(){
		return toList(notificationRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
