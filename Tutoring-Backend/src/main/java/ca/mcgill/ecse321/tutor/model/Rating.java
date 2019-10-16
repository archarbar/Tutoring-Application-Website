package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import ca.mcgill.ecse321.tutor.model.Tutor;

@Entity
public class Rating{
  private Integer id;

  public void setId(Integer value) {
    this.id = value;
  }
  @Id
  @GeneratedValue()
  public Integer getId() {
    return this.id;
  }
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
  private TutoringSession tutoringSession;

  @ManyToOne(optional=false)
  public TutoringSession getTutoringSession() {
    return this.tutoringSession;
  }

  public void setTutoringSession(TutoringSession tutoringSession) {
    this.tutoringSession = tutoringSession;
  }

  private Student student;

  @ManyToOne(optional=false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  public Student getStudent() {
    return this.student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  private Tutor tutor;

  @ManyToOne(optional=false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  public Tutor getTutor() {
    return this.tutor;
  }

  public void setTutor(Tutor tutor) {
    this.tutor = tutor;
  }

}
