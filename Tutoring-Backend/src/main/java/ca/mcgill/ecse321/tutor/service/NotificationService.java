package ca.mcgill.ecse321.tutor.service;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.dao.NotificationRepository;
import ca.mcgill.ecse321.tutor.dao.TutorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationService {

	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	TutorRepository tutorRepository;

	@Transactional
	public Notification createNotification(Booking booking, Tutor tutor) {
		String error = "";
		if (booking == null) {
			error = error + "A booking needs to be specified! ";
		}
		if (tutor == null) {
			error = error + "A tutor needs to be specified!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Notification notification = new Notification();
		notification.setBooking(booking);
		Set<Notification> notifications = tutor.getNotification();
		notifications.add(notification);
		tutor.setNotification(notifications);
		tutorRepository.save(tutor);
		notification.setTutor(tutor);
		notificationRepository.save(notification);
		return notification;
	}

	@Transactional
	public Notification getNotification(Integer notificationId) {
		if (notificationId == null) {
			throw new IllegalArgumentException("A notification ID needs to be specified!");
		}
		return notificationRepository.findNotificationById(notificationId);
	}

	@Transactional
	public List<Notification> getNotificationsByTutor(Tutor tutor){
		if (tutor == null) {
			throw new IllegalArgumentException("A tutor needs to be specified!");
		}
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
