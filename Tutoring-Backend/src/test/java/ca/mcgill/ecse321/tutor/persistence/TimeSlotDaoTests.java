package ca.mcgill.ecse321.tutor.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.sql.Time;
import java.util.List;

import ca.mcgill.ecse321.tutor.service.TimeSlotService;
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
public class TimeSlotDaoTests {

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

  //  @Test
  //  public void testGetTimeSlot() {
  //    assertEquals(0, timeSlotService.getAllTimeSlots().size());
  //
  //    Time startTime = Time.valueOf("10:35:11");
  //    Time endTime = Time.valueOf("12:35:11");
  //    TimeSlot timeSlot = timeSlotService.createTimeSlot(startTime, endTime, DayOfTheWeek.MONDAY);
  //    List<TimeSlot> allTimeSlots = null;
  //    try {
  //      allTimeSlots = timeSlotService.getAllTimeSlots();
  //    }
  //    catch (IllegalArgumentException e) {
  //      fail();
  //    }
  //
  //    assertEquals(timeSlot.getId(), allTimeSlots.get(0).getId());
  //  }

}
