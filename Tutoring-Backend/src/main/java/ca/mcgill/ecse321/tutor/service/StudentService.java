package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.StudentRepository; 
import ca.mcgill.ecse321.tutor.model.Student;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Transactional
	public Student createStudent(String firstName, String lastName, String email) {
		String error = "";
		if (firstName == null || firstName.trim().length() == 0) {
			error = error + "A first name needs to be specified! ";
		}
		if (lastName == null || lastName.trim().length() == 0) {
			error = error + "A last name needs to be specified! ";
		}
		if (email == null || email.trim().length() == 0) {
			error = error + "An email needs to be specified!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		studentRepository.save(student);
		return student;
	}

	@Transactional
	public Student getStudentByFirstName(String firstName) {
		if (firstName == null || firstName.trim().length() == 0) {
			throw new IllegalArgumentException("A first name needs to be specified!");
		}
		Student student = studentRepository.findStudentByFirstName(firstName);
		return student;
	}

	@Transactional
	public Student getStudentByLastName(String lastName) {
		if (lastName == null || lastName.trim().length() == 0) {
			throw new IllegalArgumentException("A last name needs to be specified!");
		}
		Student student = studentRepository.findStudentByLastName(lastName);
		return student;
	}

	@Transactional
	public Student getStudentById(Integer studentId) {
		if (studentId == null) {
			throw new IllegalArgumentException("A student ID needs to be specified!");
		}
		Student student = studentRepository.findStudentById(studentId);
		return student;
	}

	@Transactional 
	public Student getStudentByEmail(String email) {
		if (email == null || email.trim().length() == 0) {
			throw new IllegalArgumentException("An email needs to be specified!");
		}
		Student student = studentRepository.findStudentByEmail(email);
		return student;
	}

	@Transactional
	public List<Student> getAllStudents(){
		return toList(studentRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
