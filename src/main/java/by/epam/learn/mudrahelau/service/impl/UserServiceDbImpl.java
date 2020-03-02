package by.epam.learn.mudrahelau.service.impl;

import by.epam.learn.mudrahelau.dao.UserDao;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.UserService;

/**
 * JDBC implementation of the {@link UserService}
 *
 * @see UserService
 */
public class UserServiceDbImpl implements UserService {
    /**
     * @see UserService
     */
    private UserDao userDao;

    public UserServiceDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(String login, String password) {
        return userDao.getUser(login, password);
    }
}
