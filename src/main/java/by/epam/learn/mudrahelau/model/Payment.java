package by.epam.learn.mudrahelau.model;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Viktar on 26.12.2019
 */
public class Payment {

    private Client client;
    private double amount;
    private Date date;

    public Payment(Client client, double amount, Date date) {
        this.client = client;
        this.amount = amount;
        this.date = date;
    }

    public Payment(double amount, Date date) {
        this.amount = amount;
        this.date = date;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
