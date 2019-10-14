package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.sql.Time;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import ca.mcgill.ecse321.tutor.model.Tutor;
import javax.persistence.ManyToMany;

@Entity
public class TimeSlot{
  private Integer timeSlotId;

  public void setTimeSlotId(Integer value) {
    this.timeSlotId = value;
  }
  @Id
  @GeneratedValue()
  public Integer getTimeSlotId() {
    return this.timeSlotId;
  }
  private Time startTime;

  public void setStartTime(Time value) {
    this.startTime = value;
  }
  public Time getStartTime() {
    return this.startTime;
  }
  private Time endTime;

  public void setEndTime(Time value) {
    this.endTime = value;
  }
  public Time getEndTime() {
    return this.endTime;
  }
  private Set<TutoringSession> tutoringSession;

  @OneToMany(mappedBy="timeSlot")
  public Set<TutoringSession> getTutoringSession() {
    return this.tutoringSession;
  }

  public void setTutoringSession(Set<TutoringSession> tutoringSessions) {
    this.tutoringSession = tutoringSessions;
  }

  private Booking booking;

  @OneToOne(mappedBy="timeSlot", optional=false)
  public Booking getBooking() {
    return this.booking;
  }

  public void setBooking(Booking booking) {
    this.booking = booking;
  }

  private Set<Tutor> tutor;

  @ManyToMany(mappedBy="timeSlot")
  public Set<Tutor> getTutor() {
    return this.tutor;
  }

  public void setTutor(Set<Tutor> tutors) {
    this.tutor = tutors;
  }

}
