package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "ROOM")
public class Room{

	@Id
	@GeneratedValue
	@Column(name = "ROOM ID")
	private Integer roomId;
	@Column(name = "MANAGER")
	private Manager manager;
	@Column(name = "TUTORING SESSION")
	private TutoringSession tutoringSession;
	@Column(name = "ROOM CAPACITY")
	private Integer roomCapacity;

	@ManyToOne(optional=false)
	public Manager getManager() {
		return this.manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public void setRoomId(Integer value) {
		this.roomId = value;
	}
	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomCapacity(Integer value) {
		this.roomCapacity = value;
	}

	public Integer getRoomCapacity() {
		return this.roomCapacity;
	}

	@OneToOne(optional=false)
	public TutoringSession getTutoringSession() {
		return this.tutoringSession;
	}

	public void setTutoringSession(TutoringSession tutoringSession) {
		this.tutoringSession = tutoringSession;
	}

}
