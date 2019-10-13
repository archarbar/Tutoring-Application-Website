package ca.mcgill.ecse321.tutor.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;


public interface TimeSlotRepository extends CrudRepository<TimeSlot, Integer>{
  
  TimeSlot findTimeSlotById(Integer timeSlotId);
  
  List<TimeSlot> findByTutor(Tutor tutor);
  
  TimeSlot findByBooking(Booking booking);
  
  TimeSlot findByTutoringSession(TutoringSession tutoringSession);

}

