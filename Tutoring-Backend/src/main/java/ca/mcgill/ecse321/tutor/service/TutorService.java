package ca.mcgill.ecse321.tutor.service;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutor.dao.TutorRepository;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;

@Service
public class TutorService {

	@Autowired
	TutorRepository tutorRepository;
	@Autowired
	TimeSlotRepository timeSlotRepository;

	@Transactional
	public Tutor createTutor(String firstName, String lastName, String email, String password) {
		String error = "";
		if (firstName == null || firstName.trim().length() == 0) {
			error = error + "A first name needs to be specified! ";
		}
		if (lastName == null || lastName.trim().length() == 0) {
			error = error + "A last name needs to be specified! ";
		}
		if (email == null || email.trim().length() == 0) {
			error = error + "An email needs to be specified! ";
		}
		if (password == null || password.trim().length() == 0) {
			error = error + "A password needs to be specified! ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Integer hourlyRate = 10;
		boolean isApproved = false;
		Tutor tutor = new Tutor();
		tutor.setFirstName(firstName);
		tutor.setLastName(lastName);
		tutor.setEmail(email);
		tutor.setPassword(password);
		tutor.setHourlyRate(hourlyRate);
		tutor.setIsApproved(isApproved);
		tutorRepository.save(tutor);
		return tutor;
	}

	@Transactional
	public Tutor getTutorById(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("A tutor ID needs to be specified!");
		}
		Tutor tutor = tutorRepository.findTutorById(id);
		return tutor;
	}

	@Transactional
	public Tutor getTutorByEmail(String tutorEmail) {
		if (tutorEmail == null || tutorEmail.trim().length() == 0) {
			throw new IllegalArgumentException("A tutor email needs to be specified!");
		}
		Tutor tutor = tutorRepository.findTutorByEmail(tutorEmail);
		return tutor;
	}

	@Transactional
	public List<Tutor> getAllTutors(){
		return toList(tutorRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	@Transactional
	public void addTimeSlotForTutor(Tutor tutor, String startTime, String endTime, String weekDay) {
		String error = "";
		if (tutor == null) {
			error = error + "A tutor needs to be specified! ";
		}
		if (startTime == null || startTime.trim().length() == 0) {
			error = error + "A start time needs to be specified! ";
		}
		if (endTime == null || endTime.trim().length() == 0) {
			error = error + "A end time needs to be specified! ";
		}
		if (weekDay == null || weekDay.trim().length() == 0) {
			error = error + "A weekday needs to be specified!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		TimeSlot timeSlot = new TimeSlot();
		timeSlot.setStartTime(Time.valueOf(startTime));
		timeSlot.setEndTime(Time.valueOf(endTime));
		timeSlot.setDayOfTheWeek(DayOfTheWeek.valueOf(weekDay));
		timeSlotRepository.save(timeSlot);
		Set<TimeSlot> timeslots = tutor.getTimeSlot();
		timeslots.add(timeSlot);
		tutor.setTimeSlot(timeslots);
	}

	@Transactional
	public void removeTimeSlotForTutor(Tutor tutor, Integer timeSlotId) {
		String error = "";
		if (tutor == null) {
			error = error + "A tutor needs to be specified! ";
		}
		if (timeSlotId == null) {
			error = error + "A timeSlot ID needs to be specified!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Set<TimeSlot> timeslots = tutor.getTimeSlot();
		for (TimeSlot timeSlot : timeslots) {
			if (timeSlot.getId() == timeSlotId) {
				timeslots.remove(timeSlot);
				break;
			}
		}
	}

}