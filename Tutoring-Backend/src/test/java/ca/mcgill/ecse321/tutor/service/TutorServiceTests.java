package ca.mcgill.ecse321.tutor.service;

import ca.mcgill.ecse321.tutor.dao.TutorRepository;
import ca.mcgill.ecse321.tutor.model.Tutor;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TutorServiceTests {

    @Mock
    private TutorRepository tutorRepository;

    @InjectMocks
    private TutorService tutorService;

    private Tutor tutor = new Tutor();

    private static final Integer SUCCESS_KEY = 1;
    private static final String EMAIL_KEY = "email@test.com";

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

}
