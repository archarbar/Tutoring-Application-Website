package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;

import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Room;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutor.dao.ManagerRepository;
import ca.mcgill.ecse321.tutor.dao.RoomRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoomServiceTests {

	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private ManagerRepository managerRepository;

	@Autowired
	private RoomService roomService;
	@Autowired
	private ManagerService managerService;

	@After
	public void clearDatabase() {
		roomRepository.deleteAll();
		managerRepository.deleteAll();
	}

	@Test
	public void testCreateRoom() { // test constructor method
		assertEquals(0, roomService.getAllRooms().size());

		Integer number = 12;
		Integer capacity = 30;
		Manager manager = managerService.createManager();

		try {
			roomService.createRoom(number, capacity , manager);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Room> allRooms = roomService.getAllRooms();

		assertEquals(1, allRooms.size());
		assertEquals((Integer) 12, allRooms.get(0).getRoomNumber());
		assertEquals((Integer) 30, allRooms.get(0).getRoomCapacity());
	}

	@Test
	public void testCreateRoomNull() {
		assertEquals(0, roomService.getAllRooms().size());

		Integer number = null;
		Integer capacity = null;
		Manager manager = null;
		String error = null;

		try {
			roomService.createRoom(number, capacity , manager);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A room number needs to be specified! A room capacity needs to be specified! A manager needs to be specified!", error);

		// check no change in memory
		assertEquals(0, roomService.getAllRooms().size());
	}

}