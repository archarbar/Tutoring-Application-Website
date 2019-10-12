package main.java.ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import main.java.ca.mcgill.ecse321.tutor.model.User;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Id;

@Entity
public class Tutor extends User{
private Manager manager;

@ManyToOne(optional=false)
public Manager getManager() {
   return this.manager;
}

public void setManager(Manager manager) {
   this.manager = manager;
}

private double hourlyRate;

public void setHourlyRate(double value) {
   this.hourlyRate = value;
}

public double getHourlyRate() {
   return this.hourlyRate;
}

private Set<TimeSlot> timeSlot;

@OneToMany(mappedBy="tutor")
public Set<TimeSlot> getTimeSlot() {
   return this.timeSlot;
}

public void setTimeSlot(Set<TimeSlot> timeSlots) {
   this.timeSlot = timeSlots;
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

private Set<Course> course;

@ManyToMany(mappedBy="tutor")
public Set<Course> getCourse() {
   return this.course;
}

public void setCourse(Set<Course> courses) {
   this.course = courses;
}

private Boolean isApproved;

public void setIsApproved(Boolean value) {
   this.isApproved = value;
}

public Boolean getIsApproved() {
   return this.isApproved;
}

private Integer tutorId;

public void setTutorId(Integer value) {
this.tutorId = value;
    }
@Id
public Integer getTutorId() {
return this.tutorId;
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
