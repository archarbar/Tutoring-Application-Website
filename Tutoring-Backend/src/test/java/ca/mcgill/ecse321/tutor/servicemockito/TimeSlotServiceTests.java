package ca.mcgill.ecse321.tutor.servicemockito;

import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
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
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TimeSlotServiceTests {

    @Mock
    private TimeSlotRepository timeSlotRepository;

    @InjectMocks
    private TimeSlotService timeSlotService;

    private TimeSlot timeSlot = new TimeSlot();

    private static final Integer SUCCESS_KEY = 1;

    @Before
    public void setMockOutput(){
        when(timeSlotRepository.findTimeSlotById(anyInt())).thenAnswer( (InvocationOnMock invocation) ->{
            if (invocation.getArgument(0).equals(SUCCESS_KEY)){
                timeSlot.setId(SUCCESS_KEY);
                return timeSlot;
            } else {
                return null;
            }
        });
        when(timeSlotRepository.findAll()).thenAnswer( (InvocationOnMock invocation) ->{
            List<TimeSlot> timeSlots = new ArrayList<>();
            timeSlot.setId(SUCCESS_KEY);
            timeSlots.add(timeSlot);
            return timeSlots;
        });
    }

    @Test
    public void testGetTimeSlot(){
        assertEquals(SUCCESS_KEY, timeSlotService.getTimeSlotById(SUCCESS_KEY).getId());
        assertEquals(SUCCESS_KEY, timeSlotService.getAllTimeSlots().get(0).getId());
    }

}
