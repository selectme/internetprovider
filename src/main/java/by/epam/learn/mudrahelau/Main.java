package by.epam.learn.mudrahelau;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.dao.ProviderDaoImpl;
import by.epam.learn.mudrahelau.dao.UserDao;
import by.epam.learn.mudrahelau.hash.PasswordHash;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
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


//        UserDao userDao = new UserDao();
        AdminDao adminDao = new AdminDao();
//        adminDao.assignTariffPlanToClient(26, 1);
//        adminDao.createUser("123", "123", "joe", "boom", Role.CLIENT);

        ClientDao clientDao = new ClientDao();
        List<Client> clients = adminDao.retrieveClients();
        Client client = clients.get(0);
//        Payment payment = new Payment(client, new BigDecimal(24), LocalDateTime.now());
//        clientDao.makePayment(payment);
//        clientDao.makePayment(payment);
//        clientDao.makePayment(payment);
//////

//////
//        ClientDao clientDao = new ClientDao();
//        clientDao.makePayment(payment);
////
       List<Payment> payments = clientDao.retrievePayments(client.getId());
//
        for (Payment payment1 : payments) {
            logger.info(payment1);
        }
////        TariffPlan tariffPlan = new TariffPlan(3,"superXXL", 200, 35);
////        adminDao.editTariffPlan(tariffPlan);
////
////



    }
}
