package ca.mcgill.ecse321.tutor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ RoomTests.class, StudentTests.class, TimeSlotTests.class, TutoringSessionTests.class,
		TutorTests.class })
public class TutoringApplicationTests {
	
}
