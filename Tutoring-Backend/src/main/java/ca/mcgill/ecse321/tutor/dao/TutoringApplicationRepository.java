package ca.mcgill.ecse321.tutor.dao;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.CourseLevel;
import ca.mcgill.ecse321.tutor.model.DayOfTheWeek;
import ca.mcgill.ecse321.tutor.model.Manager;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Rating;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

public class TutoringApplicationRepository {

	@Autowired
	EntityManager entityManager;

	@Transactional
	public Student createStudent(String firstName, String lastName, String email, Set<Booking> bookings, Set<Rating> ratings) {
		Student s = new Student();
		s.setFirstName(firstName);
		s.setLastName(lastName);
		s.setEmail(email);
		s.setBooking(bookings);
		s.setRating(ratings);
		entityManager.persist(s);
		return s;
	}

	@Transactional
	public Student getStudent(Integer studentId) {
		Student s = entityManager.find(Student.class, studentId);
		return s;
	}

	@Transactional
	public Tutor createTutor(Double hourlyRate, Boolean isApproved, Manager manager, Set<TutoringSession> tutoringSessions,
			Set<TimeSlot> timeSlots, Set<Rating> ratings, Set<Notification> notifications, Set<Course> courses) {
		Tutor t = new Tutor();
		t.setHourlyRate(hourlyRate);
		t.setIsApproved(isApproved);
		t.setManager(manager);
		t.setTutoringSession(tutoringSessions);
		t.setTimeSlot(timeSlots);
		t.setRating(ratings);
		t.setNotification(notifications);
		t.setCourse(courses);
		entityManager.persist(t);
		return t;
	}

	@Transactional
	public Tutor getTutor(Integer tutorId) {
		Tutor t = entityManager.find(Tutor.class, tutorId);
		return t;
	}

	@Transactional
	public Course createCourse(String courseName, CourseLevel level, Set<Tutor> tutors, Set<Booking> booking) {
		Course c = new Course();
		c.setName(courseName);
		c.setLevel(level);
		c.setTutor(tutors);
		c.setBooking(booking);
		entityManager.persist(c);
		return c;
	}

	@Transactional
	public Course getCourse(Integer courseId) {
		Course c = entityManager.find(Course.class, courseId);
		return c;
	}

	@Transactional
	public TimeSlot createTimeSlot(Time startTime, Time endTime, DayOfTheWeek dayOfTheWeek,
			Set<TutoringSession> tutoringSessions, Tutor tutor) {
		TimeSlot t = new TimeSlot();
		t.setStartTime(startTime);
		t.setEndTime(endTime);
		t.setDayOfTheWeek(dayOfTheWeek);
		t.setTutoringSession(tutoringSessions);
		t.setTutor(tutor);
		entityManager.persist(t);
		return t;
	}

	@Transactional
	public TimeSlot getTimeSlot(Integer timeSlotId) {
		TimeSlot t = entityManager.find(TimeSlot.class, timeSlotId);
		return t;
	}

	@Transactional
	public TutoringSession createTutoringSession(Date date, TimeSlot timeSlot,
			Tutor tutor, Room room, Booking booking) {
		TutoringSession t = new TutoringSession();
		t.setDate(date);
		t.setTimeSlot(timeSlot);
		t.setTutor(tutor);
		t.setRoom(room);
		t.setBooking(booking);
		entityManager.persist(t);
		return t;
	}

	@Transactional
	public TutoringSession getTutoringSession(Integer tutoringSessionId) {
		TutoringSession t = entityManager.find(TutoringSession.class, tutoringSessionId);
		return t;
	}

	@Transactional
	public Rating createRating(Integer stars, String comment, Tutor tutor, Student student) {
		Rating r = new Rating();
		r.setStars(stars);
		r.setComment(comment);
		r.setTutor(tutor);
		r.setStudent(student);
		entityManager.persist(r);
		return r;
	}

	@Transactional
	public Rating getRating(Integer ratingId) {
		Rating r = entityManager.find(Rating.class, ratingId);
		return r;
	}

	@Transactional
	public Notification createNotification(Tutor tutor, Booking booking) {
		Notification n = new Notification();
		n.setTutor(tutor);
		n.setBooking(booking);
		entityManager.persist(n);
		return n;
	}

	@Transactional
	public Notification getNotification(Integer notificationId) {
		Notification n = entityManager.find(Notification.class, notificationId);
		return n;
	}

	@Transactional
	public Booking createBooking(String tutorEmail, Date specificDate, TimeSlot timeSlot, TutoringSession tutoringSession,
			Set<Student> students, Notification notification, Course course) {
		Booking b = new Booking();
		b.setTutorEmail(tutorEmail);
		b.setSpecificDate(specificDate);
		b.setTimeSlot(timeSlot);
		b.setTutoringSession(tutoringSession);
		b.setStudent(students);
		b.setNotification(notification);
		b.setCourse(course);
		entityManager.persist(b);
		return b;
	}

	@Transactional
	public Booking getBooking(Integer bookingId) {
		Booking b = entityManager.find(Booking.class, bookingId);
		return b;
	}

}
