package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.sql.Time;

@Entity
@Table(name = "TIME SLOT")
public class TimeSlot{
	@Id
	@Column(name = "TIME SLOT ID")
	private Integer timeSlotId;
	@Column(name = "START TIME")
	private Time startTime;
	@Column(name = "END TIME")
	private Time endtime;
	@Column(name = "TUTOR")
	private Tutor tutor;
	@Column(name = "TUTORING SESSIONS")
	private Set<TutoringSession> tutoringSession;

	public void setTimeSlotId(Integer value) {
		this.timeSlotId = value;
	}

	public Integer getTimeSlotId() {
		return this.timeSlotId;
	}

	@ManyToOne(optional=false)
	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

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

	public void setStartTime(Time value) {
		this.startTime = value;
	}

	public Time getStartTime() {
		return this.startTime;
	}

	public void setEndTime(Time value) {
		this.endtime = value;
	}

	public Time getEndTime() {
		return this.endtime;
	}
}
