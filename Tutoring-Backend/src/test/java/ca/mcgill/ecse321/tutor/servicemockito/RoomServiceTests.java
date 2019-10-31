package ca.mcgill.ecse321.tutor.servicemockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
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

	private Manager manager;

	private Room room = new Room();

	private static final Integer SUCCESS_KEY = 1;
	private static final Integer ROOM_NUMBER = 12;
	private static final Integer ROOM_CAPACITY = 30;

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

	@Before
	public void setUpMocks() {
		manager = mock(Manager.class);
	}

	@Test
	public void testGetRoom(){
		assertEquals(SUCCESS_KEY, roomService.getRoom(SUCCESS_KEY).getId());
		assertEquals(SUCCESS_KEY, roomService.getAllRooms().get(0).getId());
	}

	@Test
	public void testCreateRoom() { // test constructor method

		try {
			room = roomService.createRoom(ROOM_NUMBER, ROOM_CAPACITY , manager);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(manager.getId(), room.getManager().getId());
		assertEquals(ROOM_NUMBER, room.getRoomNumber());
		assertEquals(ROOM_CAPACITY, room.getRoomCapacity());
	}

	@Test
	public void testCreateRoomNull() { // test constructor method
		String error = null;

		try {
			room = roomService.createRoom(null, null , null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A room number needs to be specified! A room capacity needs to be specified! A manager needs to be specified!", error);
	}
	
	@Test
	public void testCreateRoomNullNumber() { // test constructor method
		String error = null;

		try {
			room = roomService.createRoom(null, ROOM_CAPACITY , manager);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A room number needs to be specified!", error);
	}
	
	@Test
	public void testCreateRoomNullCapacity() { // test constructor method
		String error = null;

		try {
			room = roomService.createRoom(ROOM_NUMBER, null , manager);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A room capacity needs to be specified!", error);
	}
	
	@Test
	public void testCreateRoomNullManager() { // test constructor method
		String error = null;

		try {
			room = roomService.createRoom(ROOM_NUMBER, ROOM_CAPACITY , null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		// check error
		assertEquals("A manager needs to be specified!", error);
	}

}
