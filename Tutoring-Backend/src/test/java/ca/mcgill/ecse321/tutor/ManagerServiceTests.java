package ca.mcgill.ecse321.tutor;

import ca.mcgill.ecse321.tutor.dao.ManagerRepository;
import ca.mcgill.ecse321.tutor.service.ManagerService;
import ca.mcgill.ecse321.tutor.model.Manager;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ManagerServiceTests {

    @Mock
    private ManagerRepository managerRepository;

    @InjectMocks
    private ManagerService managerService;

    private Manager manager = new Manager();

    private static final Integer SUCCESS_KEY = 1;

    @Before
    public void setMockOutput() {
        when(managerRepository.findManagerById(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(SUCCESS_KEY)) {
                manager.setId(SUCCESS_KEY);
                return manager;
            } else {
                return null;
            }
        });
        when(managerRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<Manager> managers = new ArrayList<>();
            manager.setId(SUCCESS_KEY);
            managers.add(manager);
            return managers;
        });
    }

    @Test
    public void testGetManager() {
        assertEquals(SUCCESS_KEY, managerService.getManager(SUCCESS_KEY).getId());
        assertEquals(SUCCESS_KEY, managerService.getAllManagers().get(0).getId());
    }

}
