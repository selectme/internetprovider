package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.util.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final String GET_ALL_TARIFFS_SQL = "SELECT * from tariff_plan";
    private static final String GET_ALL_CLIENTS_SQL = "SELECT * from user";
    private static final String GET_TARIFF_PLAN_BY_CLIENT_ID_SQL = "SELECT * from tariff_plan tp " +
            "JOIN user_tariffplan ut ON tp.id=ut.tariff_id where user_id = ?";
    private static final String UPDATE_TARIFF_PLAN_SQL = "UPDATE tariff_plan SET title = ?, speed = ?, price = ? where id = ?";
    private static final int TARIFF_CLIENT_ID_COLUMN = 1;
    private static final int TARIFF_TITLE_COLUMN = 2;
    private static final int TARIFF_SPEED_COLUMN = 3;
    private static final int TARIFF_PRICE_COLUMN = 4;

    public List<Client> retrieveClients() {
        List<Client> clients = new ArrayList<>();
        ResultSet resultSet;

        try (PreparedStatement preparedStatement = DBUtils.getDbConnection().prepareStatement(GET_ALL_CLIENTS_SQL)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long clientId = resultSet.getLong(TARIFF_CLIENT_ID_COLUMN);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String surname = resultSet.getString(5);
                Role role = Role.valueOf(resultSet.getString(6).toUpperCase());
                if (role == Role.CLIENT) {
                    TariffPlan tariffPlan = findTariffPlanByClientId(clientId);
                    Client client = new Client(clientId, login, password, name, surname, tariffPlan);
                    clients.add(client);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return clients;
    }

    private TariffPlan findTariffPlanByClientId(long userId) {
        TariffPlan tariffPlan = null;
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = DBUtils.getDbConnection().prepareStatement(GET_TARIFF_PLAN_BY_CLIENT_ID_SQL)) {
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString(TARIFF_TITLE_COLUMN);
                int speed = resultSet.getInt(TARIFF_SPEED_COLUMN);
                double price = resultSet.getDouble(TARIFF_PRICE_COLUMN);
                tariffPlan = new TariffPlan(title, speed, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tariffPlan;
    }

    public List<TariffPlan> retrieveTariffPlans() {
        List<TariffPlan> tariffPlans = new ArrayList<>();
        ResultSet resultSet;
        try (PreparedStatement preparedStatement = DBUtils.getDbConnection().prepareStatement(GET_ALL_TARIFFS_SQL)) {
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString(TARIFF_TITLE_COLUMN);
                int speed = resultSet.getInt(TARIFF_SPEED_COLUMN);
                double price = resultSet.getDouble(TARIFF_PRICE_COLUMN);
                TariffPlan tariffPlan = new TariffPlan(title, speed, price);
                tariffPlans.add(tariffPlan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tariffPlans;
    }

    public void editTariffPlan(TariffPlan tariffPlan) {
        try (PreparedStatement preparedStatement = DBUtils.getDbConnection().
                prepareStatement(UPDATE_TARIFF_PLAN_SQL)) {
            preparedStatement.setString(1, tariffPlan.getTitle());
            preparedStatement.setInt(2, tariffPlan.getSpeed());
            preparedStatement.setDouble(3, tariffPlan.getPrice());
            preparedStatement.setInt(4, tariffPlan.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
