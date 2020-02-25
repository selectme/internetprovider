package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.util.PasswordUtil;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Viktar on 24.02.2020
 */
public class UserServiceTest {

    private User testUser;
    private UserService userService;

    @BeforeMethod
    public void setUp() {
        userService = new UserService();

        testUser = new User();
        testUser.setId(74L);
        testUser.setLogin("6666");
        testUser.setPassword(PasswordUtil.hashPassword("6666"));
        testUser.setName("bruce");
        testUser.setSurname("lee");
        testUser.setRole(Role.CLIENT);
    }

    @Test
    public void testGetUser() {

        User expectedUser = testUser;
        String login = "6666";
        String password = "6666";

        User actualUser = userService.getUser(login, password);

        assertEquals(actualUser, expectedUser);
    }
}