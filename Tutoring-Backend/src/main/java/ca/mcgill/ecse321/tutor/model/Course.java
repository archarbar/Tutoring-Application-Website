package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import ca.mcgill.ecse321.tutor.model.Tutor;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Course{
  private Integer id;

  public void setId(Integer value) {
    this.id = value;
  }
  @Id
  @GeneratedValue()
  public Integer getId() {
    return this.id;
  }
  private String courseName;

  public void setCourseName(String value) {
    this.courseName = value;
  }
  public String getCourseName() {
    return this.courseName;
  }
  private Level courseLevel;

  public void setCourseLevel(Level value) {
    this.courseLevel = value;
  }
  public Level getCourseLevel() {
    return this.courseLevel;
  }
  private Set<Tutor> tutor;

  @ManyToMany(mappedBy="course")
  public Set<Tutor> getTutor() {
    return this.tutor;
  }

  public void setTutor(Set<Tutor> tutors) {
    this.tutor = tutors;
  }

  private Set<Booking> booking;

  @OneToMany(mappedBy="course")
  public Set<Booking> getBooking() {
    return this.booking;
  }

  public void setBooking(Set<Booking> bookings) {
    this.booking = bookings;
  }

}
