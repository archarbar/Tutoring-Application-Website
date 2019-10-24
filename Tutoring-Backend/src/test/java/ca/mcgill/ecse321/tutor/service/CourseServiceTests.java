package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.After;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.TimeSlot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
import ca.mcgill.ecse321.tutor.dao.CourseRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseServiceTests {



	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseRepository courseRepository;


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



}