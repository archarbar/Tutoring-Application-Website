package ca.mcgill.ecse321.tutor.servicemockito;

import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutor.service.TimeSlotService;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
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
	private static final Time START_TIME = Time.valueOf("10:35:11");
	private static final Time END_TIME = Time.valueOf("12:35:11");
	private static final DayOfTheWeek DAYOFTHEWEEK = DayOfTheWeek.MONDAY;

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

	@Test
	public void testCreateTimeSlot() {

		try {
			timeSlot = timeSlotService.createTimeSlot(START_TIME, END_TIME, DAYOFTHEWEEK);
		}
		catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(START_TIME, timeSlot.getStartTime());
		assertEquals(END_TIME, timeSlot.getEndTime());
		assertEquals(DAYOFTHEWEEK, timeSlot.getDayOfTheWeek());
	}

	@Test
	public void testCreateTimeSlotNull() {
		String error = null;

		try {
			timeSlot = timeSlotService.createTimeSlot(null, null, null);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A start time needs to be specified! A end time needs to be specified! A day of the week needs to be specified!", error);
	}

	@Test
	public void testCreateTimeSlotNullStartTime() {
		String error = null;

		try {
			timeSlot = timeSlotService.createTimeSlot(null, END_TIME, DAYOFTHEWEEK);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A start time needs to be specified!", error);
	}

	@Test
	public void testCreateTimeSlotNullEndTime() {
		String error = null;

		try {
			timeSlot = timeSlotService.createTimeSlot(START_TIME, null, DAYOFTHEWEEK);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A end time needs to be specified!", error);
	}

	@Test
	public void testCreateTimeSlotNullDay() {
		String error = null;

		try {
			timeSlot = timeSlotService.createTimeSlot(START_TIME, END_TIME, null);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A day of the week needs to be specified!", error);
	}

	@Test
	public void testCreateTimeSlotEndBeforeStart() {
		String error = null;

		try {
			timeSlot = timeSlotService.createTimeSlot(END_TIME, START_TIME, DAYOFTHEWEEK);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("The end time cannot be before the start time", error);
	}

}
