package ca.mcgill.ecse321.tutor.dto;

public class StudentDto {
    private String firstName;
    private String lastName;
    private String email;
    private int studentId;

    public StudentDto(String firstName, String lastName, String email, int studentId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getStudentId() {
        return studentId;
    }
}
