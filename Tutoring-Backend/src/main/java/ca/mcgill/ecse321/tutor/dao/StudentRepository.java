package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Student;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "students", path = "students")
public interface StudentRepository extends CrudRepository<Student, Integer> {
  
  Student findStudentById(Integer studentId);
  
  Student findStudentByEmail(String email);
  
  Student findStudentByFirstName(String firstName);
  
  Student findStudentByLastName(String lastName);
  
}



