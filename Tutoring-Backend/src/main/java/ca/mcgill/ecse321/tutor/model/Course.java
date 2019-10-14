package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Course{
	private String name;

	public void setName(String value) {
		this.name = value;
	}

	public String getName() {
		return this.name;
	}

	private Set<Booking> booking;

	@OneToMany(mappedBy="course")
	public Set<Booking> getBooking() {
		return this.booking;
	}

	public void setBooking(Set<Booking> bookings) {
		this.booking = bookings;
	}

	/**
	 * <pre>
	 *           1..1     1..1
	 * Course ------------------------> CourseLevel
	 *           &lt;       level
	 * </pre>
	 */
	private CourseLevel level;

	public void setLevel(CourseLevel value) {
		this.level = value;
	}

	public CourseLevel getLevel() {
		return this.level;
	}

	private Set<Tutor> tutor;

	@ManyToMany
	public Set<Tutor> getTutor() {
		return this.tutor;
	}

	public void setTutor(Set<Tutor> tutors) {
		this.tutor = tutors;
	}

	@Id
	@GeneratedValue
	private Integer courseId;

	public Integer getCourseId() {
		return this.courseId;
	}
}
