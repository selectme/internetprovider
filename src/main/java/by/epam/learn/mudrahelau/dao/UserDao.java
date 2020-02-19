package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.constant.DbConstants;
import by.epam.learn.mudrahelau.hash.PasswordHash;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    private static final String GET_USER_SQL = "SELECT * from user WHERE login = ? and password = ?";

    public User getUser(String login, String password) {
        Connection connection = DBUtils.getInstance().getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_SQL)
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, PasswordHash.hashPassword(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong(DbConstants.ID);
                String userLogin = resultSet.getString(DbConstants.LOGIN);
                String userPassword = resultSet.getString(DbConstants.ID);
                String userName = resultSet.getString(DbConstants.NAME);
                String userSurname = resultSet.getString(DbConstants.SURNAME);
                Role role = Role.valueOf(resultSet.getString(DbConstants.ROLE));
                user = new User(userId, userLogin, userPassword, userName, userSurname, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.getInstance().releaseConnection(connection);
            }
        }
        return user;
    }
}
