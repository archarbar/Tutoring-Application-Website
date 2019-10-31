package ca.mcgill.ecse321.tutor.servicemockito;

import ca.mcgill.ecse321.tutor.dao.StudentRepository;
import ca.mcgill.ecse321.tutor.service.StudentService;
import ca.mcgill.ecse321.tutor.model.Student;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTests {

	@Mock
	private StudentRepository studentRepository;

	@InjectMocks
	private StudentService studentService;

	private Student student = new Student();

	private static final Integer SUCCESS_KEY = 1;
	private static final String EMAIL_KEY = "email@test.com";
	private static final String FIRST_NAME = "FTest";
	private static final String LAST_NAME = "LTest";

	@Before
	public void setMockOutput(){
		when(studentRepository.findStudentById(anyInt())).thenAnswer( (InvocationOnMock invocation) ->{
			if (invocation.getArgument(0).equals(SUCCESS_KEY)){
				student.setId(SUCCESS_KEY);
				return student;
			} else {
				return null;
			}
		});
		when(studentRepository.findStudentByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) ->{
			if (invocation.getArgument(0).equals(EMAIL_KEY)){
				student.setEmail(EMAIL_KEY);
				return student;
			} else {
				return null;
			}
		});
		when(studentRepository.findStudentByFirstName(anyString())).thenAnswer( (InvocationOnMock invocation) ->{
			if (invocation.getArgument(0).equals(FIRST_NAME)){
				student.setFirstName(FIRST_NAME);
				return student;
			} else {
				return null;
			}
		});
		when(studentRepository.findStudentByLastName(anyString())).thenAnswer( (InvocationOnMock invocation) ->{
			if (invocation.getArgument(0).equals(LAST_NAME)){
				student.setLastName(LAST_NAME);
				return student;
			} else {
				return null;
			}
		});
		when(studentRepository.findAll()).thenAnswer( (InvocationOnMock invocation) ->{
			List<Student> students = new ArrayList<>();
			student.setId(SUCCESS_KEY);
			students.add(student);
			return students;
		});
	}

	@Test
	public void testGetStudent(){
		assertEquals(SUCCESS_KEY, studentService.getStudentById(SUCCESS_KEY).getId());
		assertEquals(EMAIL_KEY, studentService.getStudentByEmail(EMAIL_KEY).getEmail());
		assertEquals(FIRST_NAME, studentService.getStudentByFirstName(FIRST_NAME).getFirstName());
		assertEquals(LAST_NAME, studentService.getStudentByLastName(LAST_NAME).getLastName());
		assertEquals(SUCCESS_KEY, studentService.getAllStudents().get(0).getId());
	}

	@Test
	public void testCreateStudent() {

		try {
			student = studentService.createStudent(FIRST_NAME, LAST_NAME, EMAIL_KEY);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(FIRST_NAME, student.getFirstName());
		assertEquals(LAST_NAME, student.getLastName());
		assertEquals(EMAIL_KEY, student.getEmail());	
	}

	@Test
	public void testCreateStudentNull() {
		String firstName = null;
		String lastName = null;
		String email = null;
		String error = null;

		try {
			student = studentService.createStudent(firstName, lastName, email);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A first name needs to be specified! A last name needs to be specified! An email needs to be specified!", error);	
	}

}
