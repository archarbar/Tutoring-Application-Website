package ca.mcgill.ecse321.tutor.model;

import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Manager{
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

	@Id
	@GeneratedValue
	private Integer managerId;

	public Integer getManagerId() {
		return this.managerId;
	}
}
