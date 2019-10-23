package ca.mcgill.ecse321.tutor.dao;

import java.util.List;

import ca.mcgill.ecse321.tutor.model.Tutor;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "TutoringSessions", path = "TutoringSessions")
public interface TutoringSessionRepository extends CrudRepository<TutoringSession, Integer>{
  
  TutoringSession findTutoringSessionById(Integer tutoringSessionId);

  List<TutoringSession> findByTimeSlot(TimeSlot timeSlot);

  List<TutoringSession> findTutoringSessionByTutor (Tutor tutor);
  
  TutoringSession findTutoringSessionByBooking(Booking booking);
}


