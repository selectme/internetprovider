package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.role.Role;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class AdminService {

    private AdminDao adminDao = new AdminDao();

    public void createUser(String login, String password, String name, String surname, Role role) {
        adminDao.createUser(login, password, name, surname, role);
    }

    public void deleteUserById(long userId) {
        adminDao.deleteUserById(userId);
    }

    public void deleteTariffPlanById(int tariffPlanId) {
        adminDao.deleteTariffPlanById(tariffPlanId);
    }

    public Client getClientById(long id) {
        return adminDao.getClientById(id);
    }

    public void editClientByAdmin(Client client) {
        adminDao.editClientByAdmin(client);
    }

    public List<Client> retrieveClients() {
        return adminDao.retrieveClients();
    }

    public void createTariffPlan(String title, int speed, BigDecimal price) {
        adminDao.createTariffPlan(title, speed, price);
    }

    public void editTariffPlan(TariffPlan tariffPlan) {
        adminDao.editTariffPlan(tariffPlan);
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

}
