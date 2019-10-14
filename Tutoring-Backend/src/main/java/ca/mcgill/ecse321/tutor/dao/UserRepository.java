package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.tutor.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>{}



