package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.dao.impl.ClientDaoDbImpl;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.service.impl.ClientServiceDbImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

/**
 * @author Viktar on 24.02.2020
 */
public class ClientServiceTest {

    private static int TEST_TARIFF_ID = 27;


    private User user;
    private TariffPlan tariffPlan;
    ClientService clientService;
    ClientDaoDbImpl clientDaoDb;
    Client clientExpected;

    @BeforeMethod
    public void setUp() {

        clientDaoDb = mock(ClientDaoDbImpl.class);
        clientService = new ClientServiceDbImpl(clientDaoDb);

        clientExpected = new Client();
        clientExpected.setId(99L);
    }

    @Test
    public void testRetrieveClientMoneyAmountByClientId() {

        BigDecimal amount = new BigDecimal(10);
        clientExpected.setMoneyOnAccount(amount);

        when(clientService.retrieveClientMoneyAmountByClientId(anyLong())).thenReturn(amount);

        BigDecimal actualAmount = clientService.retrieveClientMoneyAmountByClientId(clientExpected.getId());

        assertEquals(actualAmount, clientExpected.getMoneyOnAccount());
    }

    @Test
    public void testRetrievePayments() {
        List<Payment> paymentsExpected = new ArrayList<>();
        Payment payment1 = new Payment(clientExpected.getId(), new BigDecimal(10), PaymentType.DEBIT,
                LocalDateTime.of(2020, Month.FEBRUARY, 25, 15, 10));
        Payment payment2 = new Payment(clientExpected.getId(), new BigDecimal(20), PaymentType.DEBIT,
                LocalDateTime.of(2020, Month.FEBRUARY, 25, 15, 20));

        paymentsExpected.add(payment1);
        paymentsExpected.add(payment2);

        clientExpected.setPayments(paymentsExpected);

        when(clientService.retrievePayments(anyLong())).thenReturn(paymentsExpected);

        List<Payment> actualPayment = clientService.retrievePayments(99L);

        assertEquals(actualPayment, clientExpected.getPayments());
    }
}