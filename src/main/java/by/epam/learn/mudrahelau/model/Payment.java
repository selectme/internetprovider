package by.epam.learn.mudrahelau.model;

import by.epam.learn.mudrahelau.payment.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Viktar on 26.12.2019
 */
public class Payment {

    private int id;
    private long clientId;
    private BigDecimal amount;
    private LocalDateTime date;
    private PaymentType paymentType;

    public Payment() {
    }

    public Payment(long clientId, BigDecimal amount, PaymentType paymentType, LocalDateTime date) {
        this.clientId = clientId;
        this.amount = amount;
        this.paymentType = paymentType;
        this.date = date;
    }

    public Payment(BigDecimal amount, LocalDateTime date) {
        this.amount = amount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
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
