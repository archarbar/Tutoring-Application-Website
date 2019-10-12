package ca.mcgill.ecse321.tutor.dao;


import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Tutor;

public interface TutorRepository extends CrudRepository<Tutor, Integer> {

}

