package ca.mcgill.ecse321.tutor.dao;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.TimeSlot;


public interface TimeSlotRepository extends CrudRepository<TimeSlot, Integer>{
  
  TimeSlot findTimeSlotById(Integer timeSlotId);
  
  List<TimeSlot> findTimeSlotByTutor(Tutor tutor);
  
  TimeSlot findTimeSlotByBooking(Booking booking);
  
  TimeSlot findTimeSlotByTutoringSession(TutoringSession tutoringSession);

}

