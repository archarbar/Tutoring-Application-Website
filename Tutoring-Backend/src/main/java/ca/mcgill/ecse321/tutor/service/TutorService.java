package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.util.List;

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
	public Tutor createTutor(String firstName, String lastName, String email, String password) {
		if (firstName == null || lastName == null || email == null || password == null) {
			throw new IllegalArgumentException("Tutor name, email and password need to be specified!");
		}
		Tutor tutor = new Tutor();
		tutor.setFirstName(firstName);
		tutor.setLastName(lastName);
		tutor.setEmail(email);
		tutor.setPassword(password);
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