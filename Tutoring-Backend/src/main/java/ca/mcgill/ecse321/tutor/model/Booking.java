package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.Set;
import java.sql.Date;
import javax.persistence.ManyToMany;

@Entity
@Table(name = "BOOKING")
public class Booking{
	@Id
	@Column(name = "BOOKING ID")
	private Integer bookingId;
	@Column(name = "TUTOR EMAIL")
	private String tutorEmail;
	@Column(name = "STUDENTS")
	private Set<Student> student;
	@Column(name = "COURSE")
	private Course course;
	@Column(name = "DATE")
	private Date specificDate;
	@Column(name = "TIME SLOT")
	private TimeSlot timeSlot;
	@Column(name = "TUTORING SESSION")
	private TutoringSession tutoringSession;

	@ManyToOne(optional=false)
	public Course getCourse() {
		return this.course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void setTutorEmail(String value) {
		this.tutorEmail = value;
	}

	public String getTutorEmail() {
		return this.tutorEmail;
	}

	/**
	 * <pre>
	 *           1..1     1..1
	 * Booking ------------------------> Date
	 *           &lt;       specificDate
	 * </pre>
	 */

	public void setSpecificDate(Date value) {
		this.specificDate = value;
	}

	public Date getSpecificDate() {
		return this.specificDate;
	}

	@OneToOne(optional=false)
	public TimeSlot getTimeSlot() {
		return this.timeSlot;
	}

	public void setTimeSlot(TimeSlot timeSlot) {
		this.timeSlot = timeSlot;
	}

	private Notification notification;

	@OneToOne(mappedBy="booking", optional=false)
	public Notification getNotification() {
		return this.notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	@ManyToMany
	public Set<Student> getStudent() {
		return this.student;
	}

	public void setStudent(Set<Student> students) {
		this.student = students;
	}

	@OneToOne(mappedBy="booking", optional=false)
	public TutoringSession getTutoringSession() {
		return this.tutoringSession;
	}

	public void setTutoringSession(TutoringSession tutoringSession) {
		this.tutoringSession = tutoringSession;
	}

	public void setBookingId(Integer value) {
		this.bookingId = value;
	}
	public Integer getBookingId() {
		return this.bookingId;
	}
}
