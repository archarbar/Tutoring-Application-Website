package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class Person {
  private Integer id;

  public void setId(Integer value) {
    this.id = value;
  }
  
  @Id
  @GeneratedValue()
  public Integer getId() {
    return this.id;
  }
  private String email;

  public void setEmail(String value) {
    this.email = value;
  }
  public String getEmail() {
    return this.email;
  }
  private String password;

  public void setPassword(String value) {
    this.password = value;
  }
  public String getPassword() {
    return this.password;
  }
  private String firstName;

  public void setFirstName(String value) {
    this.firstName = value;
  }
  public String getFirstName() {
    return this.firstName;
  }
  private String lastName;

  public void setLastName(String value) {
    this.lastName = value;
  }
  public String getLastName() {
    return this.lastName;
  }
}
