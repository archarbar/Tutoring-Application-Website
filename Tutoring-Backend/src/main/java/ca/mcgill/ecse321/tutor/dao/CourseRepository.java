package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Course;

public interface CourseRepository extends CrudRepository<Course, Integer>{

	Course findCourseById(Integer courseId);
	
	Course findCourseByName(String courseName);
	
	Course findByBooking(Booking booking);
	
	Course findByTutoringSession(TutoringSession tutoringSession);
	
	
}
