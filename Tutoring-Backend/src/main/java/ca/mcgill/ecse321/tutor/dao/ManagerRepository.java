package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Manager;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {
}

