package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.status.ClientStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ClientService {
    private static final Logger logger = LogManager.getLogger(ClientService.class);
    private ClientDao clientDao = new ClientDao();


    public void editClientByClient(Client client) {
        clientDao.editClientByClient(client);
        logger.info("Client{" + client.getId() + "} edited");
    }

    public void makePayment(Payment payment) {
        clientDao.makePayment(payment);
        if (payment.getAmount().compareTo(new BigDecimal(0)) > 0)
            logger.info("Client{" + payment.getClientId() + "} paid " + payment.getAmount());
    }

    public List<Payment> retrievePayments(long clientId) {
        logger.info("Client{" + clientId + "} requested the payments history");
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

    public Map<Long, Integer> retrieveActiveClientsId() {
        return clientDao.retrieveActiveClientsId();
    }

    public LocalDateTime retrieveLastDebitDate(long clientId) {
        return clientDao.retrieveLastDebitDate(clientId);
    }
}
