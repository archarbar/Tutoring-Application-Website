package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User{
@Id
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
