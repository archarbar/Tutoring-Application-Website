package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{

}



