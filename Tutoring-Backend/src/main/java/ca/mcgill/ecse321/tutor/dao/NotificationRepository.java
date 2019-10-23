package ca.mcgill.ecse321.tutor.dao;


import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Notification;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "notifications", path = "notifications")
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
  
	Notification findNotificationById(Integer Id);
	
}
