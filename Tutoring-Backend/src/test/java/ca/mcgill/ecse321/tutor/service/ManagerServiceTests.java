package ca.mcgill.ecse321.tutor.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;

import ca.mcgill.ecse321.tutor.model.Manager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.tutor.dao.ManagerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManagerServiceTests {

  @Autowired
  private ManagerRepository managerRepository;

  @Autowired
  private ManagerService managerService;

  @After
  public void clearDatabase() {
    managerRepository.deleteAll();
  }

  @Test
  public void testCreateManager() {
    assertEquals(0, managerService.getAllManagers().size());
    try {
      managerService.createManager();
    } catch (IllegalArgumentException e) {
      fail();
    }
    List<Manager> allManagers = managerService.getAllManagers();
    assertEquals(1, allManagers.size());
  }
}
