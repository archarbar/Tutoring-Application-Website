package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class TimeSlot{
private Integer timeSlotId;
   
   public void setTimeSlotId(Integer value) {
this.timeSlotId = value;
    }
@Id
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
	}
