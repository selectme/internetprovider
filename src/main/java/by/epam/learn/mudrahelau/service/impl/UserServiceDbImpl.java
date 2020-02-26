package by.epam.learn.mudrahelau.service.impl;

import by.epam.learn.mudrahelau.dao.UserDao;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.UserService;

/**
 * @author Viktar on 27.01.2020
 */
public class UserServiceDbImpl implements UserService {

    private UserDao userDao;

    public UserServiceDbImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser(String login, String password) {
        return userDao.getUser(login, password);
    }
}
