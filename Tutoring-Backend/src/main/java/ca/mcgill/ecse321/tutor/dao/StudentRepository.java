package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
  
  Student findStudentById(Integer studentId);
  
  Student findByBooking(Booking booking);
  
  Student findByName(String firstName, String lastName);
  
  Student findByEmail(String email);
  
}



