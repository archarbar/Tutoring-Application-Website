package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import java.util.Set;
import javax.persistence.OneToMany;

import java.sql.Time;

@Entity
public class TimeSlot{
	
	@Id
	@GeneratedValue
	private Integer timeSlotId;

	public Integer getTimeSlotId() {
		return this.timeSlotId;
	}
	private Tutor tutor;

	@ManyToOne(optional=false)
	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	private Set<TutoringSession> tutoringSession;

	@OneToMany(mappedBy="timeSlot")
	public Set<TutoringSession> getTutoringSession() {
		return this.tutoringSession;
	}

	public void setTutoringSession(Set<TutoringSession> tutoringSessions) {
		this.tutoringSession = tutoringSessions;
	}

	private DayOfTheWeek dayOfTheWeek;

	public void setDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public DayOfTheWeek getDayOfTheWeek() {
		return this.dayOfTheWeek;
	}

	private Time startTime;

	public void setStartTime(Time value) {
		this.startTime = value;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	private Time endtime;

	public void setEndTime(Time value) {
		this.endtime = value;
	}

	public Time getEndTime() {
		return this.endtime;
	}
}
