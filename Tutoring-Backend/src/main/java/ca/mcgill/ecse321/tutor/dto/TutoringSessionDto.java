package ca.mcgill.ecse321.tutor.dto;

import java.util.Date;

import ca.mcgill.ecse321.tutor.model.Room;

public class TutoringSessionDto {
    private Date sessionDate;
    private Integer tutorId;
    private int roomNumber;
    private TimeSlotDto timeSlot;
    private int tutoringSessionId;

    public TutoringSessionDto(Date sessionDate, Integer tutorId, Room room, TimeSlotDto timeSlotDto, int tutoringSessionId) {
        this.sessionDate = sessionDate;
        this.tutorId = tutorId;
        this.roomNumber = room.getRoomNumber();
        this.timeSlot = timeSlotDto;
        this.tutoringSessionId = tutoringSessionId;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public Integer getTutor() {
        return tutorId;
    }

    public int getRoom() {
        return roomNumber;
    }

    public TimeSlotDto getTimeSlot() {
        return timeSlot;
    }


    public int getTutoringSessionId() {
        return tutoringSessionId;
    }
}
