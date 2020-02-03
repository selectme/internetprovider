package by.epam.learn.mudrahelau.sheduler;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.service.ClientService;
import by.epam.learn.mudrahelau.status.ClientStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TimerTask;

/**
 * @author Viktar on 29.01.2020
 */
public class PaymentChecker extends TimerTask {
    private static final Logger logger = LogManager.getLogger(PaymentChecker.class);
    private AdminService adminService = new AdminService();
    private ClientService clientService = new ClientService();
    private static final int DAYS_AFTER_THE_LAST_DEBIT_PAYMENT = 1;

    @Override
    public void run() {
        LocalDateTime today = LocalDateTime.now();
        Map<Long, Integer> clientsId = clientService.retrieveActiveClientsId();

        for (Map.Entry<Long, Integer> clientIdValues : clientsId.entrySet()) {
            Client client = adminService.getClientById(clientIdValues.getKey());
            if (clientService.retrieveLastDebitDate(client.getId()).plusDays(DAYS_AFTER_THE_LAST_DEBIT_PAYMENT).compareTo(today) < 0) {

                BigDecimal clientBalance = clientService.retrieveClientMoneyAmountByClientId(client.getId());
                BigDecimal tariffPlanPrice = adminService.getTariffPlanById(clientIdValues.getValue()).getPrice();

                if (clientBalance.compareTo(tariffPlanPrice) >= 0) {

                    Payment payment = new Payment();
                    payment.setClientId(client.getId());
                    payment.setAmount(tariffPlanPrice.negate());
                    payment.setPaymentType(PaymentType.DEBIT);
                    payment.setDate(today);
                    clientService.makePayment(payment);
                    logger.info("Client {" + client.getId() + "} paid " + tariffPlanPrice
                            + " for the tariff {" + clientIdValues.getValue() + "}");
                    if (client.getStatus() != ClientStatus.ACTIVE) {
                        clientService.changeClientStatus(client.getId(), ClientStatus.ACTIVE);
                        logger.info("Client {" + client.getId() + "} status changed to " + ClientStatus.ACTIVE);
                    }
                } else if (client.getStatus() != ClientStatus.BLOCKED) {
                    clientService.changeClientStatus(client.getId(), ClientStatus.BLOCKED);
                    logger.info("Client {" + client.getId() + "} does not have enough money on balance." +
                            " Status changed to " + ClientStatus.BLOCKED);
                }
            } else {
                logger.info("Client {" + client.getId() + "}. The bill is paid.");
            }
        }
    }
}
