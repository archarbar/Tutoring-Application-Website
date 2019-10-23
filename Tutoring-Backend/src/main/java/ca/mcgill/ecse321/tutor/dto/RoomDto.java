package ca.mcgill.ecse321.tutor.dto;

import ca.mcgill.ecse321.tutor.model.Manager;

public class RoomDto {
    private int roomNumber;
    private int roomCapacity;
    private Manager manager;
    private int roomId;

    public RoomDto(int roomNumber, int roomCapacity, Manager manager, int roomId) {
        this.roomNumber = roomNumber;
        this.roomCapacity = roomCapacity;
        this.manager = manager;
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getRoomCapacity() {
        return roomCapacity;
    }

    public int getRoomId() {
        return roomId;
    }

    public Manager getManager() {
        return manager;
    }
}
