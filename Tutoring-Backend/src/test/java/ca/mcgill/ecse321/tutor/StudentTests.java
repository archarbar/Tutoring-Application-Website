package ca.mcgill.ecse321.tutor;

import static org.junit.Assert.*;

import org.junit.After;

import ca.mcgill.ecse321.tutor.model.Student;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ca.mcgill.ecse321.tutor.dao.StudentRepository;
import ca.mcgill.ecse321.tutor.service.StudentService;

public class StudentTests {

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentRepository studentRepository;

	@After
	public void clearDatabase() {

		studentRepository.deleteAll();

	}

	@Test
	public void testCreateStudent() {
		//		assertEquals(0, service.getAllStudents().size());

		int studentId = 1;
		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";
		try {
			studentService.createStudent(studentId, firstName, lastName, email);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Student> allStudents = service.getAllStudents();

		assertEquals(1, allStudents.size());
		assertEquals(studentId, allStudents.get(0).getStudentId());
		assertEquals(firstName, allStudents.get(0).getFirstName());
		assertEquals(lastName, allStudents.get(0).getLastName());
		assertEquals(email, allStudents.get(0).getEmail());	

	}



	@Test
	public void testGetStudent() {
		fail("Not yet implemented");
	}

}
