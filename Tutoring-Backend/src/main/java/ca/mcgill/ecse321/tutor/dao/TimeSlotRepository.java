package ca.mcgill.ecse321.tutor.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "TimeSlots", path = "TimeSlots")
public interface TimeSlotRepository extends CrudRepository<TimeSlot, Integer>{
  
  TimeSlot findTimeSlotById(Integer timeSlotId);
  
  TimeSlot findByBooking(Booking booking);
  
  TimeSlot findByTutoringSession(TutoringSession tutoringSession);

  List<TimeSlot> findTimeSlotByTutor(Integer tutorId);
}

