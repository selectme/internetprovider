package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.dao.impl.UserDaoDbImpl;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.impl.UserServiceDbImpl;
import by.epam.learn.mudrahelau.util.PasswordUtil;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.any;
import static org.testng.Assert.assertEquals;

/**
 * @author Viktar on 24.02.2020
 */
public class UserServiceTest {

    private User testUser;
    private UserService userService;
    UserDaoDbImpl userDaoDbImpl;

    @BeforeMethod
    public void setUp() {
         userDaoDbImpl = Mockito.mock(UserDaoDbImpl.class);
    }

    @Test
    public void testGetUser() {

        testUser = new User();
        testUser.setLogin("1111");
        testUser.setPassword(PasswordUtil.hashPassword("1111"));

        Mockito.when(userDaoDbImpl.getUser(any(), any())).thenReturn(testUser);
        userService = new UserServiceDbImpl(userDaoDbImpl);

        User user = userService.getUser("1111", "1111");

        assertEquals(user.getLogin(), testUser.getLogin());
        assertEquals(user.getPassword(), testUser.getPassword());

    }
}