package ca.mcgill.ecse321.tutor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.CourseRepository;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.CourseLevel;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Transactional
	public Course createCourse(Integer courseId, String courseName, CourseLevel level) {
		Course course = new Course();
		course.setCourseId(courseId);
		course.setName(courseName);
		course.setLevel(level);
		courseRepository.save(course);
		return course;

	}

	@Transactional
	public Course getBooking(Integer courseId) {
		Course course = courseRepository.findCourseById(courseId);
		return course;
	}

}
