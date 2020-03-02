package by.epam.learn.mudrahelau.scheduler;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import by.epam.learn.mudrahelau.dao.impl.AdminDaoDbImpl;
import by.epam.learn.mudrahelau.dao.impl.ClientDaoDbImpl;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.service.ClientService;
import by.epam.learn.mudrahelau.service.impl.AdminServiceDbImpl;
import by.epam.learn.mudrahelau.service.impl.ClientServiceDbImpl;
import by.epam.learn.mudrahelau.status.ClientStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.TimerTask;

import static by.epam.learn.mudrahelau.constant.LoggerConstants.CLIENT_DOES_NOT_HAVE_ARREARS;

/**
 * PaymentChecker is used for checking {@link Client} is paid the bill. Otherwise PaymentChecker will try to make
 * {@link Payment} based on the price of {@link by.epam.learn.mudrahelau.model.TariffPlan}. In case that {@link Client}
 * has no enough money on the balance his {@link ClientStatus} will be changed to BLOCKED until {@link Client}
 * replenishes the balance.
 */
public class PaymentChecker extends TimerTask {
    private static final Logger logger = LogManager.getLogger(PaymentChecker.class);
    /**
     * {@link AdminService} {@link AdminServiceDbImpl}
     */
    private AdminService adminService = new AdminServiceDbImpl(new AdminDaoDbImpl());
    /**
     * {@link ClientService} {@link ClientServiceDbImpl}
     */
    private ClientService clientService = new ClientServiceDbImpl(new ClientDaoDbImpl());

    /**
     * Number of days after the last debit payment. Debit payment means that {@link by.epam.learn.mudrahelau.model.TariffPlan}
     * was paid. PaymentChecker checks the last date of debit payment and in case that current date is higher than the last
     * debit date PaymentChecker will try to make {@link Payment}.
     */
    private static final int DAYS_AFTER_THE_LAST_DEBIT_PAYMENT = 1;
    private static final int ZERO_VALUE = 0;

    @Override
    public void run() {

        LocalDateTime today = LocalDateTime.now();
        Map<Long, Integer> clientsId = clientService.retrieveActiveClientsId();

        for (Map.Entry<Long, Integer> clientIdValues : clientsId.entrySet()) {
            Client client = adminService.getClientById(clientIdValues.getKey());
            if (clientService.retrieveLastDebitDate(client.getId()).plusDays(DAYS_AFTER_THE_LAST_DEBIT_PAYMENT).compareTo(today) < ZERO_VALUE) {

                BigDecimal clientBalance = clientService.retrieveClientMoneyAmountByClientId(client.getId());
                BigDecimal tariffPlanPrice = adminService.getTariffPlanById(clientIdValues.getValue()).getPrice();

                if (clientBalance.compareTo(tariffPlanPrice) >= ZERO_VALUE) {

                    Payment payment = new Payment();
                    payment.setClientId(client.getId());
                    payment.setAmount(tariffPlanPrice.negate());
                    payment.setPaymentType(PaymentType.DEBIT);
                    payment.setDate(today);
                    clientService.makePayment(payment);
                    logger.info(LoggerConstants.CLIENT_PAID, client.getId(), payment.getAmount(), clientIdValues.getValue());
                    if (client.getStatus() != ClientStatus.ACTIVE) {
                        clientService.changeClientStatus(client.getId(), ClientStatus.ACTIVE);
                        logger.info(LoggerConstants.CLIENT_STATUS_CHANGED, client.getId(), ClientStatus.ACTIVE);
                    }
                } else if (client.getStatus() != ClientStatus.BLOCKED) {
                    clientService.changeClientStatus(client.getId(), ClientStatus.BLOCKED);
                    logger.info(LoggerConstants.CLIENT_DOES_NOT_HAVE_MONEY, client.getId());
                    logger.info(LoggerConstants.CLIENT_STATUS_CHANGED, client.getId(), ClientStatus.BLOCKED);
                }
            } else {
                logger.info(CLIENT_DOES_NOT_HAVE_ARREARS, client.getId());
            }
        }
    }
}
