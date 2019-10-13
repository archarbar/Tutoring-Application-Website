package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

@Service
public class TutoringSessionService {

	@Autowired
	TutoringSessionRepository tutoringSessionRepository;

	@Transactional
	public TutoringSession createTutoringSession(Integer tutoringSessionId, Date date) {
		TutoringSession tutoringSession = new TutoringSession();
		tutoringSession.setTutoringSessionId(tutoringSessionId);
		tutoringSession.setDate(date);
		tutoringSessionRepository.save(tutoringSession);
		return tutoringSession;
	}

	@Transactional
	public TutoringSession getTutoringSession(Integer tutoringSessionId) {
		TutoringSession tutoringSession = tutoringSessionRepository.findTutoringSessionById(tutoringSessionId);
		return tutoringSession;
	}

}
