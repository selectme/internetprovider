package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.status.ClientStatus;

import java.math.BigDecimal;
import java.util.List;

public class ClientService {

    private ClientDao clientDao = new ClientDao();


    public void editClientByClient(Client client) {
        clientDao.editClientByClient(client);
    }

    public void makePayment(Payment payment) {
        clientDao.makePayment(payment);
    }

    public List<Payment> retrievePayments(Long clientId) {
        return clientDao.retrievePayments(clientId);
    }

    public BigDecimal retrieveClientMoneyAmountByClientId(long clientId) {
        return clientDao.retrieveClientMoneyAmountByClientId(clientId);
    }

    public void changeClientStatus(long clientId, ClientStatus status) {
        clientDao.changeClientStatus(clientId, status);
    }

    public void removeTariffPlanFromClient(long clientId) {
        clientDao.removeTariffPlanFromClient(clientId);
    }
}
