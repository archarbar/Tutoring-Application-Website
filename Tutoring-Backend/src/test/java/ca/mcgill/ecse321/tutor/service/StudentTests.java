package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;

import ca.mcgill.ecse321.tutor.model.Student;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutor.dao.StudentRepository;
import ca.mcgill.ecse321.tutor.service.StudentService;


@RunWith(SpringRunner.class)
@SpringBootTest
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



//	@Test
//	public void testGetStudent() {
//		String firstName = "Michael";
//		String lastName = "Li";
//		
//		Student student = studentService.getStudentByName(firstName, lastName);
//		
//		assertEquals(firstName, student.getFirstName());
//		assertEquals(lastName, student.getLastName());
//	}

}
