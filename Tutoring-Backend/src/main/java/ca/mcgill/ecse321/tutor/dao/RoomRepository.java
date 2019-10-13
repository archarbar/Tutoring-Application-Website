package ca.mcgill.ecse321.tutor.dao;

<<<<<<< HEAD
public class RoomRepository {

=======
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

public interface RoomRepository extends CrudRepository<Room, Integer> {
	Room findRoomById(Integer Id);
	Room findByTutoringSession(TutoringSession tutoringSession);
>>>>>>> remove-gradleception
}
