package ca.mcgill.ecse321.tutor.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.StudentRepository; 
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Rating;

@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Transactional
	public Student createStudent(Integer studentId, String firstName, String lastName, String email, Set<Booking> bookings, Set<Rating> ratings) {
		Student student = new Student();
		student.setStudentId(studentId);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setBooking(bookings);
		student.setRating(ratings);
		studentRepository.save(student);
		return student;
	}

	@Transactional
	public Student getStudent(Integer studentId) {
		Student student = studentRepository.findStudentById(studentId);
		return student;
	}

}
