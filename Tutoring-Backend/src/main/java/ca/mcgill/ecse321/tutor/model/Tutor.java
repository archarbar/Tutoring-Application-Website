package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import ca.mcgill.ecse321.tutor.model.Person;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("T")
public class Tutor extends Person{
  private Boolean isApproved;

  public void setIsApproved(Boolean value) {
    this.isApproved = value;
  }
  public Boolean getIsApproved() {
    return this.isApproved;
  }
  private double hourlyRate;

  public void setHourlyRate(double value) {
    this.hourlyRate = value;
  }
  public double getHourlyRate() {
    return this.hourlyRate;
  }
  private Manager manager;

  @ManyToOne(optional=false)
  public Manager getManager() {
    return this.manager;
  }

  public void setManager(Manager manager) {
    this.manager = manager;
  }

  private Set<Course> course;

  @ManyToMany
  public Set<Course> getCourse() {
    return this.course;
  }

  public void setCourse(Set<Course> courses) {
    this.course = courses;
  }

  private Set<Notification> notification;

  @OneToMany(mappedBy="tutor")
  public Set<Notification> getNotification() {
    return this.notification;
  }

  public void setNotification(Set<Notification> notifications) {
    this.notification = notifications;
  }

  private Set<TutoringSession> tutoringSession;

  @OneToMany(mappedBy="tutor")
  public Set<TutoringSession> getTutoringSession() {
    return this.tutoringSession;
  }

  public void setTutoringSession(Set<TutoringSession> tutoringSessions) {
    this.tutoringSession = tutoringSessions;
  }

  private Set<TimeSlot> timeSlot;

  @ManyToMany
  public Set<TimeSlot> getTimeSlot() {
    return this.timeSlot;
  }

  public void setTimeSlot(Set<TimeSlot> timeSlots) {
    this.timeSlot = timeSlots;
  }

  private Set<Rating> rating;

  @OneToMany(mappedBy="tutor")
  public Set<Rating> getRating() {
    return this.rating;
  }

  public void setRating(Set<Rating> ratings) {
    this.rating = ratings;
  }

}
