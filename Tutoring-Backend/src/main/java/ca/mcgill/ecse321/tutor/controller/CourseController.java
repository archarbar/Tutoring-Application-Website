package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.CourseDto;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping("/course/{courseId}")
    public CourseDto getCourseById(@PathVariable int courseId) {
        return convertToDto(service.getCourseById(courseId));
    }

    @GetMapping("/course/{level}")
    public ArrayList<CourseDto> getCoursesByLevel (@PathVariable Level level) {
        ArrayList<CourseDto> returnCourse = new ArrayList<>();
        ArrayList<Course> courses = service.getCourseByCourseLevel(level);
        for (Course course : courses) {
            returnCourse.add(convertToDto(course));
        }
        return returnCourse;
    }

    @GetMapping("/course/{courseName}")
    public CourseDto getCourseByName (@PathVariable String courseName) {
        return convertToDto(service.getCourseByCourseName(courseName));
    }


    private CourseDto convertToDto(Course course) {
        if (course == null) throw new IllegalArgumentException("This course does not exist!");
        return new CourseDto(course.getCourseName(), course.getCourseLevel(), course.getId());
    }
}
