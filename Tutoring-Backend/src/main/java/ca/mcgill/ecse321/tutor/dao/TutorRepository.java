package ca.mcgill.ecse321.tutor.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

@Repository
public interface TutorRepository extends CrudRepository<Tutor, Integer> {
	
  Tutor findTutorById(Integer tutorId);
  
  Tutor findTutorbyEmail(String email);
  
  Tutor findByName(String firstName, String lastName);
  
  List<Tutor> findByCourse(Course course);
  
  Tutor findByNotification(Notification notification);
  
  Tutor findByTutoringSession(TutoringSession tutoringSession);

}

