package ca.mcgill.ecse321.tutor.servicemockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import ca.mcgill.ecse321.tutor.dao.RoomRepository;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.service.ManagerService;
import ca.mcgill.ecse321.tutor.service.RoomService;


@RunWith(MockitoJUnitRunner.class)
public class RoomServiceTests {

	@Mock
	private RoomRepository roomRepository;

	@InjectMocks
	private RoomService roomService;
	@InjectMocks
	private ManagerService managerService;

	private Room room = new Room();

	private static final Integer SUCCESS_KEY = 1;

	@Before
	public void setMockOutput(){
		when(roomRepository.findRoomById(anyInt())).thenAnswer( (InvocationOnMock invocation) ->{
			if (invocation.getArgument(0).equals(SUCCESS_KEY)){
				room.setId(SUCCESS_KEY);
				return room;
			} else {
				return null;
			}
		});
		when(roomRepository.findAll()).thenAnswer( (InvocationOnMock invocation) ->{
			List<Room> rooms = new ArrayList<>();
			room.setId(SUCCESS_KEY);
			rooms.add(room);
			return rooms;
		});
	}

	@Test
	public void testGetRoom(){
		assertEquals(SUCCESS_KEY, roomService.getRoom(SUCCESS_KEY).getId());
		assertEquals(SUCCESS_KEY, roomService.getAllRooms().get(0).getId());
	}

	@Test
	public void testCreateRoom() { // test constructor method
		assertEquals(0, roomService.getAllRooms().size());

		Integer number = 12;
		Integer capacity = 30;
		Manager manager = managerService.createManager();

		try {
			room = roomService.createRoom(number, capacity , manager);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals((Integer) 12, room.getRoomNumber());
		assertEquals((Integer) 30, room.getRoomCapacity());
	}

	@Test
	public void testCreateRoomNull() { // test constructor method
		Integer number = null;
		Integer capacity = null;
		Manager manager = null;
		String error = null;

		try {
			room = roomService.createRoom(number, capacity , manager);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A room number needs to be specified! A room capacity needs to be specified! A manager needs to be specified!", error);
	}

}
