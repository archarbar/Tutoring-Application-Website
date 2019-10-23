package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.tutor.model.Tutor;

@RepositoryRestResource(collectionResourceRel = "tutors", path = "tutors")
@Repository
public interface TutorRepository extends CrudRepository<Tutor, Integer> {
	
  Tutor findTutorById(int id);
  
  Tutor findTutorByEmail(String email);

}

