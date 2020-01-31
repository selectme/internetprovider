package by.epam.learn.mudrahelau.sheduler;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.service.AdminService;
import by.epam.learn.mudrahelau.service.ClientService;
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

    private AdminService adminService = new AdminService();
    private ClientService clientService = new ClientService();

    @Override
    public void run() {
        today = LocalDateTime.now();
        Map<Long, Integer> clientsId = clientService.retrieveActiveClientsId();

        for (Map.Entry<Long, Integer> client : clientsId.entrySet()) {
            System.out.println(client.getKey());
            System.out.println(clientService.retrieveLastDebitDate(client.getKey()));
            if (clientService.retrieveLastDebitDate(client.getKey()).plusDays(30).compareTo(today) < 0) {
                BigDecimal clientBalance = clientService.retrieveClientMoneyAmountByClientId(client.getKey());
                BigDecimal tariffPlanPrice = adminService.getTariffPlanById(client.getValue()).getPrice();

                if (clientBalance.compareTo(tariffPlanPrice) >= 0) {
                    Payment payment = new Payment();
                    payment.setClientId(client.getKey());
                    payment.setAmount(tariffPlanPrice.negate());
                    payment.setPaymentType(PaymentType.DEBIT);
                    payment.setDate(today);
                    clientService.makePayment(payment);
                    if (adminService.getClientById(client.getKey()).getStatus() != ClientStatus.ACTIVE) {
                        clientService.changeClientStatus(client.getKey(), ClientStatus.ACTIVE);
                    }
                } else {
                    clientService.changeClientStatus(client.getKey(), ClientStatus.BLOCKED);
                }
            } else {
                System.out.println("all is ok");
            }

        }

    }
}
