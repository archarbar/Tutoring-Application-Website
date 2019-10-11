import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
private Set<Booking> booking;

@ManyToMany(mappedBy="student")
public Set<Booking> getBooking() {
   return this.booking;
}

public void setBooking(Set<Booking> bookings) {
   this.booking = bookings;
}

private Integer studentId;

public void setStudentId(Integer value) {
this.studentId = value;
    }
@Id
public Integer getStudentId() {
return this.studentId;
    }
private Set<Rating> rating;

@OneToMany(mappedBy="student")
public Set<Rating> getRating() {
   return this.rating;
}

public void setRating(Set<Rating> ratings) {
   this.rating = ratings;
}

}
