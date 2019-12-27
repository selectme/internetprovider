package by.epam.learn.mudrahelau;

import by.epam.learn.mudrahelau.dao.ProviderDaoImpl;
import by.epam.learn.mudrahelau.model.TariffPlan;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Viktar on 27.12.2019
 */
public class Main {
    public static void main(String[] args) throws SQLException {
        List<TariffPlan> tariffPlans = new ProviderDaoImpl().retrieveTariffPlans();
        for (TariffPlan tariffPlan : tariffPlans) {
            System.out.println(tariffPlan);
        }
    }
}
