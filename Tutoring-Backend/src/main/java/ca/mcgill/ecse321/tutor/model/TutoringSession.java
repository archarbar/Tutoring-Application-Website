package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import java.sql.Date;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "TUTORING SESSION")
public class TutoringSession{

	@Id
	@GeneratedValue
	@Column(name = "TUTORING SESSION ID")
	private Integer tutoringSessionId;
	@Column(name = "TUTOR")
	private Tutor tutor;
	@Column(name = "DATE")
	private Date date;
	@Column(name = "TIME SLOT")
	private TimeSlot timeSlot;
	@Column(name = "ROOM")
	private Room room;
	@Column(name = "BOOKING")
	private Booking booking;

	@OneToOne(optional=false)
	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	@ManyToOne(optional=false)
	public TimeSlot getTimeSlot() {
		return this.timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	@ManyToOne(optional=false)
	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	@OneToOne(mappedBy="tutoringSession", optional=false)
	public Room getRoom() {
		return this.room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public void setTutoringSessionId(Integer value) {
		this.tutoringSessionId = value;
	}

	public Integer getTutoringSessionId() {
		return this.tutoringSessionId;
	}

	public void setDate(Date value) {
		this.date = value;
	}

	public Date getDate() {
		return this.date;
	}
}
