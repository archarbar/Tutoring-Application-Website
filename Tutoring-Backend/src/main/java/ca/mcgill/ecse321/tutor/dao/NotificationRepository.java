package ca.mcgill.ecse321.tutor.dao;


import ca.mcgill.ecse321.tutor.model.Tutor;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Notification;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "notifications", path = "notifications")
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
  
	Notification findNotificationById(Integer Id);

	List<Notification> findNotificationByTutor(Tutor tutor);
	
}
