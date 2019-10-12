package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.CourseLevel;

public interface CourseLevelRepository extends CrudRepository<CourseLevel, Integer>{

	// I think we should delete this repository, since 
	// we're not doing any CRUD operations on the enum
	
}
