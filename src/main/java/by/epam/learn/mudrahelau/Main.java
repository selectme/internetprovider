package by.epam.learn.mudrahelau;

import by.epam.learn.mudrahelau.dao.ProviderDaoImpl;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.TariffPlan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
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

        logger.info("list of clients: ");
        List<Client> clients = new ProviderDaoImpl().retrieveClients();
        for (Client client : clients) {
           logger.info(client);
        }
    }
}
