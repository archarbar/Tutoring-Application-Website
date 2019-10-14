package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.model.Tutor;

@Service
public class TimeSlotService {

	@Autowired
	TimeSlotRepository timeSlotRepository;

	@Transactional
	public TimeSlot createTimeSlot(Integer timeSlotId, Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek,
			Set<TutoringSession> tutoringSessions, Tutor tutor) {
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setTimeSlotId(timeSlotId);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setDayOfTheWeek(dayOfTheWeek);
		timeSlot.setTutoringSession(tutoringSessions);
		timeSlot.setTutor(tutor);
		timeSlotRepository.save(timeSlot);
		return timeSlot;
	}

	@Transactional
	public TimeSlot getTimeSlot(Integer timeSlotId) {
		TimeSlot timeSlot = timeSlotRepository.findTimeSlotById(timeSlotId);
		return timeSlot;
	}

	@Transactional
	public List<TimeSlot> getAllTimeSlots(){
		return toList(timeSlotRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
