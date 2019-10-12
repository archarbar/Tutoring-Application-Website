package main.java.ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import main.java.ca.mcgill.ecse321.tutor.model.Course;
import main.java.ca.mcgill.ecse321.tutor.model.Booking;
import main.java.ca.mcgill.ecse321.tutor.model.TutoringSession;

public interface CourseRepository extends CrudRepository<Course, Integer>{

	Course findCourseById(Integer courseId);
	
	Course findCourseByName(String courseName);
	
	Course findByBooking(Booking booking);
	
	Course findByTutoringSession(TutoringSession tutoringSession);
	
	
}
