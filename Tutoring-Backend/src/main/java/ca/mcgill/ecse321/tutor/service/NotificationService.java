package ca.mcgill.ecse321.tutor.service;

import ca.mcgill.ecse321.tutor.model.Notification;
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
	public Notification createNotification() {
		Notification notification = new Notification();
		notificationRepository.save(notification);
		return notification;
	}

	@Transactional
	public Notification getNotification(Integer notificationId) {
		Notification notification = notificationRepository.findNotificationById(notificationId);
		return notification;
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
