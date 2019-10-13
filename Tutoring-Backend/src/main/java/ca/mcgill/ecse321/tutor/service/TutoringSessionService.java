package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

public class TutoringSessionService {
	
	@Autowired
	TutoringSessionRepository tutoringSessionRepository;
	
	@Transactional
	public TutoringSession getTutoringSessionById(Integer tutoringSessionId) {
		TutoringSession tutoringSession = tutoringSessionRepository.findTutoringSessionById(tutoringSessionId);
		return tutoringSession;
	}

	@Transactional
	public TutoringSession createTutoringSession(Booking booking, int tutoringSessionId,Date date, Room room, Tutor tutor, TimeSlot timeSlot) {
		TutoringSession tutoringSession = new TutoringSession();
		tutoringSession.setBooking(booking);
		tutoringSession.setDate(date);
		tutoringSession.setRoom(room);
		tutoringSession.setTutor(tutor);
		tutoringSession.setTimeSlot(timeSlot);
		tutoringSession.setTutoringSessionId(tutoringSessionId);
		tutoringSessionRepository.save(tutoringSession);
		return tutoringSession;
	}
	
}
