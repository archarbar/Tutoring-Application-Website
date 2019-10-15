package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Student;

public interface StudentRepository extends CrudRepository<Student, Integer> {
  
  Student findStudentById(Integer studentId);
  
  Student findStudentByEmail(String email);
  
  Student findStudentByFirstName(String firstName);
  
  Student findStudentByLastName(String lastName);
  
}



