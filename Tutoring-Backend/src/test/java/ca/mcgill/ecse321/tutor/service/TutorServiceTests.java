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

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
import ca.mcgill.ecse321.tutor.dao.ManagerRepository;
import ca.mcgill.ecse321.tutor.dao.TutorRepository;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Tutor;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TutorServiceTests {

	@Autowired
	private TutorRepository tutorRepository;
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private ManagerService managerService;
	@Autowired
	private TutorService tutorService;

	@Before
	@After
	public void clearDatabase() {
		bookingRepository.deleteAll();
		tutorRepository.deleteAll();
		managerRepository.deleteAll();
	}

	@Test
	public void testCreateTutor() { //test constructor methods
		assertEquals(0, tutorService.getAllTutors().size());
		String firstName = "Marcus";
		String lastName = "Fenix";
		String email = "marcusfenix@gears.com";
		String password = "locust";
		Manager manager = managerService.createManager();
		try {
			tutorService.createTutor(firstName, lastName, email, password);
		} catch (IllegalArgumentException e) {
			// Check that no error occurred
			fail();
		}
		List<Tutor> allTutors = tutorService.getAllTutors();
		assertEquals(1, allTutors.size());
		assertEquals(firstName, allTutors.get(0).getFirstName());
		assertEquals(email, allTutors.get(0).getEmail());
	}

	@Test

	public void testCreateTutorNull() {
		assertEquals(0, tutorService.getAllTutors().size());
		String firstName = null;
		String lastName = null;
		String email = null;
		String password = null;
		Manager manager = null;
		String error = null;
		try {
			tutorService.createTutor(firstName, lastName, email, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		// check error
		assertEquals("A first name needs to be specified! A last name needs to be specified! An email needs to be specified! A password needs to be specified!", error);

		// check no change in memory
		assertEquals(0, tutorService.getAllTutors().size());
	}

//	@Test
//	public void testCreateTutorEmpty() {
//		assertEquals(0, tutorService.getAllTutors().size());
//		String firstName = "";
//		String lastName = "";
//		String email = "";
//		String password = "";
//		Manager manager = managerService.createManager();
//		String error = null;
//		try {
//			tutorService.createTutor(firstName, lastName, email, password);
//		} catch (IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//
//		// check error
//		assertEquals("A first name needs to be specified! A last name needs to be specified! An email needs to be specified! A password needs to be specified!", error);
//
//		// check no change in memory
//		assertEquals(0, tutorService.getAllTutors().size());
//	}

	@Test
	public void testCreateTutorWhiteSpace() {
		assertEquals(0, tutorService.getAllTutors().size());
		String firstName = "   ";
		String lastName = " ";
		String email = "  ";
		String password = "   ";
//		Manager manager = managerService.createManager();
		String error = null;
		try {
			tutorService.createTutor(firstName, lastName, email, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A first name needs to be specified! A last name needs to be specified! An email needs to be specified! A password needs to be specified!", error);

		// check no change in memory
		assertEquals(0, tutorService.getAllTutors().size());
	}
	
//	@Test
//	public void testCreateTutorNullManager() {
//		assertEquals(0, tutorService.getAllTutors().size());
//
//		String firstName = "Marcus";
//		String lastName = "Fenix";
//		String email = "marcusfenix@gears.com";
//		String password = "locust";
//		String error = null;
//		try {
//			tutorService.createTutor(firstName, lastName, email, password);
//		} catch (IllegalArgumentException e) {
//			error = e.getMessage();
//		}
//
//		// check error
//		assertEquals("A manager needs to be specified!", error);
//
//		// check no change in memory
//		assertEquals(0, tutorService.getAllTutors().size());
//	}

		@Test
		public void testChangeTutorEmail() { // test setter methods
			assertEquals(0, tutorService.getAllTutors().size());
			String firstName = "Marcus";
			String lastName = "Fenix";
			String email = "marcusfenix@gears.com";
			String password = "locust";
			String newEmail = "jdfenix@gears.com";
			Manager manager = managerService.createManager();
			Tutor t = tutorService.createTutor(firstName, lastName, email, password);
			try {
				t.setEmail(newEmail);
				tutorRepository.save(t);
			} catch (IllegalArgumentException e) {
				fail();
			}
			List<Tutor> allTutors = tutorService.getAllTutors();
			assertEquals(newEmail, allTutors.get(0).getEmail());
		}
		
		@Test
		public void testChangeTutorFirstName() { // test setter methods
			assertEquals(0, tutorService.getAllTutors().size());
			String firstName = "Marcus";
			String lastName = "Fenix";
			String email = "marcusfenix@gears.com";
			String password = "locust";
			String newFirstName = "Sixnine";
			Manager manager = managerService.createManager();
			Tutor t = tutorService.createTutor(firstName, lastName, email, password);
			try {
				t.setFirstName(newFirstName);
				tutorRepository.save(t);
			} catch (IllegalArgumentException e) {
				fail();
			}
			List<Tutor> allTutors = tutorService.getAllTutors();
			assertEquals(1, allTutors.size());
			assertEquals(newFirstName, allTutors.get(0).getFirstName());
		}
		
		
		@Test
		public void testGetTutorByEmail() { // test getter method
			assertEquals(0, tutorService.getAllTutors().size());
			String firstName = "Marcus";
			String lastName = "Fenix";
			String email = "marcusfenix@gears.com";
			String password = "locust";
			Manager manager = managerService.createManager();
			Tutor tutor = tutorService.createTutor(firstName, lastName, email, password);
			List<Tutor> allTutors = null;
			try {
				allTutors = tutorService.getAllTutors();
			} catch (IllegalArgumentException e) {
				fail();
			}
			assertEquals(tutor.getEmail(), allTutors.get(0).getEmail());
		}
}