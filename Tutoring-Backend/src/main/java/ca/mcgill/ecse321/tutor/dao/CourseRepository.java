package ca.mcgill.ecse321.tutor.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.Tutor;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "courses", path = "courses")
public interface CourseRepository extends CrudRepository<Course, Integer>{

	Course findCourseById(Integer id);
	
	Course findCourseByCourseName(String courseName);
	
	List<Course> findCourseByCourseLevel(Level level);
	
	List<Course> findCourseByTutor(Tutor tutor);
	
}
