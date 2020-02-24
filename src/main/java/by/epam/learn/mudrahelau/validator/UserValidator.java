package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.util.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Viktar on 19.02.2020
 */
public class UserValidator {
    private static final String GET_LOGIN_SQL = "SELECT login FROM user WHERE login=?";
    private static final String EMPTY_STRING = "";

    private static final Logger logger = LogManager.getLogger(UserValidator.class);

    public static boolean validateUser(User user) {
        return validateLogin(user) && validatePassword(user) && validateName(user) && validateSurname(user) && validateRole(user);
    }

    private static boolean validateName(User user) {

        if (user.getName() != null) {
            System.out.println(user.getName());
            return !user.getName().trim().equals(EMPTY_STRING);
        } else return false;
    }

    private static boolean validateSurname(User user) {
        if (user.getSurname() != null) {
            System.out.println(user.getSurname());
            return !user.getSurname().trim().equals(EMPTY_STRING);
        } else return false;
    }

    private static boolean validatePassword(User user) {
        if (user.getPassword() != null) {
            System.out.println(user.getPassword());
            return !user.getPassword().trim().equals(EMPTY_STRING);
        } else return false;
    }

    private static boolean validateLogin(User user) {
        boolean result = true;
        if (user.getLogin() != null) {
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
        }
        return result;
    }

    private static boolean validateRole(User user) {
        return user.getRole() != null;
    }
}
