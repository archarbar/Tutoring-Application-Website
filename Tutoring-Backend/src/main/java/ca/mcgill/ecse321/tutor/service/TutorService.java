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
	public Tutor createTutor(Double hourlyRate, Boolean isApproved, Manager manager, Set<TutoringSession> tutoringSessions,
			Set<TimeSlot> timeSlots, Set<Rating> ratings, Set<Notification> notifications, Set<Course> courses) {
		Tutor tutor = new Tutor();
		tutor.setHourlyRate(hourlyRate);
		tutor.setIsApproved(isApproved);
		tutor.setManager(manager);
		tutor.setTutoringSession(tutoringSessions);
		tutor.setTimeSlot(timeSlots);
		tutor.setRating(ratings);
		tutor.setNotification(notifications);
		tutor.setCourse(courses);
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
