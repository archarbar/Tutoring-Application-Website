package ca.mcgill.ecse321.tutor.model;

import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Table(name = "MANAGER")
public class Manager{
	@Id
	@Column(name = "MANAGER ID")
	private Integer managerId;
	@Column(name = "TUTORS")
	private Set<Tutor> tutor;
	@Column(name = "ROOMS")
	private Set<Room> room;
	
	@OneToMany(mappedBy="manager")
	public Set<Tutor> getTutor() {
	   return this.tutor;
	}
	
	public void setTutor(Set<Tutor> tutors) {
	   this.tutor = tutors;
	}
	
	@OneToMany(mappedBy="manager")
	public Set<Room> getRoom() {
	   return this.room;
	}
	
	public void setRoom(Set<Room> rooms) {
	   this.room = rooms;
	}
	
	public void setManagerId(Integer value) {
		this.managerId = value;
	}
	public Integer getManagerId() {
		return this.managerId;
	}
}
