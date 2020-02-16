package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;

/**
 * @author Viktar on 16.02.2020
 */
public class AdminValidator {

    public static boolean checkUserIsAdmin(User user) {
        if (user != null) {
            return user.getRole() == Role.ADMIN;
        } else {
            return false;
        }
    }

}
