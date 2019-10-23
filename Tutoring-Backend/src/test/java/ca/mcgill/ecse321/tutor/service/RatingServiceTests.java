package ca.mcgill.ecse321.tutor.service;

import ca.mcgill.ecse321.tutor.dao.RatingRepository;
import ca.mcgill.ecse321.tutor.model.Rating;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class RatingServiceTests {

    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    private RatingService ratingService;

    private Rating rating = new Rating();

    private static final Integer SUCCESS_KEY = 1;

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
        when(ratingRepository.findAll()).thenAnswer( (InvocationOnMock invocation) ->{
            List<Rating> ratings = new ArrayList<>();
            rating.setId(SUCCESS_KEY);
            ratings.add(rating);
            return ratings;
        });
    }

    @Test
    public void testCreateRatingNullStars() {
        String error = null;
        try {
            rating = ratingService.createRating(null, null,
                    mock(Student.class), null, null);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("A star rating needs to be specified!", error);
    }

    @Test
    public void testCreateRatingNullStudent() {
        String error = null;
        try {
            rating = ratingService.createRating(5, null,
                    null, null, null);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("A student needs to be specified!", error);
    }

    @Test
    public void testGetRating(){
        assertEquals(SUCCESS_KEY, ratingService.getRating(SUCCESS_KEY).getId());
        assertEquals(SUCCESS_KEY, ratingService.getAllRatings().get(0).getId());
    }

}
