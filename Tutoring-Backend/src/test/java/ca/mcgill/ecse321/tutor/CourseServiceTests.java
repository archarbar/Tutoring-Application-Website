package ca.mcgill.ecse321.tutor;

import ca.mcgill.ecse321.tutor.dao.CourseRepository;
import ca.mcgill.ecse321.tutor.service.CourseService;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Level;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTests {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    private Course course = new Course();

    private static final Level LEVEL_KEY = Level.UNIVERSITY;
    private static final String COURSENAME_KEY = "CourseTestName";
    private static final Integer COURSE_KEY = 1;

    @Before
    public void setMockOutput(){
        when(courseRepository.findCourseByCourseLevel(any())).thenAnswer( (InvocationOnMock invocation) ->{
            if (invocation.getArgument(0).equals(LEVEL_KEY)){
                List<Course> courses = new ArrayList<>();
                course.setCourseLevel(LEVEL_KEY);
                courses.add(course);
                return courses;
            } else {
                return null;
            }
        });
        when(courseRepository.findCourseByCourseName(anyString())).thenAnswer( (InvocationOnMock invocation) ->{
            if (invocation.getArgument(0).equals(COURSENAME_KEY)){
                course.setCourseName(COURSENAME_KEY);
                return course;
            } else {
                return null;
            }
        });
        when(courseRepository.findCourseById(anyInt())).thenAnswer( (InvocationOnMock invocation) ->{
            if (invocation.getArgument(0).equals(COURSE_KEY)){
                course.setId(COURSE_KEY);
                return course;
            } else {
                return null;
            }
        });
        when(courseRepository.findAll()).thenAnswer( (InvocationOnMock invocation) ->{
            List<Course> courses = new ArrayList<>();
            course.setId(COURSE_KEY);
            courses.add(course);
            return courses;
        });
    }

    @Test
    public void testGetCourse(){
        assertEquals(LEVEL_KEY, courseService.getCourseByCourseLevel(LEVEL_KEY).get(0).getCourseLevel());
        assertEquals(COURSENAME_KEY, courseService.getCourseByCourseName(COURSENAME_KEY).getCourseName());
        assertEquals(COURSE_KEY, courseService.getCourseById(COURSE_KEY).getId());
        assertEquals(COURSE_KEY, courseService.getAllCourses().get(0).getId());
    }

}
