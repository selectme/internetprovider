package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.hash.PasswordHash;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.util.DBUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final String GET_ALL_TARIFFS_SQL = "SELECT * from tariff_plan";
    private static final String GET_ALL_CLIENTS_SQL = "SELECT * from user";
    private static final String GET_TARIFF_PLAN_BY_CLIENT_ID_SQL = "SELECT * from tariff_plan tp " +
            "JOIN user_tariffplan ut ON tp.id=ut.tariff_id where user_id = ?";
    private static final String UPDATE_TARIFF_PLAN_SQL = "UPDATE tariff_plan SET title = ?, speed = ?, price = ? where id = ?";
    private final static String CREATE_CLIENT_SQL = "INSERT INTO " +
            "user (login, password, name, surname, role) VALUES (?,?,?,?,?) ";
    //    private final static String INSERT_USER_AND_TARIFF_ID_SQL = "INSERT INTO " +
//            "user_tariffplan (user_id) " +
//            "SELECT u.id " +
//            "FROM user as u " +
//            "WHERE u.id=?";
//    private final static String INSERT_USER_AND_TARIFF_ID_SQL = "INSERT INTO " +
//            "user_tariffplan (user_id, tariff_id) " +
//            "SELECT u.id, t.id " +
//            "FROM user as u, tariff_plan as t " +
//            "WHERE u.id=? AND t.id=?";
    private final static String INSERT_USER_AND_TARIFF_ID_SQL = "UPDATE user_tariffplan SET tariff_id=? " +
            "WHERE user_id = ?";
    private final static String CREATE_TARIFF_PLAN_SQL = "INSERT into tariff_plan(title, speed, price) values (?,?,?)";
    private static final int TARIFF_CLIENT_ID_COLUMN = 1;
    private static final int TARIFF_TITLE_COLUMN = 2;
    private static final int TARIFF_SPEED_COLUMN = 3;
    private static final int TARIFF_PRICE_COLUMN = 4;

    public List<Client> retrieveClients() {
        List<Client> clients = new ArrayList<>();
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLIENTS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long clientId = resultSet.getLong(TARIFF_CLIENT_ID_COLUMN);
                String login = resultSet.getString(2);
                String password = resultSet.getString(3);
                String name = resultSet.getString(4);
                String surname = resultSet.getString(5);
                Role role = Role.valueOf(resultSet.getString(6).toUpperCase());
                if (role == Role.CLIENT) {
                    TariffPlan tariffPlan = getTariffPlanByClientId(clientId);
                    Client client = new Client(clientId, login, password, name, surname, tariffPlan);
                    clients.add(client);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.releaseConnection(connection);
            }
        }
        return clients;
    }

    public Client getClientById(long id) {
        Client client = new Client();
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatementClient = connection.prepareStatement("SELECT id, login, name, surname FROM user " +
                "WHERE id = ?")
        ) {
            preparedStatementClient.setLong(1, id);
            ResultSet clientInfo = preparedStatementClient.executeQuery();
            while (clientInfo.next()) {
                long client_id = clientInfo.getLong("id");
                String login = clientInfo.getString("login");
                String name = clientInfo.getString("name");
                String surname = clientInfo.getString("surname");
                client.setId(client_id);
                client.setLogin(login);
                client.setName(name);
                client.setSurname(surname);
            }
            client.setTariffPlan(getTariffPlanByClientId(id));
            client.setMoneyOnAccount(new ClientDao().retrieveClientMoneyAmountByClientId(id));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.releaseConnection(connection);
        }
        return client;
    }

    public TariffPlan getTariffPlanByClientId(long clientId) {
        TariffPlan tariffPlan = null;
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TARIFF_PLAN_BY_CLIENT_ID_SQL)) {
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(TARIFF_TITLE_COLUMN);
                int speed = resultSet.getInt(TARIFF_SPEED_COLUMN);
                BigDecimal price = resultSet.getBigDecimal(TARIFF_PRICE_COLUMN);
                tariffPlan = new TariffPlan(id, title, speed, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.releaseConnection(connection);
            }
        }
        return tariffPlan;
    }

    public TariffPlan getTariffPlanById(int tariffPlanId) {
        TariffPlan tariffPlan = null;
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tariff_plan" +
                " WHERE id=?")) {
            preparedStatement.setLong(1, tariffPlanId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                int speed = resultSet.getInt(3);
                BigDecimal price = resultSet.getBigDecimal(4);
                tariffPlan = new TariffPlan(id, title, speed, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.releaseConnection(connection);
            }
        }
        return tariffPlan;
    }

    public void createUser(String login, String password, String name, String surname, Role role) {
        Connection connection = DBUtils.getConnection();
        try (
                PreparedStatement createUserStatement = connection.prepareStatement(CREATE_CLIENT_SQL);
                PreparedStatement assignToTariffPlanTableStatement = connection.prepareStatement("INSERT INTO" +
                        " user_tariffplan(user_id, tariff_id) VALUES (LAST_INSERT_ID(), ?)")) {
            connection.setAutoCommit(false);
            createUserStatement.setString(1, login);
            createUserStatement.setString(2, PasswordHash.hashPassword(password));
            createUserStatement.setString(3, name);
            createUserStatement.setString(4, surname);
            createUserStatement.setString(5, role.name());
            createUserStatement.execute();
            assignToTariffPlanTableStatement.setLong(1, 0);
            assignToTariffPlanTableStatement.execute();
            connection.commit();

        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBUtils.releaseConnection(connection);
            }
        }
    }

    public void assignTariffPlanToClient(long clientId, int tariffPlanId) {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_AND_TARIFF_ID_SQL)) {
            preparedStatement.setInt(1, tariffPlanId);
            preparedStatement.setLong(2, clientId);
            preparedStatement.execute();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.releaseConnection(connection);
            }
        }
    }

    public void createTariffPlan(String title, int speed, BigDecimal price) {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TARIFF_PLAN_SQL)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, speed);
            preparedStatement.setBigDecimal(3, price);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.releaseConnection(connection);
            }
        }
    }

    public List<TariffPlan> retrieveTariffPlans() {
        List<TariffPlan> tariffPlans = new ArrayList<>();
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TARIFFS_SQL);
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String title = resultSet.getString(2);
                int speed = resultSet.getInt(3);
                BigDecimal price = resultSet.getBigDecimal(4);
                if (id != 0) {
                    TariffPlan tariffPlan = new TariffPlan(id, title, speed, price);
                    tariffPlans.add(tariffPlan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.releaseConnection(connection);
            }
        }
        return tariffPlans;
    }

    public void editTariffPlan(TariffPlan tariffPlan) {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.
                prepareStatement(UPDATE_TARIFF_PLAN_SQL)) {
            preparedStatement.setString(1, tariffPlan.getTitle());
            preparedStatement.setInt(2, tariffPlan.getSpeed());
            preparedStatement.setBigDecimal(3, tariffPlan.getPrice());
            preparedStatement.setInt(4, tariffPlan.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.releaseConnection(connection);
            }
        }
    }
}
