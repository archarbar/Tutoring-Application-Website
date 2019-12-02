package ca.mcgill.ecse321.tutor.dto;

import ca.mcgill.ecse321.tutor.model.Manager;

public class TutorDto {
	private String tutorId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Manager manager;
    private double hourlyRate;
    private boolean isApproved;

    public TutorDto(String tutorId, String firstName, String lastName, String email, Manager manager, double hourlyRate, boolean isApproved) {
        this(tutorId, firstName, lastName, email,null, manager, hourlyRate, isApproved);
    }

    public TutorDto(String tutorId, String firstName, String lastName, String email, String password, Manager manager, double hourlyRate, boolean isApproved) {
        this.tutorId = tutorId;
    	this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.manager = manager;
        this.hourlyRate = hourlyRate;
        this.isApproved = isApproved;
    }

    public String getTutorId() {
    	return tutorId;
    }
    public Manager getManager() {
        return manager;
    }

    public boolean getisApproved() {
        return isApproved;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(int hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
