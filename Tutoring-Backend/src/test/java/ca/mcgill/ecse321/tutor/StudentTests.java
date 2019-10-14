package ca.mcgill.ecse321.tutor;

import static org.junit.Assert.*;

import java.util.List;

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
<<<<<<< HEAD
		assertEquals(0, studentService.getAllStudents().size());
		
=======
		//		assertEquals(0, service.getAllStudents().size());

>>>>>>> 8b468bde787bf66ade79cebf44b480aabd2ba368
		int studentId = 1;
		String firstName = "Michael";
		String lastName = "Li";
		String email = "mlej@live.com";
		try {
<<<<<<< HEAD
		studentService.createStudent(studentId, firstName, lastName, email, null, null);
		} catch (IllegalArgumentException e) {
			fail();
		}
		
		List<Student> allStudents = studentService.getAllStudents();
		
=======
			studentService.createStudent(studentId, firstName, lastName, email);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Student> allStudents = service.getAllStudents();

>>>>>>> 8b468bde787bf66ade79cebf44b480aabd2ba368
		assertEquals(1, allStudents.size());
		assertEquals(firstName, allStudents.get(0).getFirstName());
		assertEquals(lastName, allStudents.get(0).getLastName());
		assertEquals(email, allStudents.get(0).getEmail());	

	}



	@Test
	public void testGetStudent() {
		String firstName = "Michael";
		String lastName = "Li";
		
		Student student = studentService.getStudentByName(firstName, lastName);
		
		assertEquals(firstName, student.getFirstName());
		assertEquals(lastName, student.getLastName());
	}

}
