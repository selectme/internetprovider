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

    List<Client> retrieveClients();

    Client getClientById(long id);

    TariffPlan getTariffPlanById(int tariffPlanId);

    void createUser(User user);

    void deleteUserById(long userId);

    void deleteTariffPlanById(int tariffPlanId);

    void editClientByAdmin(Client client);

    void makeInactiveClient(long clientId);

    void makePaymentAndChangeTariffPlan(long clientId, int tariffPlanId, Payment payment);

    void createTariffPlan(TariffPlan tariffPlan);

    List<TariffPlan> retrieveTariffPlans();

    void editTariffPlan(TariffPlan tariffPlan);

    TariffPlan getTariffPlanByClientId(long clientId);
}
