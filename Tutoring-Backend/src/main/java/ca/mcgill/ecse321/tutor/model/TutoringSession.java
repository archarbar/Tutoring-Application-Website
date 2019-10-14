package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import java.sql.Date;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.Set;
import javax.persistence.OneToMany;
import ca.mcgill.ecse321.tutor.model.Tutor;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class TutoringSession{
  private Date sessionDate;

  public void setSessionDate(Date value) {
    this.sessionDate = value;
  }
  public Date getSessionDate() {
    return this.sessionDate;
  }
  private Integer tutoringSessionId;

  public void setTutoringSessionId(Integer value) {
    this.tutoringSessionId = value;
  }
  @Id
  @GeneratedValue()
  public Integer getTutoringSessionId() {
    return this.tutoringSessionId;
  }
  private Set<Rating> rating;

  @OneToMany(mappedBy="tutoringSession")
  public Set<Rating> getRating() {
    return this.rating;
  }

  public void setRating(Set<Rating> ratings) {
    this.rating = ratings;
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

  @ManyToOne(optional=false)
  public Room getRoom() {
    return this.room;
  }

  public void setRoom(Room room) {
    this.room = room;
  }

  private TimeSlot timeSlot;

  @ManyToOne(optional=false)
  public TimeSlot getTimeSlot() {
    return this.timeSlot;
  }

  public void setTimeSlot(TimeSlot timeSlot) {
    this.timeSlot = timeSlot;
  }

  private Booking booking;

  @OneToOne(mappedBy="tutoringSession", optional=false)
  public Booking getBooking() {
    return this.booking;
  }

  public void setBooking(Booking booking) {
    this.booking = booking;
  }

}
