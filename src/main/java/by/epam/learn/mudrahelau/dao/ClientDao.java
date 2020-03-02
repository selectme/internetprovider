package by.epam.learn.mudrahelau.dao;

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
public interface ClientDao {


    /**
     * Edits {@link Client} data.
     *
     * @param client {@link Client}
     */
    void editClientByClient(Client client);

    /**
     * Makes {@link Payment}.
     *
     * @param payment {@link Client}
     */
    void makePayment(Payment payment);

    /**
     * Retrieves list of {@link Payment}.
     *
     * @param clientId {@link Client} identificator.
     * @return If {@link Client} exists will be returned List of {@link Payment} the given {@link Client}.
     */
    List<Payment> retrievePayments(Long clientId);

    /**
     * Changes {@link ClientStatus}.
     *
     * @param clientId {@link Client} id which {@link ClientStatus} should be changed.
     * @param status   New {@link ClientStatus} which should be assigned to {@link Client}.
     */
    void changeClientStatus(long clientId, ClientStatus status);


    /**
     * Retrieves the last debit payment date of the {@link Client}.
     *
     * @param clientId {@link Client} id whose the last debit must be retrieved.
     * @return {@link LocalDateTime}
     */
    LocalDateTime retrieveLastDebitDate(long clientId);

    /**
     * Retrieves {@link Map} of active {@link Client} id and their {@link by.epam.learn.mudrahelau.model.TariffPlan} id.
     *
     * @return {@link Map<Long, Integer>} of {@link Client} id and {@link by.epam.learn.mudrahelau.model.TariffPlan} id.
     */
    Map<Long, Integer> retrieveActiveClientsId();

    /**
     * Retrieves amount of money on {@link Client} balance.
     *
     * @param clientId {@link Client} identificator.
     * @return If {@link Client} exists will be returned amount of money the given {@link Client}.
     */
    BigDecimal retrieveClientMoneyAmountByClientId(long clientId);
}
