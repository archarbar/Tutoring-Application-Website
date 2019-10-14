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
		if (firstName == null) {
			throw new IllegalArgumentException("A first name needs to be specified!");
		}
		if (lastName == null) {
			throw new IllegalArgumentException("A last name needs to be specified!");
		}
		if (email == null) {
			throw new IllegalArgumentException("An email needs to be specified!");
		}
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		studentRepository.save(student);
		return student;
	}

	@Transactional
	public Student getStudentById(Integer studentId) {
		Student student = studentRepository.findStudentById(studentId);
		return student;
	}
	
	@Transactional 
	public Student getStudentByName(String firstName, String lastName) {
		Student student = studentRepository.findByName(firstName, lastName);
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
