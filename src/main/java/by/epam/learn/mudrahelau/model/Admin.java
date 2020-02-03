package by.epam.learn.mudrahelau.model;

import by.epam.learn.mudrahelau.role.Role;

public class Admin extends User {

    public Admin(long id, String login, String password, String name, String surname) {
        super(id, login, password, name, surname);
        super.setRole(Role.ADMIN);
    }

    public Admin() {
    }
}
