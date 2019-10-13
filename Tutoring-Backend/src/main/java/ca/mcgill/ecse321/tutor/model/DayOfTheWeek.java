package ca.mcgill.ecse321.tutor.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public enum DayOfTheWeek{
  SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
}
