package by.epam.learn.mudrahelau.model;

import by.epam.learn.mudrahelau.role.Role;

import java.util.Objects;


/**
 * Model object that represents a user of the internet-provider. The user may have the role of client or administrator.
 */
public class User {
    /**
     * A unique identificator of user.
     */
    private Long id;
    /**
     * User's login is used for log in. Must be four digits.
     */
    private String login;
    /**
     * User's password. There is no specific format restrictions.
     */
    private String password;
    /**
     * User's name.
     */
    private String name;
    /**
     * User's surname.
     */
    private String surname;
    /**
     * User's {@link Role}. User must be a client or an administrator.
     */
    private Role role;

    public User() {
    }

    public User(long id, String login, String password, String name, String surname) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) &&
                login.equals(user.login) &&
                password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password);
    }
}
