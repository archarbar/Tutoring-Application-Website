package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Room{
  private Integer roomId;

  public void setRoomId(Integer value) {
    this.roomId = value;
  }
  @Id
  @GeneratedValue()
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
  private Integer roomNumber;

  public void setRoomNumber(Integer value) {
    this.roomNumber = value;
  }
  public Integer getRoomNumber() {
    return this.roomNumber;
  }
  private Manager manager;

  @ManyToOne(optional=false)
  public Manager getManager() {
    return this.manager;
  }

  public void setManager(Manager manager) {
    this.manager = manager;
  }

  private Set<TutoringSession> tutoringSession;

  @OneToMany(mappedBy="room")
  public Set<TutoringSession> getTutoringSession() {
    return this.tutoringSession;
  }

  public void setTutoringSession(Set<TutoringSession> tutoringSessions) {
    this.tutoringSession = tutoringSessions;
  }

}
