package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Tutor;

public interface TutorRepository extends CrudRepository<Tutor, Integer> {
  
  Tutor findTutorById(Integer tutorId);
  
  List<Tutor> findTutorByCourse(Course course);
  
  Tutor findTutorByNotification(Notification notification);
  
  Tutor findTutorByTutoringSession(TutoringSession tutoringSession);

}

