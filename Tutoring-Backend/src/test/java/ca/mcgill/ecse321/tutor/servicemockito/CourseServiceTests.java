package ca.mcgill.ecse321.tutor.servicemockito;

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
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTests {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    private Course course = new Course();

    private static final Level COURSE_LEVEL = Level.UNIVERSITY;
    private static final Integer COURSE_KEY = 1;
    private static final String COURSE_NAME = "English";
    
    @Before
    public void setMockOutput(){
        when(courseRepository.findCourseByCourseLevel(any())).thenAnswer( (InvocationOnMock invocation) ->{
            if (invocation.getArgument(0).equals(COURSE_LEVEL)){
                List<Course> courses = new ArrayList<>();
                course.setCourseLevel(COURSE_LEVEL);
                courses.add(course);
                return courses;
            } else {
                return null;
            }
        });
        when(courseRepository.findCourseByCourseName(anyString())).thenAnswer( (InvocationOnMock invocation) ->{
            if (invocation.getArgument(0).equals(COURSE_NAME)){
                course.setCourseName(COURSE_NAME);
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
	public void testCreateCourse() { // test constructor method
//		assertEquals(0, courseService.getAllCourses().size());

		try {
			course = courseService.createCourse(COURSE_NAME, COURSE_LEVEL);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals("English", course.getCourseName());
		assertEquals(Level.UNIVERSITY, course.getCourseLevel());
	}

	@Test
	public void testCreateCourseNull() {
//		assertEquals(0, courseService.getAllCourses().size());
		String error = null;
		
		try {
			courseService.createCourse(null, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A course name needs to be specified! An education level needs to be specified!", error);
	}
	
	@Test
	public void testCreateCourseNullName() {
//		assertEquals(0, courseService.getAllCourses().size());
		String error = null;
		
		try {
			courseService.createCourse(null, COURSE_LEVEL);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A course name needs to be specified!", error);
	}
	
	@Test
	public void testCreateCourseNullLevel() {
//		assertEquals(0, courseService.getAllCourses().size());
		String error = null;
		
		try {
			courseService.createCourse(COURSE_NAME, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("An education level needs to be specified!", error);
	}

	@Test
	public void testCreateCourseEmpty() {
//		assertEquals(0, courseService.getAllCourses().size());
		String error = null;

		try {
			courseService.createCourse("", COURSE_LEVEL);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A course name needs to be specified!", error);
	}

	@Test
	public void testCreateCourseSpaces() {
//		assertEquals(0, courseService.getAllCourses().size());

		String error = null;

		try {
			courseService.createCourse("   ", COURSE_LEVEL);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A course name needs to be specified!", error);
	}

    @Test
    public void testGetCourse(){
        assertEquals(COURSE_LEVEL, courseService.getCourseByCourseLevel(COURSE_LEVEL).get(0).getCourseLevel());
        assertEquals(COURSE_NAME, courseService.getCourseByCourseName(COURSE_NAME).getCourseName());
        assertEquals(COURSE_KEY, courseService.getCourseById(COURSE_KEY).getId());
        assertEquals(COURSE_KEY, courseService.getAllCourses().get(0).getId());
    }

}
