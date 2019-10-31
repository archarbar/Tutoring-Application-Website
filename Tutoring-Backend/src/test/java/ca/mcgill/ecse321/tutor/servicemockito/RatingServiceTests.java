package ca.mcgill.ecse321.tutor.servicemockito;

import ca.mcgill.ecse321.tutor.dao.RatingRepository;
import ca.mcgill.ecse321.tutor.service.RatingService;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.Level;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Rating;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RatingServiceTests {

	@Mock
	private RatingRepository ratingRepository;

	@InjectMocks
	private RatingService ratingService;

	private Student student;
	private Tutor tutor;
	private TutoringSession tutoringSession;
	
	private Rating rating = new Rating();

	private static final Integer SUCCESS_KEY = 1;
	private static final Integer RATING_STARS = 5;
	private static final String RATING_COMMENT = "Worst ever";
	private static final Date SESSTION_DATE = Date.valueOf("2019-10-14");
	private static final String TUTOR_EMAIL = "arthurmorgan@redemption.com";
	private static final String STUDENT_FIRSTNAME = "Michael";
	private static final String STUDENT_LASTNAME = "Li";
	private static final String STUDENT_EMAIL = "mlej@live.com";

	@Before
	public void setMockOutput(){
		when(ratingRepository.findRatingById(anyInt())).thenAnswer( (InvocationOnMock invocation) ->{
			if (invocation.getArgument(0).equals(SUCCESS_KEY)){
				rating.setId(SUCCESS_KEY);
				return rating;
			} else {
				return null;
			}
		});
		when(ratingRepository.findAll()).thenAnswer((InvocationOnMock invocation) ->{
			List<Rating> ratings = new ArrayList<>();
			rating.setId(SUCCESS_KEY);
			ratings.add(rating);
			return ratings;
		});
	}
	
	@Before
	public void setUpMocks() {
		student = mock(Student.class);
		student.setEmail(STUDENT_EMAIL);
		student.setFirstName(STUDENT_FIRSTNAME);
		student.setLastName(STUDENT_LASTNAME);
		tutor = mock(Tutor.class);
		tutor.setEmail(TUTOR_EMAIL);
		tutoringSession = mock(TutoringSession.class);
	}

	@Test
	public void testCreateRating() {
//		assertEquals(0, ratingService.getAllRatings().size());

		try {
			rating = ratingService.createRating(RATING_STARS, RATING_COMMENT, student, tutor, tutoringSession);
		}
		catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(RATING_STARS, rating.getStars());
		assertEquals(RATING_COMMENT, rating.getComment());
		assertEquals(student.getId(), rating.getStudent().getId());
		assertEquals(student.getEmail(), rating.getStudent().getEmail());
		assertEquals(tutor.getId(), rating.getTutor().getId());
		assertEquals(tutor.getEmail(), rating.getTutor().getEmail());
		assertEquals(tutoringSession.getId(), rating.getTutoringSession().getId());
	}

	@Test
	public void testCreateRatingNull() {
//		assertEquals(0, ratingService.getAllRatings().size());

		String error = null;

		try {
			ratingService.createRating(null, null, null, null, null);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A star rating needs to be specified! A comment needs to be specified! A student needs to be specified! A tutor needs to be specified! A tutoring session needs to be specified!", error);
	}
	
	@Test
	public void testCreateRatingNullStars() {
//		assertEquals(0, ratingService.getAllRatings().size());

		String error = null;

		try {
			ratingService.createRating(null, RATING_COMMENT, student, tutor, tutoringSession);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A star rating needs to be specified!", error);
	}
	
	@Test
	public void testCreateRatingNullComment() {
//		assertEquals(0, ratingService.getAllRatings().size());

		String error = null;

		try {
			ratingService.createRating(RATING_STARS, null, student, tutor, tutoringSession);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A comment needs to be specified!", error);
	}
	
	@Test
	public void testCreateRatingNullStudent() {
//		assertEquals(0, ratingService.getAllRatings().size());

		String error = null;

		try {
			ratingService.createRating(RATING_STARS, RATING_COMMENT, null, tutor, tutoringSession);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A student needs to be specified!", error);
	}
	
	@Test
	public void testCreateRatingNullTutor() {
//		assertEquals(0, ratingService.getAllRatings().size());

		String error = null;

		try {
			ratingService.createRating(RATING_STARS, RATING_COMMENT, student, null, tutoringSession);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A tutor needs to be specified!", error);
	}
	
	@Test
	public void testCreateRatingNullTutoringSession() {
//		assertEquals(0, ratingService.getAllRatings().size());

		String error = null;

		try {
			ratingService.createRating(RATING_STARS, RATING_COMMENT, student, tutor, null);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A tutoring session needs to be specified!", error);
	}

	@Test
	public void testCreateRatingEmptyComment() {
//		assertEquals(0, ratingService.getAllRatings().size());

		String error = null;

		try {
			ratingService.createRating(RATING_STARS, "", student, tutor, tutoringSession);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A comment needs to be specified!", error);
	}

	@Test
	public void testCreateRatingSpacesComment() {
//		assertEquals(0, ratingService.getAllRatings().size());

		String error = null;

		try {
			ratingService.createRating(RATING_STARS, "  ", student, tutor, tutoringSession);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A comment needs to be specified!", error);
	}
	
	@Test
	public void testGetRating(){
		assertEquals(SUCCESS_KEY, ratingService.getRating(SUCCESS_KEY).getId());
		assertEquals(SUCCESS_KEY, ratingService.getAllRatings().get(0).getId());
	}

}