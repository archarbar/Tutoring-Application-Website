package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.model.TimeSlot;

@Service
public class TutoringSessionService {

	@Autowired
	TutoringSessionRepository tutoringSessionRepository;

	@Transactional
	public TutoringSession createTutoringSession(Date sessionDate, Tutor tutor, Room room,
	    Booking booking, TimeSlot timeSlot) {
		if (sessionDate == null) {
			throw new IllegalArgumentException("A date needs to be specified!");
		}
		TutoringSession tutoringSession = new TutoringSession();
		tutoringSession.setSessionDate(sessionDate);
		tutoringSession.setTutor(tutor);
		tutoringSession.setRoom(room);
		tutoringSession.setTimeSlot(timeSlot);
		tutoringSession.setBooking(booking);
		tutoringSessionRepository.save(tutoringSession);
		return tutoringSession;
	}

	@Transactional
	public TutoringSession getTutoringSessionById(Integer tutoringSessionId) {
		TutoringSession tutoringSession = tutoringSessionRepository.findTutoringSessionById(tutoringSessionId);
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
