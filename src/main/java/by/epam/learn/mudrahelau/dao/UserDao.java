package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.model.User;

/**
 * Provides operations common to all {@link User}.
 */
public interface UserDao {
    /**
     * Gets {@link User} using login and password.
     *
     * @param login    {@link User} login
     * @param password {@link User} password
     * @return {@link User} in case {@link User} if found.
     */
    User getUser(String login, String password);
}
