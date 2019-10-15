package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import ca.mcgill.ecse321.tutor.model.Tutor;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Notification{
  private Integer id;

  public void setId(Integer value) {
    this.id = value;
  }
  @Id
  @GeneratedValue()
  public Integer getId() {
    return this.id;
  }
  private Boolean isRead;

  public void setIsRead(Boolean value) {
    this.isRead = value;
  }
  public Boolean getIsRead() {
    return this.isRead;
  }
  private Tutor tutor;

  @ManyToOne(optional=false)
  public Tutor getTutor() {
    return this.tutor;
  }

  public void setTutor(Tutor tutor) {
    this.tutor = tutor;
  }

  private Booking booking;

  @OneToOne(optional=false)
  public Booking getBooking() {
    return this.booking;
  }

  public void setBooking(Booking booking) {
    this.booking = booking;
  }

}
