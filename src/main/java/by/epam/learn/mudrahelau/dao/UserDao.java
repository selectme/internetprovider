package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.model.User;


public interface UserDao {

    User getUser(String login, String password);
}
