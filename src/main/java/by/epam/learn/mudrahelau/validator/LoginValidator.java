package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * @author Viktar on 04.02.2020
 */
public class LoginValidator {
    private static final String GET_LOGIN_SQL = "SELECT login FROM user WHERE login=?";

    public static boolean checkLoginIsUnique(String login) {

        boolean result = true;
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LOGIN_SQL)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
        return result;
    }
}
