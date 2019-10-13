package ca.mcgill.ecse321.tutor.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

public interface TutoringSessionRepository extends CrudRepository<TutoringSession, Integer>{
  
  TutoringSession findTutoringSessionById(Integer tutoringSessionId);
  
}


