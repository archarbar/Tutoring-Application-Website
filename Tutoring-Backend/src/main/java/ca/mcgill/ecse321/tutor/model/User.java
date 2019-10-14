package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public abstract class User{
private Integer userId;
   
   public void setUserId(Integer value) {
this.userId = value;
    }
@Id
@GeneratedValue()public Integer getUserId() {
return this.userId;
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
