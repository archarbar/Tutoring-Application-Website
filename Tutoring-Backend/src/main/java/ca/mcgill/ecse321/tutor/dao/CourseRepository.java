package ca.mcgill.ecse321.tutor.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Level;

public interface CourseRepository extends CrudRepository<Course, Integer>{

	Course findCourseById(Integer courseId);
	
	Course findCourseByName(String courseName);
	
	List<Course> findCourseByLevel(Level level);
	
}
