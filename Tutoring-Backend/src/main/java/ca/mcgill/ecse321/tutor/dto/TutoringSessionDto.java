package ca.mcgill.ecse321.tutor.dto;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;

import java.util.Date;

public class TutoringSessionDto {
    private Date sessionDate;
    private Tutor tutor;
    private Room room;
    private TimeSlot timeSlot;
    private Booking booking;
    private int tutoringSessionId;

    public TutoringSessionDto(Date sessionDate, Tutor tutor, Room room, TimeSlot timeSlot, Booking booking, int tutoringSessionId) {
        this.sessionDate = sessionDate;
        this.tutor = tutor;
        this.room = room;
        this.timeSlot = timeSlot;
        this.booking = booking;
        this.tutoringSessionId = tutoringSessionId;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public Room getRoom() {
        return room;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Booking getBooking() {
        return booking;
    }

    public int getTutoringSessionId() {
        return tutoringSessionId;
    }
}
