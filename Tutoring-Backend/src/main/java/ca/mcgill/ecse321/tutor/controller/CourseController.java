package ca.mcgill.ecse321.tutor.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutor.dto.CourseDto;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.CourseService;
import ca.mcgill.ecse321.tutor.service.TutorService;

@CrossOrigin(origins = "*")
@RestController
public class CourseController {

	@Autowired
	private CourseService service;
	@Autowired
	private TutorService tutorService;

	@GetMapping(value= {"/courses", "/courses/"})
	public List<CourseDto> getAllCourses() {
		List<CourseDto> cDtos = new ArrayList<CourseDto>();
		for (Course course: service.getAllCourses()) {
			cDtos.add(convertToDto(course));
		}
		return cDtos;
	}

	@GetMapping("/course/{courseId}")
	public CourseDto getCourseById(@PathVariable String courseId) {
		return convertToDto(service.getCourseById(Integer.parseInt(courseId)));
	}

	// USE CASE 5

	@PostMapping("/course/{tutorId}/add")
	public void addCourseForTutor(@RequestParam("courseName") String courseName,
			@RequestParam("tutorId") String tutorId) {
		Tutor tutor = tutorService.getTutorById(Integer.parseInt(tutorId));
		tutorService.addCourseForTutor(tutor, courseName);
	}

	@GetMapping("/course/level/{level}")
	public ArrayList<CourseDto> getCoursesByLevel (@PathVariable Level level) {
		ArrayList<CourseDto> returnCourse = new ArrayList<>();
		ArrayList<Course> courses = service.getCourseByCourseLevel(level);
		for (Course course : courses) {
			returnCourse.add(convertToDto(course));
		}
		return returnCourse;
	}

	@GetMapping("/course/name/{courseName}")
	public CourseDto getCourseByName (@PathVariable String courseName) {
		return convertToDto(service.getCourseByCourseName(courseName));
	}


	private CourseDto convertToDto(Course course) {
		if (course == null) throw new IllegalArgumentException("This course does not exist!");
		return new CourseDto(course.getCourseName(), course.getCourseLevel(), course.getId());
	}
}
