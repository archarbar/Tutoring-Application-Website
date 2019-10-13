package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;

@Service
public class TimeSlotService {

	@Autowired
	TimeSlotRepository timeSlotRepository;

	@Transactional
	public TimeSlot createTimeSlot(Integer timeSlotId, Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek) {
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setTimeSlotId(timeSlotId);
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setDayOfTheWeek(dayOfTheWeek);
		timeSlotRepository.save(timeSlot);
		return timeSlot;
	}

	@Transactional
	public TimeSlot getTimeSlot(Integer timeSlotId) {
		TimeSlot timeSlot = timeSlotRepository.findTimeSlotById(timeSlotId);
		return timeSlot;
	}

}
