package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.tarifftypes.TariffType;
import by.epam.learn.mudrahelau.util.DBUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Viktar on 27.12.2019
 */
public class ProviderDaoImpl {

    public List<TariffPlan> retrieveTariffPlans() throws SQLException {
        List<TariffPlan> tariffPlans = new ArrayList<>();
        Statement statement = DBUtils.getStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * from tariffs");
        while (resultSet.next()) {
            String title = resultSet.getString(2);
            TariffType tariffType = TariffType.valueOf(resultSet.getString(3).toUpperCase());
            int speed = resultSet.getInt(4);
            double price = resultSet.getDouble(5);
            TariffPlan tariffPlan = new TariffPlan(title, speed, tariffType, price);
            tariffPlans.add(tariffPlan);
        }
        resultSet.close();
        return tariffPlans;
    }

}
