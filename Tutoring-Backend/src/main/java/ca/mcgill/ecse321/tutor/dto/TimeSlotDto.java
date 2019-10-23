package ca.mcgill.ecse321.tutor.dto;

import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;

import java.sql.Time;

public class TimeSlotDto {
    private Time startTime;
    private Time endTime;
    private DayOfTheWeek dayOfTheWeek;
    private int TimeSlotId;

    public TimeSlotDto(Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek, int timeSlotId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfTheWeek = dayOfTheWeek;
        TimeSlotId = timeSlotId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public DayOfTheWeek getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public int getTimeSlotId() {
        return TimeSlotId;
    }
}
