package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.util.DBUtils;
import com.google.common.annotations.VisibleForTesting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * TariffValidator is used for validating {@link User} in during creation or editing process.
 */
public class UserValidator {
    /**
     * SQL request for getting {@link User} login.
     */
    private static final String GET_LOGIN_SQL = "SELECT login FROM user WHERE login=?";
    /**
     * Empty string involved in the validation process.
     */
    private static final String EMPTY_STRING = "";
    /**
     * Login length.
     */
    private static final int LOGIN_LENGTH = 4;
    /**
     * {@link Logger}
     */
    private static final Logger logger = LogManager.getLogger(UserValidator.class);

    /**
     * Validates {@link User}.
     *
     * @param user {@link User} to be validated.
     * @return {@code true} in case that all validation stages are satisfactory.
     */
    public static boolean validateCreatingUser(User user) {
        return validateLogin(user) && validatePassword(user) && validateName(user) && validateSurname(user) && validateRole(user);
    }

    public static boolean validateEditingClient(User user) {
        return validateName(user) && validateSurname(user);
    }

    /**
     * Checks that {@link User} name is not null or empty and fits the required parameters.
     *
     * @param user {@link User} to be validated.
     * @return {@code true} if {@link User} name is not null or empty and satisfies the condition.
     */
    @VisibleForTesting
    static boolean validateName(User user) {
        boolean result = false;
        if (user.getName() != null) {
            if (!user.getName().trim().equals(EMPTY_STRING)) {
                String regx = "^[A-Za-zА-Яа-я\\s]+[\\.\\']?[A-Za-zА-Яа-я\\s]*$";
                Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(user.getName());
                result = matcher.find();
            }
        }
        return result;
    }

    /**
     * Checks that {@link User} surname is not null or empty and fits the required parameters.
     *
     * @param user {@link User} to be validated.
     * @return {@code true} if {@link User} surname is not null or empty and satisfies the condition.
     */
    @VisibleForTesting
    static boolean validateSurname(User user) {
        boolean result = false;
        if (user.getSurname() != null) {
            if (!user.getSurname().trim().equals(EMPTY_STRING)) {
                String regx = "^[A-Za-zА-Яа-я\\s]+[\\.\\']?[A-Za-zА-Яа-я\\s]*$";
                Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(user.getSurname());
                result = matcher.find();
            }
        }
        return result;
    }

    /**
     * Checks that {@link User} password is not null or empty.
     *
     * @param user {@link User} to be validated.
     * @return {@code true} if {@link User} password is not null or empty.
     */
    @VisibleForTesting
    static boolean validatePassword(User user) {
        if (user.getPassword() != null) {
            return !user.getPassword().trim().equals(EMPTY_STRING);
        } else return false;
    }

    /**
     * Checks that login is not null and unique.
     *
     * @param user {@link User} to be validated.
     * @return {@code true} if {@link User} login is not null or empty and has no duplicates.
     */
    private static boolean validateLogin(User user) {
        boolean result = true;
        if ((user.getLogin() != null) && (user.getLogin().trim().length() <= LOGIN_LENGTH)) {

            Connection connection = DBUtils.getInstance().getConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LOGIN_SQL)) {
                preparedStatement.setString(1, user.getLogin());
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    result = false;
                }
            } catch (SQLException e) {
                logger.error(LoggerConstants.SQL_EXCEPTION, e);
            } finally {
                DBUtils.getInstance().releaseConnection(connection);
            }
        } else {
            return false;
        }
        return result;
    }

    /**
     * Checks that {@link Role} is not null and exists.
     *
     * @param user {@link User} to be validated.
     * @return {@code true} if {@link Role} not null and exists.
     */
    @VisibleForTesting
    static boolean validateRole(User user) {
        boolean result = true;
        if (user.getRole() != null) {
            List<Role> roles = new ArrayList<>(Arrays.asList(Role.values()));
            if (!roles.contains(user.getRole())) {
                result = false;
            }
        }
        return result;
    }
}
