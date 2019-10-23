package ca.mcgill.ecse321.tutor.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class TutoringSession{
  private Date sessionDate;

  public void setSessionDate(Date value) {
    this.sessionDate = value;
  }
  public Date getSessionDate() {
    return this.sessionDate;
  }
  private Integer id;

  public void setId(Integer value) {
    this.id = value;
  }
  @Id
  @GeneratedValue()
  public Integer getId() {
    return this.id;
  }
  private Rating rating;

  @OneToOne(mappedBy="tutoringSession")
  public Rating getRating() {
    return this.rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
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
