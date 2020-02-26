package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.util.DBUtils;
import com.google.common.annotations.VisibleForTesting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    private static final String GET_LOGIN_SQL = "SELECT login FROM user WHERE login=?";
    private static final String EMPTY_STRING = "";

    private static final Logger logger = LogManager.getLogger(UserValidator.class);

    public static boolean validateCreatingUser(User user) {
        return validateLogin(user) && validatePassword(user) && validateName(user) && validateSurname(user) && validateRole(user);
    }

    public static boolean validateEditingClient(User user) {
        return validateName(user) && validateSurname(user);
    }


    public static boolean validateName(User user) {
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

    private static boolean validateSurname(User user) {
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

    @VisibleForTesting
    static boolean validatePassword(User user) {
        if (user.getPassword() != null) {
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
