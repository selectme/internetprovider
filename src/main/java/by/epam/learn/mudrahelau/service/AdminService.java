package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
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
        logger.info(LoggerConstants.USER_CREATED, name, surname, role);
    }

    public void deleteUserById(long userId) {
        adminDao.deleteUserById(userId);
        logger.info(LoggerConstants.USER_DELETED, userId);
    }

    public void deleteTariffPlanById(int tariffPlanId) {
        adminDao.deleteTariffPlanById(tariffPlanId);
        logger.info(LoggerConstants.USER_DELETED, tariffPlanId);
    }

    public Client getClientById(long id) {
        return adminDao.getClientById(id);
    }

    public void editClientByAdmin(Client client) {
        adminDao.editClientByAdmin(client);
        logger.info(LoggerConstants.CLIENT_EDITED, client.getId());
    }

    public List<Client> retrieveClients() {
        return adminDao.retrieveClients();
    }

    public void createTariffPlan(String title, int speed, BigDecimal price) {
        adminDao.createTariffPlan(title, speed, price);
        logger.info(LoggerConstants.TARIFF_CREATED, title, speed, price);
    }

    public void editTariffPlan(TariffPlan tariffPlan) {
        adminDao.editTariffPlan(tariffPlan);
        logger.info(LoggerConstants.TARIFF_EDITED, tariffPlan);
    }

    public void makeInactiveClient(long clientId) {
        adminDao.makeInactiveClient(clientId);
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
        logger.info(LoggerConstants.CLIENT_CHANGED_TARIFF, clientId, tariffId);
    }

}
