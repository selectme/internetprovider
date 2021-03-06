package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;

import java.util.List;

/**
 * @author Viktar on 26.02.2020
 */
public interface AdminDao {

    /**
     * Add {@link User}
     *
     * @param user {@link User} which will be added to the database.
     */
    void addUser(User user);

    /**
     * Deletes {@link User} by ID.
     *
     * @param userId {@link User} will be deleted according to this ID.
     */
    void deleteUserById(long userId);

    /**
     * Allows to edit {@link Client}.
     *
     * @param client {@link Client} which will be edited.
     */
    void editClientByAdmin(Client client);

    /**
     * Retrieves list if {@link Client}.
     *
     * @return List of {@link Client}
     */
    List<Client> retrieveClients();

    /**
     * Gets {@link Client} by ID.
     *
     * @param id for getting {@link Client}.
     * @return {@link Client}.
     */
    Client getClientById(long id);

    /**
     * Allows to get {@link TariffPlan} according to its id.
     *
     * @param tariffPlanId {@link TariffPlan} id.
     * @return {@link TariffPlan}
     */
    TariffPlan getTariffPlanById(int tariffPlanId);

    /**
     * Deletes {@link TariffPlan}
     *
     * @param tariffPlanId {@link TariffPlan} will be deleted according to this ID.
     */
    void deleteTariffPlanById(int tariffPlanId);

    /**
     * Allows to change {@link by.epam.learn.mudrahelau.status.ClientStatus} to Inactive.
     *
     * @param clientId {@link Client} id.
     */
    void makeInactiveClient(long clientId);

    /**
     * Specific method which allows to make {@link Payment} and change {@link TariffPlan} when it is necessary.
     *
     * @param clientId     {@link Client} id.
     * @param tariffPlanId {@link TariffPlan} id.
     * @param payment      {@link Payment}.
     */
    void makePaymentAndChangeTariffPlan(long clientId, int tariffPlanId, Payment payment);

    /**
     * Adds {@link TariffPlan}
     *
     * @param tariffPlan {@link TariffPlan} which will be added to the database.
     */
    void addTariffPlan(TariffPlan tariffPlan);

    /**
     * Retrieves list of {@link TariffPlan}.
     *
     * @return list of {@link TariffPlan}
     */
    List<TariffPlan> retrieveTariffPlans();

    /**
     * Allows to edit {@link TariffPlan}
     *
     * @param tariffPlan {@link TariffPlan} to be edited.
     */
    void editTariffPlan(TariffPlan tariffPlan);

    /**
     * Allows to get {@link TariffPlan} according to {@link Client} id.
     *
     * @param clientId {@link Client} id.
     * @return {@link TariffPlan}
     */
    TariffPlan getTariffPlanByClientId(long clientId);
}
