package by.epam.learn.mudrahelau.model;

import by.epam.learn.mudrahelau.role.Role;

import java.util.List;

/**
 * @author Viktar on 26.12.2019
 */
public class Client extends User{

    private TariffPlan tariffPlan;
    private List<Payment> payments;


    public Client(long id, String login, String password, String name, String surname, TariffPlan tariffPlan) {
        super(id, login, password, name, surname);
        this.tariffPlan = tariffPlan;
    }

    public Client(String login, String password, String name, String surname, TariffPlan tariffPlan) {
        super(login, password, name, surname);
        this.tariffPlan = tariffPlan;
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
                super.getId() + " name:" + super.getName() +
                " tariffPlan=" + tariffPlan +
                '}';
    }
}
