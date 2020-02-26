package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.status.ClientStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Viktar on 26.02.2020
 */
public interface ClientService {
    void editClientByClient(Client client);

    void makePayment(Payment payment);

    List<Payment> retrievePayments(long clientId);

    BigDecimal retrieveClientMoneyAmountByClientId(long clientId);

    void changeClientStatus(long clientId, ClientStatus status);

    void removeTariffPlanFromClient(long clientId);

    Map<Long, Integer> retrieveActiveClientsId();

    LocalDateTime retrieveLastDebitDate(long clientId);
}
