package ca.mcgill.ecse321.tutor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import ca.mcgill.ecse321.tutor.dao.RoomRepository;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Room;

@Service
public class RoomService {

	@Autowired
	RoomRepository roomRepository;

	@Transactional
	public Room createRoom(Integer roomNumber, Integer roomCapacity, Manager manager) {
		String error = "";
		if (roomNumber == null) {
			error = error + "A room number needs to be specified! ";
		}
		else if (roomNumber <= 0) {
			error = error + "The room number has to be bigger than 0! ";
		}
		if (roomCapacity == null) {
			error = error + "A room capacity needs to be specified! ";
		}
		else if (roomCapacity <= 0) {
			error = error + "The room capacity has to be bigger than 0! ";
		}
		if (manager == null) {
			error = error + "A manager needs to be specified!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Room room = new Room();
		room.setRoomNumber(roomNumber);
		room.setRoomCapacity(roomCapacity);
		room.setManager(manager);
		roomRepository.save(room);
		return room;
	}

	@Transactional
	public Room getRoom(Integer roomId) {
		if (roomId == null) {
			throw new IllegalArgumentException("A room ID needs to be specified!");
		}
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
