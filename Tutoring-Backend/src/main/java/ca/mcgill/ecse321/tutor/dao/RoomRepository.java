package main.java.ca.mcgill.ecse321.tutor.dao;

import main.java.ca.mcgill.ecse321.tutor.model.Room;
import main.java.ca.mcgill.ecse321.tutor.model.TutoringSession;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Integer> {
	Room findRoomById(Integer Id);
	Room findByTutoringSession(TutoringSession tutoringSession);
}
