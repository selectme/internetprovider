package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;

/**
 * AdminValidator is used for validate that {@link User} is an administrator.
 */
public class AdminValidator {

    public static boolean checkUserIsAdmin(User user) {
        if (user != null) {
            return user.getRole().equals(Role.ADMIN);
        } else {
            return false;
        }
    }

}
