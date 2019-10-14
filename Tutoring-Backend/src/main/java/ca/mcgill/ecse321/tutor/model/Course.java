package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.Id;

@Entity
@Table(name = "COURSE")
public class Course{

	@Column(name = "COURSE NAME")
	private String name;
	@Column(name = "COURSE LEVEL")
	private CourseLevel level;
	@Id
	@Column(name = "COURSE ID")
	private Integer courseId;
	@Column(name  = "BOOKINGS")
	private Set<Booking> booking;
	@Column(name = "TUTORS")
	private Set<Tutor> tutor;
	
	public void setName(String value) {
	   this.name = value;
	}
	
	public String getName() {
	   return this.name;
	}
	
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
	
	public void setLevel(CourseLevel value) {
	   this.level = value;
	}
	
	public CourseLevel getLevel() {
	   return this.level;
	}
	
	@ManyToMany
	public Set<Tutor> getTutor() {
	   return this.tutor;
	}
	
	public void setTutor(Set<Tutor> tutors) {
	   this.tutor = tutors;
	}
	
	public void setCourseId(Integer value) {
	this.courseId = value;
	    }
	public Integer getCourseId() {
	return this.courseId;
	       }
}
