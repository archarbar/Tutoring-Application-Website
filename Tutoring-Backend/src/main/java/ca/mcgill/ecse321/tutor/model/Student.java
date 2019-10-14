package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@Entity
public class Student{
  private String firstName;

  public void setFirstName(String value) {
    this.firstName = value;
  }
  public String getFirstName() {
    return this.firstName;
  }
  private String lastName;

  public void setLastName(String value) {
    this.lastName = value;
  }
  public String getLastName() {
    return this.lastName;
  }
  private String email;

  public void setEmail(String value) {
    this.email = value;
  }
  public String getEmail() {
    return this.email;
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
  private Set<Rating> rating;

  @OneToMany(mappedBy="student")
  public Set<Rating> getRating() {
    return this.rating;
  }

  public void setRating(Set<Rating> ratings) {
    this.rating = ratings;
  }

  private Set<Booking> booking;

  @ManyToMany(mappedBy="student")
  public Set<Booking> getBooking() {
    return this.booking;
  }

  public void setBooking(Set<Booking> bookings) {
    this.booking = bookings;
  }

}
