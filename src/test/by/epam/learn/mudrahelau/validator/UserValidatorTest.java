package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.model.User;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * @author Viktar on 26.02.2020
 */
public class UserValidatorTest {



    @Test
    public void testValidateName() {
        User testUser = new User();
        testUser.setName("Viktor");

       boolean isNameValid = UserValidator.validateName(testUser);
       assertTrue(isNameValid);
    }

}