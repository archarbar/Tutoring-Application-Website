import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Notification{
private Integer notificationId;
   
   public void setNotificationId(Integer value) {
this.notificationId = value;
    }
@Id
@GeneratedValue()public Integer getNotificationId() {
return this.notificationId;
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

@OneToOne(mappedBy="notification", optional=false)
public Booking getBooking() {
   return this.booking;
}

public void setBooking(Booking booking) {
   this.booking = booking;
}

}
