package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * @author Viktar on 26.02.2020
 */
public class UserValidatorTest {

    private User testUser;

    @BeforeMethod
    public void setUp() {
        testUser = new User();
        testUser.setName("Viktor");
        testUser.setSurname("Mudrahelau");
        testUser.setLogin("1111");
        testUser.setPassword("1111");
        testUser.setRole(Role.CLIENT);
    }

    @Test
    public void testValidateName() {
        boolean isNameValid = UserValidator.validateName(testUser);
        assertTrue(isNameValid);
    }

    @Test
    public void testValidateSurname() {
        boolean isSurnameValid = UserValidator.validateSurname(testUser);
        assertTrue(isSurnameValid);
    }

    @Test
    public void testValidatePassword() {
        boolean isPasswordValid = UserValidator.validatePassword(testUser);
        assertTrue(isPasswordValid);
    }

    @Test
    public void testValidateRole() {
        boolean isRoleValidate = UserValidator.validateRole(testUser);
        assertTrue(isRoleValidate);
    }

}