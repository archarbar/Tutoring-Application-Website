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
	public Tutor changeHourlyRate(Integer tutorId, double hourlyRate) {
		Tutor tutor = getTutor(tutorId);
		if (tutor.getIsApproved() == true) {
			tutor.setHourlyRate(hourlyRate);
		}
		return tutor;
	}

	@Transactional
	public Tutor getTutor(Integer id) {
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

}