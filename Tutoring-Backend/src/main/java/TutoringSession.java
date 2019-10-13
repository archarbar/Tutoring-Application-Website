import javax.persistence.Entity;
import javax.persistence.OneToOne;
import ca.mcgill.ecse321.tutor.model.Tutor;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class TutoringSession{
private Room room;

@OneToOne(mappedBy="tutoringSession", optional=false)
public Room getRoom() {
   return this.room;
}

public void setRoom(Room room) {
   this.room = room;
}

private Tutor tutor;

@ManyToOne(optional=false)
public Tutor getTutor() {
   return this.tutor;
}

public void setTutor(Tutor tutor) {
   this.tutor = tutor;
}

private TimeSlot timeSlot;

@ManyToOne(optional=false)
public TimeSlot getTimeSlot() {
   return this.timeSlot;
}

public void setTimeSlot(TimeSlot timeSlot) {
   this.timeSlot = timeSlot;
}

private Integer tutoringSessionId;

public void setTutoringSessionId(Integer value) {
this.tutoringSessionId = value;
    }
@Id
public Integer getTutoringSessionId() {
return this.tutoringSessionId;
    }
private Booking booking;

@OneToOne(optional=false)
public Booking getBooking() {
   return this.booking;
}

public void setBooking(Booking booking) {
   this.booking = booking;
}

}
