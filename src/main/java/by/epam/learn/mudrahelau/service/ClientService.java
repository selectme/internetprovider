package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;

import java.math.BigDecimal;
import java.util.List;

public class ClientService {

    private ClientDao clientDao = new ClientDao();

    public void editClient(Client client) {
        clientDao.editClient(client);
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
}
