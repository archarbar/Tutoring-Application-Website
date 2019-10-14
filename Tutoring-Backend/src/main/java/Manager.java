import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Manager{
private Integer managerId;
   
   public void setManagerId(Integer value) {
this.managerId = value;
    }
@Id
@GeneratedValue()public Integer getManagerId() {
return this.managerId;
    }
private Set<Tutor> tutor;

@OneToMany(mappedBy="manager")
public Set<Tutor> getTutor() {
   return this.tutor;
}

public void setTutor(Set<Tutor> tutors) {
   this.tutor = tutors;
}

private Set<Room> room;

@OneToMany(mappedBy="manager")
public Set<Room> getRoom() {
   return this.room;
}

public void setRoom(Set<Room> rooms) {
   this.room = rooms;
}

}
