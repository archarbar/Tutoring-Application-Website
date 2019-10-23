package ca.mcgill.ecse321.tutor.service;

import ca.mcgill.ecse321.tutor.dao.BookingRepository;
import ca.mcgill.ecse321.tutor.model.Booking;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//@PropertySource("classpath:applications.properties")
public class BookingServiceTests {

    @Mock
    private BookingRepository bookingDao;

    @InjectMocks
    private BookingService bookingService;

    private Booking booking = new Booking();

    private static final Integer BOOKING_KEY = 1;
    private static final String TUTOR_EMAIL = "arthurmorgan@redemption.com";
    private static final Date BOOKING_DATE = Date.valueOf("2019-10-10");

    @Before
    public void setMockOutput() {
        when(bookingDao.findBookingById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BOOKING_KEY)) {
                booking.setId(BOOKING_KEY);
                booking.setTutorEmail(TUTOR_EMAIL);
                return booking;
            } else {
                return null;
            }
        });
        when(bookingDao.findBookingByTutorEmail(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(TUTOR_EMAIL)) {
                List<Booking> bookings = new ArrayList<>();
                booking.setTutorEmail(TUTOR_EMAIL);
                bookings.add(booking);
                return bookings;
            } else {
                return null;
            }
        });
        when(bookingDao.findBookingBySpecificDate(any())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(BOOKING_DATE)) {
                List<Booking> bookings = new ArrayList<>();
                booking.setSpecificDate(BOOKING_DATE);
                bookings.add(booking);
                return bookings;
            } else {
                return null;
            }
        });
        when(bookingDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<Booking> bookings = new ArrayList<>();
            booking.setId(BOOKING_KEY);
            bookings.add(booking);
            return bookings;
        });
    }

    @Test
    public void testGetBooking() { // test constructor method
        assertEquals(BOOKING_KEY, bookingService.getBookingById(BOOKING_KEY).getId());
        assertEquals(TUTOR_EMAIL, bookingService.getBookingByTutorEmail(TUTOR_EMAIL).get(0).getTutorEmail());
        assertEquals(BOOKING_DATE, bookingService.getBookingBySpecificDate(BOOKING_DATE).get(0).getSpecificDate());
        assertEquals(BOOKING_KEY, bookingService.getAllBookings().get(0).getId());
    }

}
