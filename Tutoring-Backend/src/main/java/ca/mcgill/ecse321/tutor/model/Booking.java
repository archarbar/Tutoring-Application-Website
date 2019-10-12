package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Booking{
private Course course;

@ManyToOne(optional=false)
public Course getCourse() {
   return this.course;
}

public void setCourse(Course course) {
   this.course = course;
}

private String tutorEmail;

public void setTutorEmail(String value) {
   this.tutorEmail = value;
}

public String getTutorEmail() {
   return this.tutorEmail;
}

/**
 * <pre>
 *           1..1     1..1
 * Booking ------------------------> Date
 *           &lt;       specificDate
 * </pre>
 */
private Date specificDate;

public void setSpecificDate(Date value) {
   this.specificDate = value;
}

public Date getSpecificDate() {
   return this.specificDate;
}

private TimeSlot timeSlot;

@OneToOne(optional=false)
public TimeSlot getTimeSlot() {
   return this.timeSlot;
}

public void setTimeSlot(TimeSlot timeSlot) {
   this.timeSlot = timeSlot;
}

private Notification notification;

@OneToOne(mappedBy="booking", optional=false)
public Notification getNotification() {
   return this.notification;
}

public void setNotification(Notification notification) {
   this.notification = notification;
}

private Set<Student> student;

@ManyToMany
public Set<Student> getStudent() {
   return this.student;
}

public void setStudent(Set<Student> students) {
   this.student = students;
}

private TutoringSession tutoringSession;

@OneToOne(mappedBy="booking", optional=false)
public TutoringSession getTutoringSession() {
   return this.tutoringSession;
}

public void setTutoringSession(TutoringSession tutoringSession) {
   this.tutoringSession = tutoringSession;
}

}
