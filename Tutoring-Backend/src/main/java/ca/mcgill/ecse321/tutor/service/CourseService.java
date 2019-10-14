package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.CourseRepository;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.CourseLevel;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.Booking;

@Service
public class CourseService {

	@Autowired
	CourseRepository courseRepository;

	@Transactional
	public Course createCourse(String courseName, CourseLevel level, Set<Tutor> tutors, Set<Booking> booking) {
		Course course = new Course();
		course.setName(courseName);
		course.setLevel(level);
		course.setTutor(tutors);
		course.setBooking(booking);
		courseRepository.save(course);
		return course;
	}

	@Transactional
	public Course getBooking(Integer courseId) {
		Course course = courseRepository.findCourseById(courseId);
		return course;
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

}
