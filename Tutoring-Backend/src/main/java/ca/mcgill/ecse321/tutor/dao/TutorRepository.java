package ca.mcgill.ecse321.tutor.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Tutor;

@RepositoryRestResource(collectionResourceRel = "tutors", path = "tutors")
public interface TutorRepository extends CrudRepository<Tutor, Integer> {
	
  Tutor findTutorById(int id);
  
  Tutor findTutorByEmail(String email);
  
  List<Tutor> findByCourse(Course course);

}

