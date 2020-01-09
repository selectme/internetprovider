package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.tarifftypes.TariffType;
import by.epam.learn.mudrahelau.util.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Viktar on 27.12.2019
 */
public class ProviderDaoImpl {
    private static final String GET_ALL_TARIFFS = "SELECT * from tariffs";
    private static final String GET_ALL_CLIENTS = "SELECT * from clients";
    private static final int TARIFF_TITLE_COLUMN = 2;
    private static final int TARIFF_TYPE_COLUMN = 3;
    private static final int TARIFF_SPEED_COLUMN = 4;
    private static final int TARIFF_PRICE_COLUMN = 5;

    public void createClient(String login, String password, String name, String surname, TariffPlan tariffPlan) throws SQLException {
        PreparedStatement preparedStatement = DBUtils.getDbConnection()
                .prepareStatement("INSERT into clients(login, password, name, surname, tariff) values (?,?,?,?,?)");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, surname);
            preparedStatement.setString(5, tariffPlan.getTitle());
            preparedStatement.execute();
            preparedStatement.close();
    }

    public List<Client> retrieveClients() throws SQLException {
        List<Client> clients = new ArrayList<>();
        Statement statement = DBUtils.getStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_CLIENTS);
        while (resultSet.next()) {
            String login = resultSet.getString(2);
            String password = resultSet.getString(3);
            String name = resultSet.getString(4);
            String surname = resultSet.getString(5);
            String tariffPlanTitle = resultSet.getString(6);
            TariffPlan tariffPlan = findTariffPlan(tariffPlanTitle);
            Client client = new Client(login, password, name, surname, tariffPlan);
            clients.add(client);
        }
        return clients;
    }

    private TariffPlan findTariffPlan(String tariffPlanTitle) throws SQLException {
        TariffPlan tariffPlan = null;
        PreparedStatement preparedStatement = DBUtils.getDbConnection().prepareStatement("SELECT * from tariffs where title=?");
        preparedStatement.setString(1, tariffPlanTitle);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            String title = resultSet.getString(TARIFF_TITLE_COLUMN);
            TariffType tariffType = TariffType.valueOf(resultSet.getString(TARIFF_TYPE_COLUMN).toUpperCase());
            int speed = resultSet.getInt(TARIFF_SPEED_COLUMN);
            double price = resultSet.getDouble(TARIFF_PRICE_COLUMN);
            tariffPlan = new TariffPlan(title, speed, tariffType, price);
        }
        return tariffPlan;
    }

    public List<TariffPlan> retrieveTariffPlans() throws SQLException {
        List<TariffPlan> tariffPlans = new ArrayList<>();
        Statement statement = DBUtils.getStatement();
        ResultSet resultSet = statement.executeQuery(GET_ALL_TARIFFS);
        while (resultSet.next()) {
            String title = resultSet.getString(TARIFF_TITLE_COLUMN);
            TariffType tariffType = TariffType.valueOf(resultSet.getString(TARIFF_TYPE_COLUMN).toUpperCase());
            int speed = resultSet.getInt(TARIFF_SPEED_COLUMN);
            double price = resultSet.getDouble(TARIFF_PRICE_COLUMN);
            TariffPlan tariffPlan = new TariffPlan(title, speed, tariffType, price);
            tariffPlans.add(tariffPlan);
        }
        resultSet.close();
        return tariffPlans;
    }

    public void editTariffPlan(TariffPlan tariffPlan) throws SQLException {
        PreparedStatement preparedStatement = DBUtils.getDbConnection().
                prepareStatement("UPDATE tariffs SET title = ?, type = ?, speed = ?, price = ? where title = ?");
        preparedStatement.setString(1, tariffPlan.getTitle());
        preparedStatement.setString(2, tariffPlan.getTariffType().label);
        preparedStatement.setInt(3, tariffPlan.getSpeed());
        preparedStatement.setDouble(4, tariffPlan.getPrice());
        preparedStatement.setString(5, tariffPlan.getTitle());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void makePayment(Payment payment) throws SQLException {
        PreparedStatement preparedStatement = DBUtils.getDbConnection().prepareStatement("INSERT into payments(client_login, amount, date) " +
                "values (?,?,?)");
        preparedStatement.setString(1, payment.getClient().getLogin());
        preparedStatement.setDouble(2, payment.getAmount());
        preparedStatement.setDate(3, new java.sql.Date(payment.getDate().getTimeInMillis()));
        preparedStatement.execute();
        preparedStatement.close();

    }

}
