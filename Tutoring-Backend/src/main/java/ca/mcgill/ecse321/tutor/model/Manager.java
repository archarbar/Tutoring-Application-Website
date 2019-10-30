package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import ca.mcgill.ecse321.tutor.model.Tutor;
import java.util.Set;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Manager{
  private Integer id;

  public void setId(Integer value) {
    this.id = value;
  }
  @Id
  @GeneratedValue()
  public Integer getId() {
    return this.id;
  }
  private Set<Tutor> tutor;
  
  @JsonIgnore
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
