package ca.mcgill.ecse321.tutor.dao;

import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

public interface RoomRepository extends CrudRepository<Room, Integer> {
	Room findRoomById(Integer Id);
	Room findByTutoringSession(TutoringSession tutoringSession);
}
