package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.TutorRepository;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Tutor;

@Service
public class TutorService {

	@Autowired
	TutorRepository tutorRepository;

	@Transactional
	public Tutor createTutor(String firstName, String lastName, String email, String password, Manager manager) {
		if (firstName == null || lastName == null || email == null || password == null || firstName.trim().length() == 0
			|| lastName.trim().length() == 0 || email.trim().length() == 0 || password.trim().length() == 0) {
			throw new IllegalArgumentException("Tutor name, email and password need to be specified!");
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
		tutor.setManager(manager);
		tutorRepository.save(tutor);
		return tutor;
	}

	@Transactional
	public Tutor getTutor(Integer id) {
		Tutor tutor = tutorRepository.findTutorById(id);
		return tutor;
	}
	
	@Transactional
    public Tutor getTutorByEmail(String tutorEmail) {
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

}