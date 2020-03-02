package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.model.User;

/**
 * Provides operations common to all {@link User}.
 */
public interface UserService {

    /**
     * Gets {@link User} using login and password.
     *
     * @param login {@link User} login
     * @param password {@link User} password
     * @return {@link User} in case {@link User} if found.
     */
    User getUser(String login, String password);
}
