package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
@Table(name = "NOTIFICATION")
public class Notification{

	@Id
	@GeneratedValue
	@Column(name = "NOTIFICATION ID")
	private Integer notificationId;
	@Column(name = "TUTOR")
	private Tutor tutor;
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
	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	public Integer getNotificationId() {
		return this.notificationId;
	}

}
