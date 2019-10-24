package ca.mcgill.ecse321.tutor.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;

@Service
public class TimeSlotService {

	@Autowired
	TimeSlotRepository timeSlotRepository;

	@Transactional
	public TimeSlot createTimeSlot(Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek) {
		String error = "";
		if (startTime == null) {
			error = error + "A start time needs to be specified! ";
		}
		if (endTime == null) {
			error = error + "A end time needs to be specified! ";
		}
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			error = error + "The end time cannot be before the start time";
		}
		if (dayOfTheWeek == null) {
			error = error + "A day of the week needs to be specified!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(startTime);
		timeSlot.setEndTime(endTime);
		timeSlot.setDayOfTheWeek(dayOfTheWeek);
		timeSlotRepository.save(timeSlot);
		return timeSlot;
	}

	@Transactional
	public TimeSlot getTimeSlotById(Integer timeSlotId) {
		if (timeSlotId == null) {
			throw new IllegalArgumentException("A timeSlot ID needs to be specified!");
		}
		TimeSlot timeSlot = timeSlotRepository.findTimeSlotById(timeSlotId);
		return timeSlot;
	}

	@Transactional
	public TimeSlot getTimeSlotByBooking(Booking booking) {
		if (booking == null) {
			throw new IllegalArgumentException("A booking needs to be specified!");
		}
		TimeSlot timeSlot = timeSlotRepository.findByBooking(booking);
		return timeSlot;
	}

	@Transactional
	public TimeSlot getTimeSlotByTutoringSession(TutoringSession tutoringSession) {
		if (tutoringSession == null) {
			throw new IllegalArgumentException("A tutoring session needs to be specified!");
		}
		TimeSlot timeSlot = timeSlotRepository.findByTutoringSession(tutoringSession);
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
