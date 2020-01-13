package by.epam.learn.mudrahelau;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.ProviderDaoImpl;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author Viktar on 27.12.2019
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
//        List<TariffPlan> tariffPlans = new ProviderDaoImpl().retrieveTariffPlans();
//        for (TariffPlan tariffPlan : tariffPlans) {
//            System.out.println(tariffPlan);
//        }
//
//        ProviderDaoImpl providerDao = new ProviderDaoImpl();
//
//
        AdminDao adminDao = new AdminDao();
        logger.info("list of clients: ");
        List<Client> clients = adminDao.retrieveClients();
        for (Client client : clients) {
            logger.info(client);
        }
//        Calendar calendar = new GregorianCalendar();
//        Payment payment = new Payment(clients.get(1), 24,
//                new GregorianCalendar(2020, Calendar.JANUARY, 9));
//        providerDao.makePayment(payment);

        TariffPlan tariffPlan = new TariffPlan(3,"superXXL", 200, 35);
        adminDao.editTariffPlan(tariffPlan);


        List<TariffPlan> tariffPlans = adminDao.retrieveTariffPlans();
        for (TariffPlan tariff : tariffPlans) {
            System.out.println(tariff);
        }
    }


}
