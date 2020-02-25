package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AdminService {
    private static final Logger logger = LogManager.getLogger(AdminService.class);

    private AdminDao adminDao = new AdminDao();

    public void createUser(User user) {
        adminDao.createUser(user);
        logger.info(LoggerConstants.USER_CREATED, user.getName(), user.getSurname(), user.getRole());
    }

    public void deleteUserById(long userId) {
        adminDao.deleteUserById(userId);
        logger.info(LoggerConstants.USER_DELETED, userId);
    }

    public void deleteTariffPlanById(int tariffPlanId) {
        adminDao.deleteTariffPlanById(tariffPlanId);
        logger.info(LoggerConstants.TARIFF_DELETED, tariffPlanId);
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

    public void createTariffPlan(TariffPlan tariffPlan) {
        adminDao.createTariffPlan(tariffPlan);
        logger.info(LoggerConstants.TARIFF_CREATED, tariffPlan.getTitle(), tariffPlan.getSpeed(), tariffPlan.getPrice());
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
