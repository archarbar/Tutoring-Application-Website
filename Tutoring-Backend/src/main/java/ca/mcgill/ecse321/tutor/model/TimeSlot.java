import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;
import java.sql.Time;

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

private Time startTime;

public void setStartTime(Time value) {
this.startTime = value;
    }
public Time getStartTime() {
return this.startTime;
    }
private Time endTime;

public void setEndTime(Time value) {
this.endTime = value;
    }
public Time getEndTime() {
return this.endTime;
       }
   }
