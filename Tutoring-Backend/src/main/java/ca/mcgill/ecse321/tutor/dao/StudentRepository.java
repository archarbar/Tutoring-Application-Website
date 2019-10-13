package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
  
  Student findStudentById(Integer studentId);
  
  List<Student> findStudentByBooking(Booking booking);
  
  Student findStudentByRating(Rating rating);
  
}



