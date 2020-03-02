package by.epam.learn.mudrahelau.model;

import by.epam.learn.mudrahelau.payment.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * Model object that represents a payment.
 */
public class Payment {
    /**
     * Unique identificator of payment transaction.
     */
    private int id;
    /**
     * {@link Client} identificator.
     */
    private long clientId;
    /**
     * Amount of money
     */
    private BigDecimal amount;
    /**
     * Date of operation.
     */
    private LocalDateTime date;
    /**
     * {@link PaymentType} can be DEBIT or CREDIT.
     */
    private PaymentType paymentType;

    public Payment() {
    }

    public Payment(long clientId, BigDecimal amount, PaymentType paymentType, LocalDateTime date) {
        this.clientId = clientId;
        this.amount = amount;
        this.paymentType = paymentType;
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
