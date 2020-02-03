package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.role.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;

public class AdminService {
    private static final Logger logger = LogManager.getLogger(AdminService.class);

    private AdminDao adminDao = new AdminDao();

    public void createUser(String login, String password, String name, String surname, Role role) {
        adminDao.createUser(login, password, name, surname, role);
        logger.info("User{" + name + " " + surname + "." + role + "} created");
    }

    public void deleteUserById(long userId) {
        adminDao.deleteUserById(userId);
        logger.info("User{" + userId + "} deleted");
    }

    public void deleteTariffPlanById(int tariffPlanId) {
        adminDao.deleteTariffPlanById(tariffPlanId);
        logger.info("Tariff plan{" + tariffPlanId + "} deleted");
    }

    public Client getClientById(long id) {
        return adminDao.getClientById(id);
    }

    public void editClientByAdmin(Client client) {
        adminDao.editClientByAdmin(client);
        logger.info("Client{" + client.getId() + "} edited");
    }

    public List<Client> retrieveClients() {
        return adminDao.retrieveClients();
    }

    public void createTariffPlan(String title, int speed, BigDecimal price) {
        adminDao.createTariffPlan(title, speed, price);
        logger.info("Tariff plan {title: " + title + ", speed: " + speed + ", price: " + price + "} created");
    }

    public void editTariffPlan(TariffPlan tariffPlan) {
        adminDao.editTariffPlan(tariffPlan);
        logger.info("Tariff plan{" + tariffPlan.getId() + "} edited");
    }

    public void assignTariffPlanToClient(long clientId, int tariffPlanId) {
        adminDao.assignTariffPlanToClient(clientId, tariffPlanId);
    }

    public TariffPlan getTariffPlanByClientId(long clientId) {
        return adminDao.getTariffPlanByClientId(clientId);
    }

    public TariffPlan getTariffPlanById(int tariffPlanId) {
        return adminDao.getTariffPlanById(tariffPlanId);
    }

    public List<TariffPlan> retrieveTariffPlans() {
        return adminDao.retrieveTariffPlans();
    }

    public void makePaymentAndChangeTariff(long clientId, int tariffId, Payment payment) {
        adminDao.makePaymentAndChangeTariffPlan(clientId, tariffId, payment);
        logger.info("Client{" + clientId + "} changed tariff plan to {" + tariffId + "}");
    }

}
