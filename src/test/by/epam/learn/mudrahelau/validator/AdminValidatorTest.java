package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.service.UserService;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

/**
 * @author Viktar on 26.02.2020
 */
public class AdminValidatorTest {
    private Client client;
    private User user;
    private User user2;
    private TariffPlan tariffPlan;
    private AdminService adminService;
    private UserService userService;

    @BeforeMethod
    public void setUp() {
//        adminService = new AdminServiceDbImpl(new AdminDao)
//        userService = new UserService();



        user = new User();
        user.setLogin("0000");
        user.setPassword("0000");
        user.setName("Test");
        user.setSurname("User");
        user.setRole(Role.ADMIN);

        user = new User();
        user.setLogin("1111");
        user.setPassword("1111");
        user.setName("Test2");
        user.setSurname("User2");
        user.setRole(Role.CLIENT);

    }

    @Test
    public void testCheckUserIsAdmin() {
        User adminUser = user;
        boolean isAdmin = AdminValidator.checkUserIsAdmin(adminUser);

        assertTrue(isAdmin);
    }

    @Test
    public void testCheckUserIsAdminFalse() {
        User adminUser = user2;
        boolean isAdmin = AdminValidator.checkUserIsAdmin(adminUser);

        assertFalse(isAdmin);
    }
}