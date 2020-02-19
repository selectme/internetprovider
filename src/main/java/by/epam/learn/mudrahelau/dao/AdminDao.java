package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.constant.DbConstants;
import by.epam.learn.mudrahelau.constant.ParameterConstant;
import by.epam.learn.mudrahelau.hash.PasswordHash;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.status.ClientStatus;
import by.epam.learn.mudrahelau.util.DBUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDao {
    private static final String GET_CLIENT_BY_ID_SQL = "SELECT id, login, name, surname, status FROM user WHERE id = ?";
    private static final String GET_TARIFF_BY_ID_SQL = "SELECT * FROM tariff_plan WHERE id=?";
    private static final String GET_ALL_TARIFFS_SQL = "SELECT * from tariff_plan";
    private static final String GET_ALL_CLIENTS_SQL = "SELECT * from user";
    private static final String GET_TARIFF_PLAN_BY_CLIENT_ID_SQL = "SELECT * from tariff_plan tp " +
            "JOIN user_tariffplan ut ON tp.id=ut.tariff_id where user_id = ?";
    private final static String CREATE_CLIENT_SQL = "INSERT INTO " +
            "user (login, password, name, surname, role, status) VALUES (?,?,?,?,?,?) ";
    private final static String CREATE_TARIFF_PLAN_SQL = "INSERT into tariff_plan(title, speed, price) values (?,?,?)";
    private final static String CREATE_USER_SQL = "INSERT INTO user_tariffplan(user_id, tariff_id) VALUES (LAST_INSERT_ID(), ?)";
    private final static String DELETE_USER_BY_ID_SQL = "DELETE FROM user WHERE id=?";
    private final static String DELETE_TARIFF_BY_ID_SQL = "DELETE FROM tariff_plan WHERE id=?";
    private final static String SET_FAKE_TARIFF_TO_USER_SQL = "UPDATE user_tariffplan SET tariff_id=0 WHERE user_id = ?";
    private final static String MAKE_INACTIVE_CLIENT_SQL = "UPDATE user_tariffplan SET tariff_id=0 WHERE user_id = ?";
    private final static String MAKE_PAYMENT_SQL = "INSERT into payments(client_id, amount, type, date) values (?,?,?,?)";
    private final static String MAKE_ACTIVE_CLIENT = "UPDATE user SET status='ACTIVE' WHERE id=?";
    private static final String UPDATE_TARIFF_PLAN_SQL = "UPDATE tariff_plan SET title = ?, speed = ?, price = ? where id = ?";
    private final static String UPDATE_USER_TARIFF = "UPDATE user_tariffplan SET tariff_id=? WHERE user_id = ?";
    private final static String UPDATE_USER_SQL = "UPDATE user SET  name=?, surname=?, status=? where id=?";


    public List<Client> retrieveClients() {
        List<Client> clients = new ArrayList<>();
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_CLIENTS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long clientId = resultSet.getLong(DbConstants.ID);
                String login = resultSet.getString(DbConstants.LOGIN);
                String password = resultSet.getString(DbConstants.PASSWORD);
                String name = resultSet.getString(DbConstants.NAME);
                String surname = resultSet.getString(DbConstants.SURNAME);
                Role role = Role.valueOf(resultSet.getString(DbConstants.ROLE).toUpperCase());
                ClientStatus status = ClientStatus.valueOf(resultSet.getString(DbConstants.STATUS).toUpperCase());
                if (role == Role.CLIENT) {
                    TariffPlan tariffPlan = getTariffPlanByClientId(clientId);
                    Client client = new Client(clientId, login, password, name, surname, tariffPlan, status);
                    clients.add(client);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.getInstance().releaseConnection(connection);
            }
        }
        return clients;
    }

    public Client getClientById(long id) {
        Client client = new Client();
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatementClient = connection.prepareStatement(GET_CLIENT_BY_ID_SQL)
        ) {
            preparedStatementClient.setLong(1, id);
            ResultSet clientInfo = preparedStatementClient.executeQuery();
            while (clientInfo.next()) {
                long client_id = clientInfo.getLong(DbConstants.ID);
                String login = clientInfo.getString(DbConstants.LOGIN);
                String name = clientInfo.getString(DbConstants.NAME);
                String surname = clientInfo.getString(DbConstants.SURNAME);
                ClientStatus status = ClientStatus.valueOf(clientInfo.getString(DbConstants.STATUS));
                client.setId(client_id);
                client.setLogin(login);
                client.setName(name);
                client.setSurname(surname);
                client.setStatus(status);
            }
            client.setTariffPlan(getTariffPlanByClientId(id));
            client.setMoneyOnAccount(new ClientDao().retrieveClientMoneyAmountByClientId(id));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
        return client;
    }

    public TariffPlan getTariffPlanByClientId(long clientId) {
        TariffPlan tariffPlan = null;
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TARIFF_PLAN_BY_CLIENT_ID_SQL)) {
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(DbConstants.TARIFF_ID);
                String title = resultSet.getString(DbConstants.TITLE);
                int speed = resultSet.getInt(DbConstants.SPEED);
                BigDecimal price = resultSet.getBigDecimal(DbConstants.PRICE);
                tariffPlan = new TariffPlan(id, title, speed, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
        return tariffPlan;
    }

    public TariffPlan getTariffPlanById(int tariffPlanId) {
        TariffPlan tariffPlan = null;
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TARIFF_BY_ID_SQL)) {
            preparedStatement.setLong(1, tariffPlanId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(DbConstants.ID);
                String title = resultSet.getString(DbConstants.TITLE);
                int speed = resultSet.getInt(DbConstants.SPEED);
                BigDecimal price = resultSet.getBigDecimal(DbConstants.PRICE);
                tariffPlan = new TariffPlan(id, title, speed, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
        return tariffPlan;
    }

    //todo too difficult?
    public void createUser(String login, String password, String name, String surname, Role role) {
        Connection connection = DBUtils.getInstance().getConnection();
        PreparedStatement assignToTariffPlanTableStatement = null;
        try (PreparedStatement createUserStatement = connection.prepareStatement(CREATE_CLIENT_SQL)
        ) {
            connection.setAutoCommit(false);
            createUserStatement.setString(1, login);
            createUserStatement.setString(2, PasswordHash.hashPassword(password));
            createUserStatement.setString(3, name);
            createUserStatement.setString(4, surname);
            createUserStatement.setString(5, role.name());
            if (role == Role.ADMIN) {
                {
                    createUserStatement.setString(6, ClientStatus.ACTIVE.name());
                    createUserStatement.execute();
                }
            } else if (role == Role.CLIENT) {
                assignToTariffPlanTableStatement = connection.prepareStatement(CREATE_USER_SQL);
                createUserStatement.setString(6, ClientStatus.INACTIVE.name());
                assignToTariffPlanTableStatement.setLong(1, ParameterConstant.FAKE_TARIFF_ID);
                createUserStatement.execute();
                assignToTariffPlanTableStatement.execute();
            }
            connection.commit();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
                if (assignToTariffPlanTableStatement != null) {
                    assignToTariffPlanTableStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            DBUtils.getInstance().releaseConnection(connection);
        }
//        Connection connection = DBUtils.getInstance().getConnection();
//        try (
//                PreparedStatement createUserStatement = connection.prepareStatement(CREATE_CLIENT_SQL);
//                PreparedStatement assignToTariffPlanTableStatement = connection.prepareStatement("INSERT INTO" +
//                        " user_tariffplan(user_id, tariff_id) VALUES (LAST_INSERT_ID(), ?)")) {
//            connection.setAutoCommit(false);
//            createUserStatement.setString(1, login);
//            createUserStatement.setString(2, PasswordHash.hashPassword(password));
//            createUserStatement.setString(3, name);
//            createUserStatement.setString(4, surname);
//            createUserStatement.setString(5, role.name());
//            if (role == Role.CLIENT) {
//                createUserStatement.setString(6, ClientStatus.INACTIVE.name());
//                assignToTariffPlanTableStatement.setLong(1, 0);
//            }
//            createUserStatement.execute();
//            assignToTariffPlanTableStatement.execute();
//            connection.commit();
//        } catch (
//                SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                connection.setAutoCommit(true);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//            DBUtils.getInstance().releaseConnection(connection);
//        }
    }

    public void deleteUserById(long userId) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_SQL)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
    }

    public void deleteTariffPlanById(int tariffPlanId) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TARIFF_BY_ID_SQL)) {
            preparedStatement.setLong(1, tariffPlanId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
    }

    public void editClientByAdmin(Client client) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement updateClient = connection.prepareStatement(UPDATE_USER_SQL)
        ) {
            updateClient.setString(1, client.getName());
            updateClient.setString(2, client.getSurname());
            updateClient.setString(3, client.getStatus().name());
            updateClient.setLong(4, client.getId());
            updateClient.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
    }

    public void makeInactiveClient(long clientId) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SET_FAKE_TARIFF_TO_USER_SQL);
             PreparedStatement changeClientStatusStatement = connection.prepareStatement(MAKE_INACTIVE_CLIENT_SQL)) {
            connection.setAutoCommit(false);
            preparedStatement.setLong(1, clientId);
            changeClientStatusStatement.setLong(1, clientId);
            preparedStatement.execute();
            changeClientStatusStatement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                DBUtils.getInstance().releaseConnection(connection);
            }
        }
    }

    public void makePaymentAndChangeTariffPlan(long clientId, int tariffPlanId, Payment payment) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement paymentStatement = connection.prepareStatement(MAKE_PAYMENT_SQL);
             PreparedStatement changeTariffStatement = connection.prepareStatement(UPDATE_USER_TARIFF);
             PreparedStatement activeClientStatement = connection.prepareStatement(MAKE_ACTIVE_CLIENT)) {
            connection.setAutoCommit(false);

            paymentStatement.setLong(1, clientId);
            paymentStatement.setBigDecimal(2, payment.getAmount());
            paymentStatement.setString(3, payment.getPaymentType().name());
            paymentStatement.setTimestamp(4, Timestamp.valueOf(payment.getDate()));

            changeTariffStatement.setInt(1, tariffPlanId);
            changeTariffStatement.setLong(2, clientId);

            activeClientStatement.setLong(1, clientId);

            paymentStatement.executeUpdate();
            changeTariffStatement.executeUpdate();
            activeClientStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            DBUtils.getInstance().releaseConnection(connection);
        }
    }


    public void createTariffPlan(String title, int speed, BigDecimal price) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TARIFF_PLAN_SQL)) {
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, speed);
            preparedStatement.setBigDecimal(3, price);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.getInstance().releaseConnection(connection);
            }
        }
    }

    public List<TariffPlan> retrieveTariffPlans() {
        List<TariffPlan> tariffPlans = new ArrayList<>();
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TARIFFS_SQL)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(DbConstants.ID);
                String title = resultSet.getString(DbConstants.TITLE);
                int speed = resultSet.getInt(DbConstants.SPEED);
                BigDecimal price = resultSet.getBigDecimal(DbConstants.PRICE);
                if (id != ParameterConstant.FAKE_TARIFF_ID) {
                    TariffPlan tariffPlan = new TariffPlan(id, title, speed, price);
                    tariffPlans.add(tariffPlan);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.getInstance().releaseConnection(connection);
            }
        }
        return tariffPlans;
    }

    public void editTariffPlan(TariffPlan tariffPlan) {
        Connection connection = DBUtils.getInstance().getConnection();
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
                DBUtils.getInstance().releaseConnection(connection);
            }
        }
    }
}
