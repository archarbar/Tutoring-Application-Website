package ca.mcgill.ecse321.tutor.dto;

import ca.mcgill.ecse321.tutor.model.Level;

public class CourseDto {
    private String courseName;
    private Level level;
    private int courseId;

    public CourseDto(String courseName, Level level, int courseId) {
        this.courseName = courseName;
        this.level = level;
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public Level getLevel() {
        return level;
    }

    public int getCourseId() {
        return courseId;
    }
}
