package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;


@Entity
@Table(name = "RATING")
public class Rating{

	@Id
	@GeneratedValue
	@Column(name = "RATING ID")
	private Integer ratingId;
	@Column(name = "STARS")
	private Integer stars;
	@Column(name = "COMMENT")
	private String comment;
	@Column(name = "STUDENT")
	private Student student;
	@Column(name = "TUTOR")
	private Tutor tutor;

	public void setStars(Integer value) {
		this.stars = value;
	}

	public Integer getStars() {
		return this.stars;
	}

	public void setComment(String value) {
		this.comment = value;
	}

	public String getComment() {
		return this.comment;
	}

	public Integer getRatingId() {
		return this.ratingId;
	}

	@ManyToOne(optional=false)
	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	@ManyToOne(optional=false)
	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;

	}

}
