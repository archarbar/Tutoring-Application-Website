package ca.mcgill.ecse321.tutor.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Booking{
  private Integer id;

  @Id
  @GeneratedValue()
  public Integer getId() {
    return this.id;
  }
  public void setId(Integer value) {
    this.id = value;
  }
  private Date specificDate;

  public void setSpecificDate(Date value) {
    this.specificDate = value;
  }
  public Date getSpecificDate() {
    return this.specificDate;
  }
  private Course course;

  @ManyToOne(optional=false)
  public Course getCourse() {
    return this.course;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  private Notification notification;

  @OneToOne(mappedBy = "booking", cascade = { CascadeType.ALL }, optional = false)
  public Notification getNotification() {
    return this.notification;
  }

  public void setNotification(Notification notification) {
    this.notification = notification;
  }

  private TutoringSession tutoringSession;

  @OneToOne
  public TutoringSession getTutoringSession() {
    return this.tutoringSession;
  }

  public void setTutoringSession(TutoringSession tutoringSession) {
    this.tutoringSession = tutoringSession;
  }

  private TimeSlot timeSlot;

  @JsonIgnore
  @ManyToOne(optional=false)
  public TimeSlot getTimeSlot() {
    return this.timeSlot;
  }

  public void setTimeSlot(TimeSlot timeSlot) {
    this.timeSlot = timeSlot;
  }

  private Set<Student> student;

  @JsonIgnore
  @ManyToMany
  public Set<Student> getStudent() {
    return this.student;
  }

  public void setStudent(Set<Student> students) {
    this.student = students;
  }
  
  private String tutorEmail;
  
  public String getTutorEmail() {
    return this.tutorEmail;
  }
  
  public void setTutorEmail(String tutorEmail) {
    this.tutorEmail = tutorEmail;
  }

}
