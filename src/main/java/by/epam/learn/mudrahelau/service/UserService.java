package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.model.User;

/**
 * @author Viktar on 27.01.2020
 */
public interface UserService {

    User getUser(String login, String password);
}
