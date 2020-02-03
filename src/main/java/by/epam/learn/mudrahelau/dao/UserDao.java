package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.hash.PasswordHash;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User getUser(String login, String password) {
        Connection connection = DBUtils.getInstance().getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from user WHERE login = ? and password = ? ");
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, PasswordHash.hashPassword(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong(1);
                String userLogin = resultSet.getString(2);
                String userPassword = resultSet.getString(3);
                String userName = resultSet.getString(4);
                String userSurname = resultSet.getString(5);
                Role role = Role.valueOf(resultSet.getString(6));
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
