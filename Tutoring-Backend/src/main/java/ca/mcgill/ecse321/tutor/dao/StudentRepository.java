package ca.mcgill.ecse321.tutor.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Rating;
import ca.mcgill.ecse321.tutor.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
  
  Student findStudentById(Integer studentId);
  
  List<Student> findByBooking(Booking booking);
  
  Student findByRating(Rating rating);
  
  Student findByName(String firstName, String lastName);
  
}



