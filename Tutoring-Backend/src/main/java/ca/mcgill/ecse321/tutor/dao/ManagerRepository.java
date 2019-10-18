package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Manager;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "manager", path = "manager")
public interface ManagerRepository extends CrudRepository<Manager, Integer> {
	
	Manager findManagerById(Integer Id);

}
