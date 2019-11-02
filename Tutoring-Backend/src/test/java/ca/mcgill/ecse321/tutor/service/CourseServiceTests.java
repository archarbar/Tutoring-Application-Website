package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;

import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Level;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutor.dao.CourseRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTests {

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseRepository courseRepository;

	@Before
	@After
	public void clearDatabase() {
		courseRepository.deleteAll();
	}

	@Test
	public void testCreateCourse() { // test constructor method
		assertEquals(0, courseService.getAllCourses().size());

		String name = "English";
		Level level = Level.UNIVERSITY;

		try {
			courseService.createCourse(name, level);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Course> allCourses = courseService.getAllCourses();

		assertEquals(1, allCourses.size());
		assertEquals("English", allCourses.get(0).getCourseName());
		assertEquals(Level.UNIVERSITY, allCourses.get(0).getCourseLevel());
	}

	@Test
	public void testCreateCourseNull() {
		assertEquals(0, courseService.getAllCourses().size());

		String name = null;
		Level level = null;
		String error = null;

		try {
			courseService.createCourse(name, level);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A course name needs to be specified! An education level needs to be specified!", error);

		// check no change in memory
		assertEquals(0, courseService.getAllCourses().size());
	}

	@Test
	public void testCreateCourseNullName() {
		assertEquals(0, courseService.getAllCourses().size());

		String name = null;
		Level level = Level.UNIVERSITY;
		String error = null;

		try {
			courseService.createCourse(name, level);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A course name needs to be specified!", error);

		// check no change in memory
		assertEquals(0, courseService.getAllCourses().size());
	}

	@Test
	public void testCreateCourseNullLevel() {
		assertEquals(0, courseService.getAllCourses().size());

		String name = "ECSE321";
		Level level = null;
		String error = null;

		try {
			courseService.createCourse(name, level);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("An education level needs to be specified!", error);

		// check no change in memory
		assertEquals(0, courseService.getAllCourses().size());
	}

	@Test
	public void testCreateCourseEmpty() {
		assertEquals(0, courseService.getAllCourses().size());

		String name = "";
		Level level = Level.UNIVERSITY;
		String error = null;

		try {
			courseService.createCourse(name, level);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A course name needs to be specified!", error);

		// check no change in memory
		assertEquals(0, courseService.getAllCourses().size());
	}

	@Test
	public void testCreateCourseSpaces() {
		assertEquals(0, courseService.getAllCourses().size());

		String name = "   ";
		Level level = Level.UNIVERSITY;
		String error = null;

		try {
			courseService.createCourse(name, level);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A course name needs to be specified!", error);

		// check no change in memory
		assertEquals(0, courseService.getAllCourses().size());
	}

}