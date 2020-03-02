package by.epam.learn.mudrahelau.dao.impl;

import by.epam.learn.mudrahelau.constant.DbConstants;
import by.epam.learn.mudrahelau.constant.LoggerConstants;
import by.epam.learn.mudrahelau.dao.UserDao;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.util.DBUtils;
import by.epam.learn.mudrahelau.util.PasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC implementation of the {@link UserDao}
 *
 * @see UserDao
 */
public class UserDaoDbImpl implements UserDao {

    private static final String GET_USER_SQL = "SELECT * from user WHERE login = ? and password = ?";
    private static final Logger logger = LogManager.getLogger(UserDaoDbImpl.class);

    @Override
    public User getUser(String login, String password) {
        Connection connection = DBUtils.getInstance().getConnection();
        User user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_SQL)
        ) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, PasswordUtil.hashPassword(password));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long userId = resultSet.getLong(DbConstants.ID);
                String userLogin = resultSet.getString(DbConstants.LOGIN);
                String userPassword = resultSet.getString(DbConstants.PASSWORD);
                String userName = resultSet.getString(DbConstants.NAME);
                String userSurname = resultSet.getString(DbConstants.SURNAME);
                Role role = Role.valueOf(resultSet.getString(DbConstants.ROLE));
                user = new User();
                user.setId(userId);
                user.setLogin(userLogin);
                user.setPassword(userPassword);
                user.setName(userName);
                user.setSurname(userSurname);
                user.setRole(role);
            }
        } catch (SQLException e) {
            logger.error(LoggerConstants.SQL_EXCEPTION, e);
        } finally {
            if (connection != null) {
                DBUtils.getInstance().releaseConnection(connection);
            }
        }
        return user;
    }
}
