package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;

@Entity
public class Rating{
	private Integer stars;

	public void setStars(Integer value) {
		this.stars = value;
	}

	public Integer getStars() {
		return this.stars;
	}

	private String comment;

	public void setComment(String value) {
		this.comment = value;
	}

	public String getComment() {
		return this.comment;
	}

	@Id
	@GeneratedValue
	private Integer ratingId;

	public Integer getRatingId() {
		return this.ratingId;
	}
	private Tutor tutor;

	@ManyToOne(optional=false)
	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	private Student student;

	@ManyToOne(optional=false)
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
