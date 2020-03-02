package by.epam.learn.mudrahelau.model;

import by.epam.learn.mudrahelau.status.ClientStatus;

import java.math.BigDecimal;
import java.util.List;


/**
 * Model object that represents a client of the internet-provider.
 */
public class Client extends User {

    /**
     * Client's {@link TariffPlan}.
     */
    private TariffPlan tariffPlan;

    /**
     * Client's list of {@link Payment}.
     */
    private List<Payment> payments;

    /**
     * Current money balance of a client.
     */
    private BigDecimal moneyOnAccount;

    /**
     * Current {@link ClientStatus} of a client. May be ACTIVE, INACTIVE, BLOCKED.
     */
    private ClientStatus status;

    public Client(long id, String login, String password, String name, String surname, TariffPlan tariffPlan, ClientStatus status) {
        super(id, login, password, name, surname);
        this.tariffPlan = tariffPlan;
        this.status = status;
    }

    public Client() {
        super();
    }

    public BigDecimal getMoneyOnAccount() {
        return moneyOnAccount;
    }

    public void setMoneyOnAccount(BigDecimal moneyOnAccount) {
        this.moneyOnAccount = moneyOnAccount;
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

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Client{" +
                super.getId() + " name:" + super.getName() +
                " tariffPlan=" + tariffPlan +
                '}';
    }
}
