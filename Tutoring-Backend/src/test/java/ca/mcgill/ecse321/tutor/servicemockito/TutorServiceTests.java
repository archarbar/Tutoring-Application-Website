package ca.mcgill.ecse321.tutor.servicemockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import ca.mcgill.ecse321.tutor.dao.TutorRepository;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.service.ManagerService;
import ca.mcgill.ecse321.tutor.service.TutorService;


@RunWith(MockitoJUnitRunner.class)
public class TutorServiceTests {

	@Mock
	private TutorRepository tutorRepository;

	@InjectMocks
	private TutorService tutorService;
	@InjectMocks
	private ManagerService managerService;

	private Tutor tutor = new Tutor();

	private static final Integer SUCCESS_KEY = 1;
	private static final String EMAIL_KEY = "email@test.com";
	private static final String FIRST_NAME = "Victor";
	private static final String LAST_NAME = "Zhong";
	private static final String PASSWORD = "BigBrain123";

	@Before
	public void setMockOutput(){
		when(tutorRepository.findTutorById(anyInt())).thenAnswer( (InvocationOnMock invocation) ->{
			if (invocation.getArgument(0).equals(SUCCESS_KEY)){
				tutor.setId(SUCCESS_KEY);
				return tutor;
			} else {
				return null;
			}
		});
		when(tutorRepository.findTutorByEmail(anyString())).thenAnswer( (InvocationOnMock invocation) ->{
			if (invocation.getArgument(0).equals(EMAIL_KEY)){
				tutor.setEmail(EMAIL_KEY);
				return tutor;
			} else {
				return null;
			}
		});
		when(tutorRepository.findAll()).thenAnswer( (InvocationOnMock invocation) ->{
			List<Tutor> tutors = new ArrayList<>();
			tutor.setId(SUCCESS_KEY);
			tutors.add(tutor);
			return tutors;
		});
	}

	@Test
	public void testGetTutor(){
		assertEquals(SUCCESS_KEY, tutorService.getTutor(SUCCESS_KEY).getId());
		assertEquals(EMAIL_KEY, tutorService.getTutorByEmail(EMAIL_KEY).getEmail());
		assertEquals(SUCCESS_KEY, tutorService.getAllTutors().get(0).getId());
	}

	@Test
	public void testCreateTutor() {

		try {
			tutor = tutorService.createTutor(FIRST_NAME, LAST_NAME, EMAIL_KEY, PASSWORD);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(FIRST_NAME, tutor.getFirstName());
		assertEquals(LAST_NAME, tutor.getLastName());
		assertEquals(PASSWORD, tutor.getPassword());
		assertEquals(EMAIL_KEY, tutor.getEmail());
//		assertEquals(manager.getId(), tutor.getManager().getId());
	}

	@Test
	public void testCreateTutorNull() {
		String error = null;

		try {
			tutor = tutorService.createTutor(null, null, null, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A first name needs to be specified! A last name needs to be specified! An email needs to be specified! A password needs to be specified!", error);
	}

	@Test
	public void testCreateTutorEmpty() {
		String error = null;

		try {
			tutor = tutorService.createTutor("", "", "", "");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A first name needs to be specified! A last name needs to be specified! An email needs to be specified! A password needs to be specified!", error);
	}

	@Test
	public void testCreateTutorWhiteSpace() {
		String error = null;

		try {
			tutor = tutorService.createTutor(" ", " ", " ", " ");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A first name needs to be specified! A last name needs to be specified! An email needs to be specified! A password needs to be specified! A manager needs to be specified!", error);
	}

	@Test
	public void testCreateTutorNullFirstName() {
		String error = null;

		try {
			tutor = tutorService.createTutor(null, LAST_NAME, EMAIL_KEY, PASSWORD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A first name needs to be specified!", error);
	}

	@Test
	public void testCreateTutorNullLastName() {
		String error = null;

		try {
			tutor = tutorService.createTutor(FIRST_NAME, null, EMAIL_KEY, PASSWORD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A last name needs to be specified!", error);
	}

	@Test
	public void testCreateTutorNullEmail() {
		String error = null;

		try {
			tutor = tutorService.createTutor(FIRST_NAME, LAST_NAME, null, PASSWORD);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("An email needs to be specified!", error);
	}

	@Test
	public void testCreateTutorNullPassword() {
		String error = null;

		try {
			tutor = tutorService.createTutor(FIRST_NAME, LAST_NAME, EMAIL_KEY, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A password needs to be specified!", error);
	}


}
