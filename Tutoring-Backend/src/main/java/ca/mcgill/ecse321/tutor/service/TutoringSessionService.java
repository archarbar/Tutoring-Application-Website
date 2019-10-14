package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

@Service
public class TutoringSessionService {

	@Autowired
	TutoringSessionRepository tutoringSessionRepository;

	@Transactional
	public TutoringSession createTutoringSession(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("A date needs to be specified!");
		}
		TutoringSession tutoringSession = new TutoringSession();
		tutoringSession.setDate(date);
		tutoringSessionRepository.save(tutoringSession);
		return tutoringSession;
	}

	@Transactional
	public TutoringSession getTutoringSession(Integer tutoringSessionId) {
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
