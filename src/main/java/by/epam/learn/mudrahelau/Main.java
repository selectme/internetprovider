package by.epam.learn.mudrahelau;

import by.epam.learn.mudrahelau.dao.ProviderDaoImpl;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.tariffplans.Tariffs;
import by.epam.learn.mudrahelau.tarifftypes.TariffType;
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
        ProviderDaoImpl providerDao = new ProviderDaoImpl();
        List<TariffPlan> tariffPlans = new ProviderDaoImpl().retrieveTariffPlans();
        for (TariffPlan tariffPlan : tariffPlans) {
            System.out.println(tariffPlan);
        }

        TariffPlan tariffSuper = new TariffPlan("super", 100, TariffType.UNLIM, 30);
//
//        providerDao.editTariffPlan(tariffPlan);

        providerDao.createClient("141414", "f2f2", "витя", "м", tariffSuper);

        logger.info("list of clients: ");
        List<Client> clients = new ProviderDaoImpl().retrieveClients();
        for (Client client : clients) {
            logger.info(client);
        }

        Payment payment = new Payment(clients.get(0), 24,
                new GregorianCalendar(2020, Calendar.JANUARY, 9));
        providerDao.makePayment(payment);
    }
}
