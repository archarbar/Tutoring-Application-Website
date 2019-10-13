package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.Tutor;

@Service
public class TutoringSessionService {

	@Autowired
	TutoringSessionRepository tutoringSessionRepository;

	@Transactional
	public TutoringSession createTutoringSession(Integer tutoringSessionId, Date date, TimeSlot timeSlot,
			Tutor tutor, Room room, Booking booking) {
		TutoringSession tutoringSession = new TutoringSession();
		tutoringSession.setTutoringSessionId(tutoringSessionId);
		tutoringSession.setDate(date);
		tutoringSession.setTimeSlot(timeSlot);
		tutoringSession.setTutor(tutor);
		tutoringSession.setRoom(room);
		tutoringSession.setBooking(booking);
		tutoringSessionRepository.save(tutoringSession);
		return tutoringSession;
	}

	@Transactional
	public TutoringSession getTutoringSession(Integer tutoringSessionId) {
		TutoringSession tutoringSession = tutoringSessionRepository.findTutoringSessionById(tutoringSessionId);
		return tutoringSession;
	}

}
