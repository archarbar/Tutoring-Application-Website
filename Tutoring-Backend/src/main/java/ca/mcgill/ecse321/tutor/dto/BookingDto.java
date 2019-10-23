package ca.mcgill.ecse321.tutor.dto;

import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;

import java.sql.Date;

public class BookingDto {
    private String tutorEmail;
    private Date specificDate;
    private TimeSlot timeSlot;
    private Student student;
    private Course course;
    private int bookingId;

    public BookingDto(int bookingId, String tutorEmail, Date specificDate, TimeSlot timeSlot, Student student, Course course) {
        this.bookingId = bookingId;
        this.tutorEmail = tutorEmail;
        this.specificDate = specificDate;
        this.timeSlot = timeSlot;
        this.student = student;
        this.course = course;
    }

    public int getbookingId() {
        return bookingId;
    }

    public String getTutorEmail() {
        return tutorEmail;
    }

    public Date getSpecificDate() {
        return specificDate;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }
}
