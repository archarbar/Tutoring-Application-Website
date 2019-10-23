package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.CourseRepository;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Level;

@Service
public class CourseService {

  @Autowired
  CourseRepository courseRepository;

  @Transactional
  public Course createCourse(String courseName, Level level) {
    if (courseName == null) {
      throw new IllegalArgumentException("A course name needs to be specified!");
    }
    Course course = new Course();
    course.setCourseName(courseName);
    course.setCourseLevel(level);
    courseRepository.save(course);
    return course;
  }

  @Transactional
  public Course getCourseById(Integer id) {
    Course course = courseRepository.findCourseById(id);
    return course;
  }

  @Transactional
  public Course getCourseByCourseName(String courseName) {
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

}
