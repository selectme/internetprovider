package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.impl.AdminDaoDbImpl;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.service.impl.AdminServiceDbImpl;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;


public class AdminServiceTest {

    private AdminService adminService;
    private AdminDao adminDao;
    TariffPlan expectedTariff;
    TariffPlan expectedTariff2;

    @BeforeMethod
    public void setUp() throws Exception {
        adminDao = mock(AdminDaoDbImpl.class);
        adminService = new AdminServiceDbImpl(adminDao);

         expectedTariff = new TariffPlan();
        expectedTariff.setId(1);
        expectedTariff.setTitle("Test tariff");
        expectedTariff.setSpeed(100);
        expectedTariff.setPrice(new BigDecimal(50));

         expectedTariff2 = new TariffPlan();
        expectedTariff.setId(2);
        expectedTariff.setTitle("Test tariff2");
        expectedTariff.setSpeed(150);
        expectedTariff.setPrice(new BigDecimal(75));

    }

    @Test
    public void testGetClientById() throws SQLException {

        Client expectedClient = new Client();
        expectedClient.setId(99L);
        expectedClient.setName("Test");

        when(adminService.getClientById(99L)).thenReturn(expectedClient);

        Client actualClient = adminService.getClientById(99L);

        assertEquals(actualClient.getId(), expectedClient.getId());
        assertEquals(actualClient.getName(), expectedClient.getName());
    }


    @Test
    public void testRetrieveClients() {
        List<Client> expectedClientList = new ArrayList<>();
        Client expectedClient1 = new Client();
        expectedClient1.setId(99L);
        expectedClient1.setName("Client1");

        Client expectedClient2 = new Client();
        expectedClient2.setId(99L);
        expectedClient2.setName("Client2");
        expectedClientList.add(expectedClient1);
        expectedClientList.add(expectedClient2);

        when(adminService.retrieveClients()).thenReturn(expectedClientList);

        List<Client> actualClientList = adminService.retrieveClients();

        assertEquals(actualClientList, expectedClientList);
    }

    @Test
    public void testGetTariffPlanByClientId() {

        Client client = new Client();
        client.setId(99L);
        client.setTariffPlan(expectedTariff);

        when(adminService.getTariffPlanByClientId(client.getId())).thenReturn(expectedTariff);

        TariffPlan actualTariff = adminService.getTariffPlanByClientId(99L);

        assertEquals(actualTariff, expectedTariff);
    }

    @Test
    public void testGetTariffPlanById() {

        when(adminService.getTariffPlanById(expectedTariff.getId())).thenReturn(expectedTariff);

        TariffPlan actualTariff = adminService.getTariffPlanById(2);
        assertEquals(actualTariff, expectedTariff);
    }

    @Test
    public void testRetrieveTariffPlans() {
        List<TariffPlan> expectedTariffPlans = new ArrayList<>();
        expectedTariffPlans.add(expectedTariff);
        expectedTariffPlans.add(expectedTariff2);

        when(adminService.retrieveTariffPlans()).thenReturn(expectedTariffPlans);

        List<TariffPlan> actualTariffPlans = adminService.retrieveTariffPlans();

        assertEquals(actualTariffPlans, expectedTariffPlans);
    }
}