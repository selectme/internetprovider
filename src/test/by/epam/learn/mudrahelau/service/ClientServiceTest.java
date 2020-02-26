package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.dao.impl.AdminDaoDbImpl;
import by.epam.learn.mudrahelau.dao.impl.UserDaoDbImpl;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.service.impl.AdminServiceDbImpl;
import by.epam.learn.mudrahelau.service.impl.UserServiceDbImpl;
import org.testng.annotations.BeforeMethod;

import java.math.BigDecimal;

/**
 * @author Viktar on 24.02.2020
 */
public class ClientServiceTest {

    private static int TEST_TARIFF_ID = 27;

    private Client client;
    private User user;
    private TariffPlan tariffPlan;
    private AdminService adminService;
    private UserService userService;

    @BeforeMethod
    public void setUp() {
        adminService = new AdminServiceDbImpl(new AdminDaoDbImpl());
        userService = new UserServiceDbImpl(new UserDaoDbImpl());

        tariffPlan = new TariffPlan();
        tariffPlan.setTitle("TestTariff");
        tariffPlan.setSpeed(100);
        tariffPlan.setPrice(new BigDecimal(30.0));

        user = new User();
        user.setLogin("0000");
        user.setPassword("0000");
        user.setName("Test");
        user.setSurname("User");
        user.setRole(Role.CLIENT);

    }

//    @Test
//    public void testCreateUser() {
//        User testUser = user;
//        adminService.createUser(testUser);
//
//        String login = testUser.getLogin();
//        String password = testUser.getPassword();
//        User userFromDb = userService.getUser(login, password);
//
//        assertEquals(testUser.getLogin(), userFromDb.getLogin());
//        assertEquals(PasswordUtil.hashPassword(password), userFromDb.getPassword());
//        assertEquals(testUser.getName(), userFromDb.getName());
//        assertEquals(testUser.getRole(), userFromDb.getRole());
//    }



}