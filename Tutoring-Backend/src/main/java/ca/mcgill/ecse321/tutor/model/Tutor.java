package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import ca.mcgill.ecse321.tutor.model.User;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "TUTOR")
public class Tutor extends User{
//	@Id
	@Column(name = "TUTOR ID")
	private Integer tutorId;
	@Column(name = "MANAGER")
	private Manager manager;
	@Column(name = "HOURLY RATE")
	private double hourlyRate;
	@Column(name = "COURSES TAUGHT")
	private Set<Course> course;
	@Column(name = "TIME SLOTS")
	private Set<TimeSlot> timeSlot;
	@Column(name = "TUTORING SESSIONS")
	private Set<TutoringSession> tutoringSession;
	@Column(name = "RATINGS")
	private Set<Rating> rating;
	
	@ManyToOne(optional=false)
	public Manager getManager() {
	   return this.manager;
	}
	
	public void setManager(Manager manager) {
	   this.manager = manager;
	}
	
	public void setHourlyRate(double value) {
	   this.hourlyRate = value;
	}
	
	public double getHourlyRate() {
	   return this.hourlyRate;
	}
	
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
	
	@OneToMany(mappedBy="tutor")
	public Set<TutoringSession> getTutoringSession() {
	   return this.tutoringSession;
	}
	
	public void setTutoringSession(Set<TutoringSession> tutoringSessions) {
	   this.tutoringSession = tutoringSessions;
	}
	
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
	
	public void setTutorId(Integer value) {
	this.tutorId = value;
	    }
	
	public Integer getTutorId() {
	return this.tutorId;
	    }
	
	@OneToMany(mappedBy="tutor")
	public Set<Rating> getRating() {
	   return this.rating;
	}
	
	public void setRating(Set<Rating> ratings) {
	   this.rating = ratings;
	}

}
