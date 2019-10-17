package ca.mcgill.ecse321.tutor.dto;

import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

public class RatingDto {
    private int stars;
    private String comment;
    private Student student;
    private Tutor tutor;
    private TutoringSession tutoringSession;
    private int ratingId;

    public RatingDto(int stars, String comment, Student student, Tutor tutor, TutoringSession tutoringSession, int ratingId) {
        this.stars = stars;
        this.comment = comment;
        this.student = student;
        this.tutor = tutor;
        this.tutoringSession = tutoringSession;
        this.ratingId = ratingId;
    }

    public int getStars() {
        return stars;
    }

    public String getComment() {
        return comment;
    }

    public Student getStudent() {
        return student;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public TutoringSession getTutoringSession() {
        return tutoringSession;
    }

    public int getRatingId() {
        return ratingId;
    }
}
