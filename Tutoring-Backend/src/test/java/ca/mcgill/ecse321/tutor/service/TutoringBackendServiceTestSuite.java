package ca.mcgill.ecse321.tutor.service;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ BookingServiceTests.class, CourseServiceTests.class, ManagerServiceTests.class,
		NotificationServiceTests.class, RatingServiceTests.class, RoomServiceTests.class, StudentServiceTests.class,
		TimeSlotServiceTests.class, TutoringSessionServiceTests.class, TutorServiceTests.class })
public class TutoringBackendServiceTestSuite {

}
