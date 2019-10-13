package ca.mcgill.ecse321.tutor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TutorRepository;
import ca.mcgill.ecse321.tutor.model.Tutor;

@Service
public class TutorService {

	@Autowired
	TutorRepository tutorRepository;

	@Transactional
	public Tutor createTutor(Integer tutorId, Double hourlyRate, Boolean isApproved) {
		Tutor tutor = new Tutor();
		tutor.setTutorId(tutorId);
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

}
