package ca.mcgill.ecse321.tutor.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
import ca.mcgill.ecse321.tutor.dao.NotificationRepository;
import ca.mcgill.ecse321.tutor.dao.TutorRepository;
import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Course;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Room;
import ca.mcgill.ecse321.tutor.model.Student;
import ca.mcgill.ecse321.tutor.model.TimeSlot;
import ca.mcgill.ecse321.tutor.model.Tutor;
import ca.mcgill.ecse321.tutor.model.TutoringSession;

@Service
public class BookingService {

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	TutorRepository tutorRepository;
	
	@Autowired
	NotificationRepository notificationRepository;
	
	@Autowired
	NotificationService notificationService;
	
	@Autowired
	RoomService roomService;
	@Autowired
	TutorService tutorService;
	
	@Autowired
	TutoringSessionService tutoringSessionService;
	
	@Transactional
	public Booking createBooking(String tutorEmail, Set<Student> studentSet, Date specificDate, 
			TimeSlot timeSlot, Course course) {
		String error = "";
		if (tutorEmail == null || tutorEmail.trim().length() == 0) {
			error = error + "A tutor email needs to be specified! ";
		}
		if (studentSet == null) {
			error = error + "A student needs to be specified! ";
		}
		if (specificDate == null) {
			error = error + "A date needs to be specified! ";
		}
		if (timeSlot == null) {
			error = error + "A time slot needs to be specified! ";
		}
		if (course == null) {
			error = error + "A course needs to be specified!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Booking booking = new Booking();
		booking.setTutorEmail(tutorEmail);
		booking.setSpecificDate(specificDate);
		booking.setTimeSlot(timeSlot);
		booking.setStudent(studentSet);
		booking.setCourse(course);
		bookingRepository.save(booking);
		Tutor tutor = tutorRepository.findTutorByEmail(tutorEmail);
		Notification notification = notificationService.createNotification(booking, tutor);
		notificationRepository.save(notification);
		return booking;
	}

	@Transactional
	public Booking getBookingById(Integer bookingId) {
		if (bookingId == null) {
			throw new IllegalArgumentException("A booking ID needs to be specified!");
		}
		Booking booking = bookingRepository.findBookingById(bookingId);
		return booking;
	}

	@Transactional
	public List<Booking> getBookingBySpecificDate(Date specificDate) {
		if (specificDate == null) {
			throw new IllegalArgumentException("A specific date needs to be specified!");
		}
		List<Booking> bookingsBySpecificDate = new ArrayList<>();
		for (Booking booking : bookingRepository.findBookingBySpecificDate(specificDate)) {
			bookingsBySpecificDate.add(booking);
		}
		return bookingsBySpecificDate;
	}

	@Transactional
	public List<Booking> getBookingByTutorEmail(String tutorEmail) {
		if (tutorEmail == null || tutorEmail.trim().length() == 0) {
			throw new IllegalArgumentException("A tutor email needs to be specified!");
		}
		List<Booking> bookingsByTutorEmail = new ArrayList<>();
		for (Booking booking : bookingRepository.findBookingByTutorEmail(tutorEmail)) {
			bookingsByTutorEmail.add(booking);
		}
		return bookingsByTutorEmail;
	}

	@Transactional
	public List<Booking> getAllBookings(){
		return toList(bookingRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}


	public ArrayList<Booking> getBookingByTutorId(String tutorId) {
		Tutor tutor = tutorRepository.findTutorById(Integer.parseInt(tutorId));
		Set<Notification> notifications = tutor.getNotification();
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		for (Notification notification: notifications) {
			bookings.add(notification.getBooking());
		}
		return bookings;
	}

	public void declineBookingById(int parseInt) {
		Booking booking = bookingRepository.findBookingById(parseInt);
		Notification notification = booking.getNotification();
		notificationRepository.delete(notification);
		bookingRepository.delete(booking);
		
	}

	public TutoringSession acceptBookingById(int parseInt) {
		Booking booking = bookingRepository.findBookingById(parseInt);
//		Notification notification = booking.getNotification();
		List<Notification> notifications = notificationService.getAllNotifications();
		for(Notification notification: notifications) {
			if(Integer.compare(booking.getId(), notification.getBooking().getId()) == 0) {
				notificationRepository.delete(notification);
			}
		}
		Time bookingStartTime = booking.getTimeSlot().getStartTime();
		Time bookingEndTime = booking.getTimeSlot().getEndTime();
		
		List<Tutor> tutors = tutorService.getAllTutors();
		Tutor realTutor= null;
		for(Tutor tutor: tutors) {
			if(booking.getTutorEmail() == tutor.getEmail()) {
				realTutor = tutor;
			}
		}
		TimeSlot timeSlot = booking.getTimeSlot();
		TutoringSession approvedTutoringSession = null;
		List<Room> rooms = roomService.getAllRooms();
		for(Room room: rooms) {
			boolean roomAvailable = true;
			Set<TutoringSession> tutoringSessions = room.getTutoringSession();
			for (TutoringSession tutoringSession : tutoringSessions) {
				if(tutoringSession.getSessionDate() == booking.getSpecificDate()) {
					Time startTime = tutoringSession.getTimeSlot().getStartTime();
					Time endTime = tutoringSession.getTimeSlot().getEndTime();
					if(startTime.before(bookingStartTime) && (endTime.after(bookingEndTime))) {
						roomAvailable = false;
					}
					else if(startTime.before(bookingEndTime) && endTime.after(bookingEndTime)) {
						roomAvailable = false;
					}
					else if(startTime.after(bookingStartTime) && endTime.before(bookingEndTime)) {
						roomAvailable = false;
					}
					else if(startTime.before(bookingStartTime) && endTime.after(bookingStartTime)) {
						roomAvailable = false;
					}
					else if(startTime.after(bookingStartTime) && startTime.before(bookingEndTime)) {
						roomAvailable = false;
					}
					else if(endTime.after(bookingStartTime) && endTime.before(bookingEndTime)) {
						roomAvailable = false;
					}
				}
			}
			if(roomAvailable) {
				approvedTutoringSession = tutoringSessionService.createTutoringSession(booking.getSpecificDate(),realTutor , room, booking, timeSlot);
				break;
			}
		}

		return approvedTutoringSession;
		
	}

}
