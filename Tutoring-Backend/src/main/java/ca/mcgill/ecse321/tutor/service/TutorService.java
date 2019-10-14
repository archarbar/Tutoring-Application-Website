package ca.mcgill.ecse321.tutor.service;

import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TutorRepository;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Rating;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Course;

@Service
public class TutorService {

	@Autowired
	TutorRepository tutorRepository;

	@Transactional
	public Tutor createTutor(Double hourlyRate, Boolean isApproved) {
		if (hourlyRate == null || hourlyRate <= 0) {
			throw new IllegalArgumentException("A valid hourly rate needs to be specified!");
		}
		Tutor tutor = new Tutor();
		tutor.setHourlyRate(hourlyRate);
		tutor.setIsApproved(isApproved);
		tutorRepository.save(tutor);
		return tutor;
	}

	@Transactional
	public Tutor getTutor(Integer tutorId) {
		Tutor tutor = tutorRepository.findTutorById(tutorId);
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

}
