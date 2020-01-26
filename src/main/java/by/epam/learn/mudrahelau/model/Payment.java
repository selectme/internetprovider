package by.epam.learn.mudrahelau.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Viktar on 26.12.2019
 */
public class Payment {

    private Client client;
    private BigDecimal amount;
    private LocalDateTime date;

    public Payment(Client client, BigDecimal amount, LocalDateTime date) {
        this.client = client;
        this.amount = amount;
        this.date = date;
    }

    public Payment(BigDecimal amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "amount=" + amount +
                ", date=" + date +
                '}';
    }
}
