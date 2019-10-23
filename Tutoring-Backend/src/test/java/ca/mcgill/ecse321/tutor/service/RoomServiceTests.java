package ca.mcgill.ecse321.tutor.service;

import ca.mcgill.ecse321.tutor.dao.RoomRepository;
import ca.mcgill.ecse321.tutor.model.Room;
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
public class RoomServiceTests {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

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

}
