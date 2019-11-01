package ca.mcgill.ecse321.tutor.dto;

import ca.mcgill.ecse321.tutor.model.Booking;
import ca.mcgill.ecse321.tutor.model.Tutor;

public class NotificationDto {
    private Booking booking;
    private Tutor tutor;
    private int notificationId;

    public NotificationDto(Booking booking, Tutor tutor, int notificationId) {
        this.booking = booking;
        this.tutor = tutor;
        this.notificationId = notificationId;
    }

    public Booking getBooking() {
        return booking;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public int getNotificationId() {
        return notificationId;
    }
}
