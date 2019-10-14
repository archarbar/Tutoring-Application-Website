package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.RoomRepository;
import ca.mcgill.ecse321.tutor.model.Room;


public class RoomService {

	@Autowired
	RoomRepository roomRepository;

	@Transactional
	public Room createRoom(Integer roomNumber, Integer roomCapacity) {
		if (roomNumber == null) {
			throw new IllegalArgumentException("A room number needs to be specified!");
		}
		if (roomCapacity == null) {
			throw new IllegalArgumentException("A room capacity needs to be specified!");
		}
		Room room = new Room();
		room.setFirstName(roomNumber);
		room.setLastName(roomCapacity);
		roomRepository.save(room);
		return room;
	}

	@Transactional
	public Room getManager(Integer roomId) {
		Room room = roomRepository.findRoomById(roomId);
		return room;
	}

	@Transactional
	public List<Room> getAllRooms(){
		return toList(roomRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

}
