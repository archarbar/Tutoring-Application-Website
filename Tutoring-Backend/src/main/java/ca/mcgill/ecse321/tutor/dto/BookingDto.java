package ca.mcgill.ecse321.tutor.dto;

import java.sql.Date;

public class BookingDto {
    private String tutorEmail;
    private Date specificDate;
    private TimeSlotDto timeSlot;
    private String studentName;
    private String course;
    private int bookingId;

    public BookingDto(int bookingId, String tutorEmail, Date specificDate, TimeSlotDto timeSlotDto, String studentName, String courseName) {
        this.bookingId = bookingId;
        this.tutorEmail = tutorEmail;
        this.specificDate = specificDate;
        this.timeSlot = timeSlotDto;
        this.studentName = studentName;
        this.course = courseName;
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

    public TimeSlotDto getTimeSlot() {
        return timeSlot;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getCourse() {
        return course;
    }
}
