package main.java.ca.mcgill.ecse321.tutor.dao;

import main.java.ca.mcgill.ecse321.tutor.model.Manager;
import org.springframework.data.repository.CrudRepository;

public interface ManagerRepository extends CrudRepository<Manager, Integer> {
}

