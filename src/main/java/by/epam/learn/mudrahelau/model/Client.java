package by.epam.learn.mudrahelau.model;

import java.util.List;

/**
 * @author Viktar on 26.12.2019
 */
public class Client {

    private String login;
    private String password;
    private String name;
    private String surname;
    private TariffPlan tariffPlan;

    private List<Payment> payments;

    public Client(String login, String password, String name, String surname, TariffPlan tariffPlan) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.tariffPlan = tariffPlan;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public TariffPlan getTariffPlan() {
        return tariffPlan;
    }

    public void setTariffPlan(TariffPlan tariffPlan) {
        this.tariffPlan = tariffPlan;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", tariffPlan=" + tariffPlan +
                '}';
    }
}
