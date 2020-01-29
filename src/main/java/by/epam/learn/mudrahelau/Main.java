package by.epam.learn.mudrahelau;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.sheduler.ScheduleExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Map;

/**
 * @author Viktar on 27.12.2019
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static Object PaymentChecker;

    public static void main(String[] args) throws SQLException {

        AdminDao adminDao = new AdminDao();
        ClientDao clientDao = new ClientDao();
        Client client = new Client();


        ScheduleExecutor scheduleExecutor = new ScheduleExecutor();
        scheduleExecutor.execute();



    }
}
