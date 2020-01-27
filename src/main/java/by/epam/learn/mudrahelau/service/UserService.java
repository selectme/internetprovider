package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.dao.UserDao;
import by.epam.learn.mudrahelau.model.User;

/**
 * @author Viktar on 27.01.2020
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public User getUser(String login, String password) {
        return userDao.getUser(login, password);
    }
}
