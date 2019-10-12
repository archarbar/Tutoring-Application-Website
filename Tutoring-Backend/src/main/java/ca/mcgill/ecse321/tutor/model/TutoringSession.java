package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class TutoringSession{
private Booking booking;

@OneToOne(optional=false)
public Booking getBooking() {
   return this.booking;
}

public void setBooking(Booking booking) {
   this.booking = booking;
}

private TimeSlot timeSlot;

@ManyToOne(optional=false)
public TimeSlot getTimeSlot() {
   return this.timeSlot;
}

public void setTimeSlot(TimeSlot timeSlot) {
   this.timeSlot = timeSlot;
}

private Tutor tutor;

@ManyToOne(optional=false)
public Tutor getTutor() {
   return this.tutor;
}

public void setTutor(Tutor tutor) {
   this.tutor = tutor;
}

private Room room;

@OneToOne(mappedBy="tutoringSession", optional=false)
public Room getRoom() {
   return this.room;
}

public void setRoom(Room room) {
   this.room = room;
}

private Integer tutoringSessionId;

public void setTutoringSessionId(Integer value) {
this.tutoringSessionId = value;
    }
@Id
public Integer getTutoringSessionId() {
return this.tutoringSessionId;
    }
private Date date;

public void setDate(Date value) {
this.date = value;
    }
public Date getDate() {
return this.date;
       }
   }
