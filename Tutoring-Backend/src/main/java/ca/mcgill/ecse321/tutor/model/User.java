package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User{
	@Id
	@Column(name = "EMAIL")
	private String email;
	@Column(name = "FIRST NAME")
	private String firstName;
	@Column(name = "LAST NAME")
	private String lastName;
	@Column(name = "PASSWORD")
	private String password;
	
	public void setEmail(String value) {
	   this.email = value;
	}
	
	public String getEmail() {
	   return this.email;
	}
	
	public void setPassword(String value) {
	   this.password = value;
	}
	
	public String getPassword() {
	   return this.password;
	}
	
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

}
