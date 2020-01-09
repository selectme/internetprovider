package by.epam.learn.mudrahelau.model;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Viktar on 26.12.2019
 */
public class Payment {

    private Client client;
    private double amount;
    private Calendar date;

    public Payment(Client client, double amount, Calendar date) {
        this.client = client;
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

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }
}
