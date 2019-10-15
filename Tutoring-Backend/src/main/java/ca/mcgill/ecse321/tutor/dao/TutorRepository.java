package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.tutor.model.Tutor;

@Repository
public interface TutorRepository extends CrudRepository<Tutor, Integer> {
	
  Tutor findTutorById(Integer userId);
  
  Tutor findTutorByEmail(String email);

}

