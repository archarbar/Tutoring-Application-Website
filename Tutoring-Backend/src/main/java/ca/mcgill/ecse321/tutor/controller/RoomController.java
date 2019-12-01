package ca.mcgill.ecse321.tutor.controller;

import ca.mcgill.ecse321.tutor.dto.RoomDto;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * This class provides controller methods for the room class
 * 
 * @author Tony Ou
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class RoomController {
    @Autowired
    private RoomService service;

    /**
     * API call to retrieve a room using its id
     * 
     * @param roomId The ID of the room
     * @return A room DTO
     */
    
    @GetMapping("/room/{roomId}")
    public RoomDto getRoom(@PathVariable String roomId) {
        return convertToDto(service.getRoom(Integer.parseInt(roomId)));
    }
    
    /**
     * Method to convert a room object into a room DTO
     * 
     * @param room A room object
     * @return A room DTO
     */

    private RoomDto convertToDto(Room room) {
        if (room == null) throw new IllegalArgumentException("This room does not exist!");
        return new RoomDto(room.getRoomNumber(), room.getRoomCapacity(), room.getManager(), room.getId());
    }
}
