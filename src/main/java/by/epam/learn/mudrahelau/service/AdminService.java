package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;

import java.util.List;

/**
 * @author Viktar on 26.02.2020
 */
public interface AdminService {

    void createUser(User user);

    void deleteUserById(long userId);

    void deleteTariffPlanById(int tariffPlanId);

    Client getClientById(long id);

    void editClientByAdmin(Client client);

    List<Client> retrieveClients();

    void createTariffPlan(TariffPlan tariffPlan);;

    void editTariffPlan(TariffPlan tariffPlan);

    void makeInactiveClient(long clientId);

    TariffPlan getTariffPlanByClientId(long clientId);

    TariffPlan getTariffPlanById(int tariffPlanId);

    List<TariffPlan> retrieveTariffPlans();

    void makePaymentAndChangeTariff(long clientId, int tariffId, Payment payment);
}
