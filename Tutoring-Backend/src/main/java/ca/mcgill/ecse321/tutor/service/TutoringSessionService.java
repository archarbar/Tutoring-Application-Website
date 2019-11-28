package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
import ca.mcgill.ecse321.tutor.dao.NotificationRepository;
import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.model.TimeSlot;

@Service
public class TutoringSessionService {

	@Autowired
	TutoringSessionRepository tutoringSessionRepository;

	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	NotificationRepository notificationRepository;
	@Autowired
	NotificationService notificationService;
	
	@Transactional
	public TutoringSession createTutoringSession(Date sessionDate, Tutor tutor, Room room,
			Booking booking, TimeSlot timeSlot) {
		String error = "";
		if (sessionDate == null) {
			error = error + "A date needs to be specified! ";
		}
		if (tutor == null) {
			error = error + "A tutor needs to be specified! ";
		}
		if (room == null) {
			error = error + "A room needs to be specified! ";
		}
		if (booking == null) {
			error = error + "A booking needs to be specified! ";
		}
		if (timeSlot == null) {
			error = error + "A timeSlot needs to be specified!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		TutoringSession tutoringSession = new TutoringSession();
		tutoringSession.setSessionDate(sessionDate);
		tutoringSession.setTutor(tutor);
		tutoringSession.setRoom(room);
		tutoringSession.setTimeSlot(timeSlot);
		booking.setTutoringSession(tutoringSession);
		
		List<Notification> notifications = notificationService.getAllNotifications();
		for(Notification notification: notifications) {
			System.out.println(notification.getBooking().getId());
			System.out.println(booking.getId());
			System.out.println(booking.getId() == notification.getBooking().getId());
			if( booking.getId() == notification.getBooking().getId()) {
				System.out.println("DELETE NOTIFICATION");
				notificationRepository.delete(notification);
			}
		}
		tutoringSessionRepository.save(tutoringSession);
		bookingRepository.save(booking);
//		tutoringSession.setBooking(booking);

		return tutoringSession;
	}

	@Transactional
	public TutoringSession getTutoringSessionById(Integer tutoringSessionId) {
		if (tutoringSessionId == null) {
			throw new IllegalArgumentException("A tutoring session ID needs to be specified!");
		}
		TutoringSession tutoringSession = tutoringSessionRepository.findTutoringSessionById(tutoringSessionId);
		return tutoringSession;
	}

	@Transactional
	public List<TutoringSession> getTutoringSessionByTutor(Tutor tutor) {
		if (tutor == null) {
			throw new IllegalArgumentException("A tutor needs to be specified!");
		}
		List<TutoringSession> tutoringSession = tutoringSessionRepository.findTutoringSessionByTutor(tutor);
		return tutoringSession;
	}

	@Transactional
	public List<TutoringSession> getAllTutoringSessions(){
		return toList(tutoringSessionRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
