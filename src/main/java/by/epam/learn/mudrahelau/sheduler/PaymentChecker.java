package by.epam.learn.mudrahelau.sheduler;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.status.ClientStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * @author Viktar on 29.01.2020
 */
public class PaymentChecker extends TimerTask {

    private LocalDateTime today;
    private AdminDao adminDao = new AdminDao();
    private ClientDao clientDao = new ClientDao();

    @Override
    public void run() {
        today = LocalDateTime.now();
        System.out.println(today);
        Map<Long, Integer> clientsId = clientDao.retrieveActiveClientsId();

        for (Map.Entry<Long, Integer> client : clientsId.entrySet()) {
            System.out.println(client.getKey());
            System.out.println(clientDao.retrieveLastDebitDate(client.getKey()));
            if (clientDao.retrieveLastDebitDate(client.getKey()).plusDays(30).compareTo(today) < 0) {
                BigDecimal clientBalance = clientDao.retrieveClientMoneyAmountByClientId(client.getKey());
                BigDecimal tariffPlanPrice = adminDao.getTariffPlanById(client.getValue()).getPrice();

                if (clientBalance.compareTo(tariffPlanPrice) >= 0) {
                    Payment payment = new Payment();
                    payment.setClientId(client.getKey());
                    payment.setAmount(tariffPlanPrice.negate());
                    payment.setPaymentType(PaymentType.DEBIT);
                    payment.setDate(today);
                    clientDao.makePayment(payment);
                    if (adminDao.getClientById(client.getKey()).getStatus() != ClientStatus.ACTIVE) {
                        clientDao.changeClientStatus(client.getKey(), ClientStatus.ACTIVE);
                    }
                } else {
                    clientDao.changeClientStatus(client.getKey(), ClientStatus.BLOCKED);
                }
            } else {
                System.out.println("all is ok");
            }

        }

    }
}
