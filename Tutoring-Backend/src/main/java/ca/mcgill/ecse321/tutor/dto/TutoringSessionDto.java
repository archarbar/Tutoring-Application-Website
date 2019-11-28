package ca.mcgill.ecse321.tutor.dto;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;

import java.util.Date;

public class TutoringSessionDto {
    private Date sessionDate;
    private Integer tutorId;
    private int roomNumber;
    private TimeSlotDto timeSlot;
    private Integer booking;
    private int tutoringSessionId;

    public TutoringSessionDto(Date sessionDate, Integer tutorId, Room room, TimeSlotDto timeSlotDto, Booking booking, int tutoringSessionId) {
        this.sessionDate = sessionDate;
        this.tutorId = tutorId;
        this.roomNumber = room.getRoomNumber();
        this.timeSlot = timeSlotDto;
        this.booking = booking.getId();
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

    public Integer getBooking() {
        return booking;
    }

    public int getTutoringSessionId() {
        return tutoringSessionId;
    }
}
