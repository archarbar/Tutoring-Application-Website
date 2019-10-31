package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutor.dao.StudentRepository;
import ca.mcgill.ecse321.tutor.model.Student;


@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceTests {

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentRepository studentRepository;

	@Before
	@After
	public void clearDatabase() {
		studentRepository.deleteAll();
	}

	@Test
	public void testCreateStudent() {
		assertEquals(0, studentService.getAllStudents().size());

		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";

		try {
			studentService.createStudent(firstName, lastName, email);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Student> allStudents = studentService.getAllStudents();

		assertEquals(1, allStudents.size());
		assertEquals(firstName, allStudents.get(0).getFirstName());
		assertEquals(lastName, allStudents.get(0).getLastName());
		assertEquals(email, allStudents.get(0).getEmail());	
	}

	@Test
	public void testCreateStudentNull() {
		assertEquals(0, studentService.getAllStudents().size());

		String firstName = null;
		String lastName = null;
		String email = null;
		String error = null;

		try {
			studentService.createStudent(firstName, lastName, email);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A first name needs to be specified! A last name needs to be specified! An email needs to be specified!", error);

		// check no change in memory
		assertEquals(0, studentService.getAllStudents().size());
	}

	@Test
	public void testCreateStudentEmpty() {
		assertEquals(0, studentService.getAllStudents().size());

		String firstName = "";
		String lastName = "";
		String email = "";
		String error = null;

		try {
			studentService.createStudent(firstName, lastName, email);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A first name needs to be specified! A last name needs to be specified! An email needs to be specified!", error);

		// check no change in memory
		assertEquals(0, studentService.getAllStudents().size());
	}

	@Test
	public void testCreateStudentSpaces() {
		assertEquals(0, studentService.getAllStudents().size());

		String firstName = " ";
		String lastName = "  ";
		String email = "   ";
		String error = null;

		try {
			studentService.createStudent(firstName, lastName, email);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A first name needs to be specified! A last name needs to be specified! An email needs to be specified!", error);

		// check no change in memory
		assertEquals(0, studentService.getAllStudents().size());
	}

}