package ca.mcgill.ecse321.tutor.model;

import java.sql.Time;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class TimeSlot{
  private Integer id;

  public void setId(Integer value) {
    this.id = value;
  }
  @Id
  @GeneratedValue()
  public Integer getId() {
    return this.id;
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

  private Set<Booking> booking;

  @OneToMany(mappedBy="timeSlot")
  public Set<Booking> getBooking() {
    return this.booking;
  }

  public void setBooking(Set<Booking> booking) {
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
  
  private DayOfTheWeek dayOfTheWeek;
  
  public DayOfTheWeek getDayOfTheWeek() {
    return this.dayOfTheWeek;
  }
  
  public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
    this.dayOfTheWeek = dayOfTheWeek;
  }

}
