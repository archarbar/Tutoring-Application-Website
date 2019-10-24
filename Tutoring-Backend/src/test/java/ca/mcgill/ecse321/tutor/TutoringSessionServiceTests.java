package ca.mcgill.ecse321.tutor;

import ca.mcgill.ecse321.tutor.dao.TutoringSessionRepository;
import ca.mcgill.ecse321.tutor.service.TutoringSessionService;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TutoringSessionServiceTests {

    @Mock
    private TutoringSessionRepository tutoringSessionRepository;

    @InjectMocks
    private TutoringSessionService tutoringSessionService;

    private TutoringSession tutoringSession = new TutoringSession();

    private static final Integer SUCCESS_KEY = 1;
    private Tutor tutor;

    @Before
    public  void setMock(){
        tutor = mock(Tutor.class);
        tutor.setEmail("test@email.com");
        tutor.setFirstName("Test");
        tutor.setLastName("Tutor");
        tutor.setManager(mock(Manager.class));

    }

    @Before
    public void setMockOutput(){
        when(tutoringSessionRepository.findTutoringSessionById(anyInt())).thenAnswer( (InvocationOnMock invocation) ->{
            if (invocation.getArgument(0).equals(SUCCESS_KEY)){
                tutoringSession.setId(SUCCESS_KEY);
                return tutoringSession;
            } else {
                return null;
            }
        });
        when(tutoringSessionRepository.findTutoringSessionByTutor(any(Tutor.class))).thenAnswer( (InvocationOnMock invocation) ->{
            if (invocation.getArgument(0).equals(tutor)){
                List<TutoringSession> tutoringSessions = new ArrayList<>();
                tutoringSession.setTutor(tutor);
                tutoringSessions.add(tutoringSession);
                return tutoringSessions;
            } else {
                return null;
            }
        });
        when(tutoringSessionRepository.findAll()).thenAnswer( (InvocationOnMock invocation) ->{
            List<TutoringSession> tutoringSessions = new ArrayList<>();
            tutoringSession.setId(SUCCESS_KEY);
            tutoringSessions.add(tutoringSession);
            return tutoringSessions;
        });
    }

    @Test
    public void testCreateTutoringSessionNull() {
        String error = null;

        try {
            tutoringSession = tutoringSessionService.createTutoringSession(null, null,
                    null, null, null);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        }
        assertEquals("A date needs to be specified!", error);
    }

    @Test
    public void testGetTutoringSession(){
        assertEquals(SUCCESS_KEY, tutoringSessionService.getTutoringSessionById(SUCCESS_KEY).getId());
        assertEquals(tutor, tutoringSessionService.getTutoringSessionByTutor(tutor).get(0).getTutor());
        assertEquals(SUCCESS_KEY, tutoringSessionService.getAllTutoringSessions().get(0).getId());
    }

}
