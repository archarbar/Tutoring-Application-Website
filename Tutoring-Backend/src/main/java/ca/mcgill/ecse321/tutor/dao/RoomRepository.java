package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Room;

public interface RoomRepository extends CrudRepository<Room, Integer> {
  
	Room findRoomById(Integer Id);
	
	Room findRoomByRoomNumber(Integer roomNumber);
	
}
