package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Viktar on 26.02.2020
 */
public class AdminValidatorTest {
    private User user;
    private User user2;


    @BeforeMethod
    public void setUp() {

        user = new User();
        user.setLogin("0000");
        user.setPassword("0000");
        user.setName("Test");
        user.setSurname("User");
        user.setRole(Role.ADMIN);

        user2 = new User();
        user2.setLogin("1111");
        user2.setPassword("1111");
        user2.setName("Test2");
        user2.setSurname("User2");
        user2.setRole(Role.CLIENT);

    }

    @Test
    public void testCheckUserIsAdmin() {

        boolean isAdmin = AdminValidator.checkUserIsAdmin(user);

        assertTrue(isAdmin);
    }

    @Test
    public void testCheckUserIsAdminFalse() {

        boolean isAdmin = AdminValidator.checkUserIsAdmin(user2);

        assertFalse(isAdmin);
    }
}