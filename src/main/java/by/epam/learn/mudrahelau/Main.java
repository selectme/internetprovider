package by.epam.learn.mudrahelau;

import by.epam.learn.mudrahelau.dao.AdminDao;
import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Viktar on 27.12.2019
 */
public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {

        AdminDao adminDao = new AdminDao();
        ClientDao clientDao = new ClientDao();
        Client client = new Client();

        BigDecimal bigDecimal = new BigDecimal(10.5);
        BigDecimal bigDecimal2 = new BigDecimal(5.3);
        MathContext mathContext = new MathContext(2);

        System.out.println(bigDecimal.subtract(bigDecimal2, mathContext));
    }
}
