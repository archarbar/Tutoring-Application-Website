import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Notification{
private Booking booking;

@OneToOne(optional=false)
public Booking getBooking() {
   return this.booking;
}

public void setBooking(Booking booking) {
   this.booking = booking;
}

private Tutor tutor;

@ManyToOne(optional=false)
public Tutor getTutor() {
   return this.tutor;
}

public void setTutor(Tutor tutor) {
   this.tutor = tutor;
}

private Integer notificationId;

public void setNotificationId(Integer value) {
this.notificationId = value;
    }
@Id
public Integer getNotificationId() {
return this.notificationId;
       }
   }
