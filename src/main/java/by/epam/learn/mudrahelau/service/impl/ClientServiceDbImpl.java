package by.epam.learn.mudrahelau.service.impl;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.service.ClientService;
import by.epam.learn.mudrahelau.status.ClientStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * JDBC implementation of the {@link ClientService}
 *
 * @see ClientService
 */
public class ClientServiceDbImpl implements ClientService {

    private static final int COMPARE_TO_VALUE = 0;
    private static final Logger logger = LogManager.getLogger(ClientServiceDbImpl.class);
    /**
     * @see ClientDao
     */
    private ClientDao clientDao;

    public ClientServiceDbImpl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public void editClientByClient(Client client) {
        clientDao.editClientByClient(client);
        logger.info(LoggerConstants.CLIENT_EDITED, client.getId());
    }

    public void makePayment(Payment payment) {
        clientDao.makePayment(payment);
        if (payment.getAmount().compareTo(ParameterConstant.ZERO_PAYMENT) > COMPARE_TO_VALUE)
            logger.info(LoggerConstants.CLIENT_MADE_PAYMENT, payment.getClientId(), payment.getAmount());
    }

    public List<Payment> retrievePayments(long clientId) {
        logger.info(LoggerConstants.CLIENT_REQUESTED_PAYMENTS_HISTORY, clientId);
        return clientDao.retrievePayments(clientId);
    }

    public BigDecimal retrieveClientMoneyAmountByClientId(long clientId) {
        return clientDao.retrieveClientMoneyAmountByClientId(clientId);
    }

    public void changeClientStatus(long clientId, ClientStatus status) {
        clientDao.changeClientStatus(clientId, status);
        logger.info(LoggerConstants.CLIENT_STATUS_CHANGED, clientId, status);
    }


    public Map<Long, Integer> retrieveActiveClientsId() {
        return clientDao.retrieveActiveClientsId();
    }

    public LocalDateTime retrieveLastDebitDate(long clientId) {
        return clientDao.retrieveLastDebitDate(clientId);
    }
}
