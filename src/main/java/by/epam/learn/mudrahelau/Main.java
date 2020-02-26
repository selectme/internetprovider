package by.epam.learn.mudrahelau;

import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.validator.UserValidator;

/**
 * @author Viktar on 26.02.2020
 */
public class Main {

    public static void main(String[] args) {
        String name = "<joe";

        User user = new User();
        user.setName(name);
        user.setSurname("dow");

     boolean res =  UserValidator.validateName(user);
        System.out.println(res);
    }
}
