package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.sql.Time;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.tutor.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.TimeSlot;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeSlotServiceTests {

	@Autowired
	private TimeSlotRepository timeslotRepository;

	@Autowired
	private TimeSlotService timeSlotService;

	@After
	public void clearDatabase() {
		timeslotRepository.deleteAll();
	}

	@Test
	public void testCreateTimeSlot() {
		assertEquals(0, timeSlotService.getAllTimeSlots().size());

		Time startTime = Time.valueOf("10:35:11");
		Time endTime = Time.valueOf("12:35:11");

		try {
			timeSlotService.createTimeSlot(startTime, endTime, DayOfTheWeek.MONDAY);
		}
		catch (IllegalArgumentException e) {
			fail();
		}

		List<TimeSlot> allTimeSlots = timeSlotService.getAllTimeSlots();

		assertEquals(1, allTimeSlots.size());
		assertEquals(startTime, allTimeSlots.get(0).getStartTime());
		assertEquals(endTime, allTimeSlots.get(0).getEndTime());
		assertEquals(DayOfTheWeek.MONDAY, allTimeSlots.get(0).getDayOfTheWeek());
	}

	@Test
	public void testCreateTimeSlotNull() {
		assertEquals(0, timeSlotService.getAllTimeSlots().size());

		Time startTime = null;
		Time endTime = null;
		String error = null;

		try {
			timeSlotService.createTimeSlot(startTime, endTime, null);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A start time needs to be specified! A end time needs to be specified! A day of the week needs to be specified!", error);

		// check no change in memory
		assertEquals(0, timeSlotService.getAllTimeSlots().size());
	}

	@Test
	public void testCreateTimeSlotEndBeforeStart() {
		assertEquals(0, timeSlotService.getAllTimeSlots().size());

		Time startTime = Time.valueOf("12:35:11");;
		Time endTime = Time.valueOf("10:35:11");;
		String error = null;

		try {
			timeSlotService.createTimeSlot(startTime, endTime, DayOfTheWeek.MONDAY);
		}
		catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("The end time cannot be before the start time", error);

		// check no change in memory
		assertEquals(0, timeSlotService.getAllTimeSlots().size());
	}

}