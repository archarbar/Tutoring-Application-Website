package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.CourseRepository;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.TimeSlot;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Transactional
	public Course createCourse(String courseName, Level level) {
		String error = "";
		if (courseName == null || courseName.trim().length() == 0) {
			error = error + "A course name needs to be specified! ";
		}
		if (level == null) {
			error = error + "An education level needs to be specified!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Course course = new Course();
		course.setCourseName(courseName);
		course.setCourseLevel(level);
		courseRepository.save(course);
		return course;
	}

	@Transactional
	public Course getCourseById(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("A course ID needs to be specified!");
		}
		Course course = courseRepository.findCourseById(id);
		return course;
	}

	@Transactional
	public Course getCourseByCourseName(String courseName) {
		if (courseName == null || courseName.trim().length() == 0) {
			throw new IllegalArgumentException("A course name needs to be specified!");
		}
		Course course = courseRepository.findCourseByCourseName(courseName);
		return course;
	}

	@Transactional
	public ArrayList<Course> getCourseByCourseLevel(Level courseLevel) {
		ArrayList<Course> coursesByLevel = new ArrayList<>();
		for (Course course : courseRepository.findCourseByCourseLevel(courseLevel)) {
			coursesByLevel.add(course);
		}
		return coursesByLevel;
	}

	@Transactional
	public List<Course> getAllCourses(){
		return toList(courseRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
	
	@Transactional
	public Course deleteCourse(Course course) {
		if (course == null) {
			throw new IllegalArgumentException("A course needs to be specified!");
		}
		courseRepository.delete(course);
		return course;
	}
	
	@Transactional
	public Integer deleteCourseById(Integer Id) {
		if (Id == null) {
			throw new IllegalArgumentException("A course Id needs to be specified!");
		}
		courseRepository.deleteById(Id);
		return Id;
	}

}
