import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Room{
private Manager manager;

@ManyToOne(optional=false)
public Manager getManager() {
   return this.manager;
}

public void setManager(Manager manager) {
   this.manager = manager;
}

private Integer roomId;

public void setRoomId(Integer value) {
this.roomId = value;
    }
@Id
public Integer getRoomId() {
return this.roomId;
    }
private Integer roomCapacity;

public void setRoomCapacity(Integer value) {
   this.roomCapacity = value;
}

public Integer getRoomCapacity() {
   return this.roomCapacity;
}

private TutoringSession tutoringSession;

@OneToOne(optional=false)
public TutoringSession getTutoringSession() {
   return this.tutoringSession;
}

public void setTutoringSession(TutoringSession tutoringSession) {
   this.tutoringSession = tutoringSession;
}

}
