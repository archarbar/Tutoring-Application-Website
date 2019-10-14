package ca.mcgill.ecse321.tutor.dao;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.CourseLevel;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Tutor;

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
	@Column(name = "COURSE NAME")
	private String name;
	@Column(name = "COURSE LEVEL")
	private CourseLevel level;
	@Id
	@Column(name = "COURSE ID")
	private Integer courseId;
	@Column(name  = "BOOKINGS")
	private Set<Booking> booking;
	@Column(name = "TUTORS")
	private Set<Tutor> tutor;
	
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
