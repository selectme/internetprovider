package by.epam.learn.mudrahelau;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.dao.ProviderDaoImpl;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Viktar on 27.12.2019
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        AdminDao adminDao = new AdminDao();


        List<Client> clients = adminDao.retrieveClients();
        Client client = clients.get(0);
        Calendar calendar = new GregorianCalendar();
        Payment payment = new Payment(client, 24, new Date());

        ClientDao clientDao = new ClientDao();
//        clientDao.makePayment(payment);

       List<Payment> payments = clientDao.retrievePayments(client);

        for (Payment payment1 : payments) {
            logger.info(payment1);
        }
//        TariffPlan tariffPlan = new TariffPlan(3,"superXXL", 200, 35);
//        adminDao.editTariffPlan(tariffPlan);
//
//

    }
}
