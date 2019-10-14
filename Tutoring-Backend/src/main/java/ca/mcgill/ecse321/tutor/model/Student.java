package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
public class Student{

	@Id
	@GeneratedValue
	@Column(name = "STUDENT ID")
	private Integer studentId;
	@Column(name = "FIRST NAME")
	private String firstName;
	@Column(name = "LAST NAME")
	private String lastName;
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "BOOKINGS")
	private Set<Booking> booking;
	@Column(name = "RATINGS")
	private Set<Rating> rating;

	public void setFirstName(String value) {
		this.firstName = value;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setLastName(String value) {
		this.lastName = value;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setEmail(String value) {
		this.email = value;
	}

	public String getEmail() {
		return this.email;
	}

	@ManyToMany(mappedBy="student")
	public Set<Booking> getBooking() {
		return this.booking;
	}

	public void setBooking(Set<Booking> bookings) {
		this.booking = bookings;
	}

	public void setStudentId(Integer value) {
		this.studentId = value;
	}

	public Integer getStudentId() {
		return this.studentId;
	}

	@OneToMany(mappedBy="student")
	public Set<Rating> getRating() {
		return this.rating;
	}

	public void setRating(Set<Rating> ratings) {
		this.rating = ratings;

	}

}
