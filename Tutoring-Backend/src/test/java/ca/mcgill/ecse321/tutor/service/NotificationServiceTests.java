package ca.mcgill.ecse321.tutor.service;

import ca.mcgill.ecse321.tutor.dao.NotificationRepository;
import ca.mcgill.ecse321.tutor.model.Notification;
import ca.mcgill.ecse321.tutor.model.Level;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class NotificationServiceTests {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    private Notification notification = new Notification();

    private static final Integer SUCCESS_KEY = 1;

    @Before
    public void setMockOutput(){
        when(notificationRepository.findNotificationById(anyInt())).thenAnswer( (InvocationOnMock invocation) ->{
            if (invocation.getArgument(0).equals(SUCCESS_KEY)){
                notification.setId(SUCCESS_KEY);
                return notification;
            } else {
                return null;
            }
        });
        when(notificationRepository.findAll()).thenAnswer( (InvocationOnMock invocation) ->{
            List<Notification> notifications = new ArrayList<>();
            notification.setId(SUCCESS_KEY);
            notifications.add(notification);
            return notifications;
        });
    }

    @Test
    public void testGetNotification(){
        assertEquals(SUCCESS_KEY, notificationService.getNotification(SUCCESS_KEY).getId());
        assertEquals(SUCCESS_KEY, notificationService.getAllNotifications().get(0).getId());
    }

}
