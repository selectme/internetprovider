package by.epam.learn.mudrahelau.dao.impl;

import by.epam.learn.mudrahelau.constant.DbConstants;
import by.epam.learn.mudrahelau.constant.LoggerConstants;
import by.epam.learn.mudrahelau.dao.ClientDao;
import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.status.ClientStatus;
import by.epam.learn.mudrahelau.util.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JDBC implementation of the {@link ClientDao}
 *
 * @see ClientDao
 */
public class ClientDaoDbImpl implements ClientDao {

    private static final String UPDATE_CLIENT_SQL = "UPDATE user SET name=?, surname=? WHERE id=?";
    private static final String MAKE_PAYMENT_SQL = "INSERT into payments(client_id, amount, type, date) values (?,?,?,?)";
    private static final String GET_PAYMENTS_BY_CLIENT_ID_SQL = "SELECT * FROM payments WHERE client_id = ?";
    private static final String GET_CLIENT_CURRENT_MONEY_BALANCE_SQL = "SELECT SUM(amount) as amount FROM payments WHERE client_id = ?";
    private static final String CHANGE_CLIENT_STATUS_SQL = "UPDATE user SET status=? WHERE id=?";
    private static final String SET_FAKE_TARIFF_TO_CLIENT_SQL = "UPDATE user_tariffplan SET tariff_id=0 WHERE user_id=?";
    private static final String GET_LAST_DEBIT_DATE_SQL = "SELECT date FROM payments WHERE date=(SELECT MAX(date) " +
            "FROM payments WHERE client_id=? AND type='DEBIT')";
    private static final String GET_ACTIVE_CLIENTS_SQL = "SELECT * FROM user_tariffplan WHERE tariff_id > 0";

    private static final Logger logger = LogManager.getLogger(ClientDaoDbImpl.class);



    @Override
    public void editClientByClient(Client client) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement updateClient = connection.prepareStatement(UPDATE_CLIENT_SQL)
        ) {
            updateClient.setString(1, client.getName());
            updateClient.setString(2, client.getSurname());
            updateClient.setLong(3, client.getId());
            updateClient.executeUpdate();
        } catch (SQLException e) {
            logger.error(LoggerConstants.SQL_EXCEPTION, e);
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public void makePayment(Payment payment) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(MAKE_PAYMENT_SQL)) {
            preparedStatement.setLong(1, payment.getClientId());
            preparedStatement.setBigDecimal(2, payment.getAmount());
            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Minsk"));
            preparedStatement.setString(3, payment.getPaymentType().name());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(localDateTime));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LoggerConstants.SQL_EXCEPTION, e);
        } finally {
            if (connection != null) {
                DBUtils.getInstance().releaseConnection(connection);
            }
        }
    }

    @Override
    public List<Payment> retrievePayments(Long clientId) {
        List<Payment> payments = new ArrayList<>();
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_PAYMENTS_BY_CLIENT_ID_SQL)
        ) {
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BigDecimal amount = resultSet.getBigDecimal(DbConstants.AMOUNT);
                PaymentType type = PaymentType.valueOf(resultSet.getString(DbConstants.TYPE).toUpperCase());
                Timestamp timestamp = resultSet.getTimestamp(DbConstants.DATE);
                LocalDateTime date = timestamp.toLocalDateTime();
                Payment payment = new Payment();
                payment.setAmount(amount);
                payment.setPaymentType(type);
                payment.setDate(date);
                payments.add(payment);
            }
        } catch (SQLException e) {
            logger.error(LoggerConstants.SQL_EXCEPTION, e);
        } finally {
            if (connection != null) {
                DBUtils.getInstance().releaseConnection(connection);
            }
        }
        return payments;
    }

    @Override
    public BigDecimal retrieveClientMoneyAmountByClientId(long clientId) {
        BigDecimal amount = new BigDecimal(0);
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_CLIENT_CURRENT_MONEY_BALANCE_SQL)) {
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getBigDecimal(DbConstants.AMOUNT);
            }
        } catch (SQLException e) {
            logger.error(LoggerConstants.SQL_EXCEPTION, e);
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
        return amount;
    }

    @Override
    public void changeClientStatus(long clientId, ClientStatus status) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_CLIENT_STATUS_SQL)) {
            preparedStatement.setString(1, status.name());
            preparedStatement.setLong(2, clientId);
            preparedStatement.executeUpdate();
            if (status == ClientStatus.INACTIVE) {
                removeTariffPlanFromClient(clientId);
            }
        } catch (SQLException e) {
            logger.error(LoggerConstants.SQL_EXCEPTION, e);
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
    }

    private void removeTariffPlanFromClient(long clientId) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SET_FAKE_TARIFF_TO_CLIENT_SQL)) {
            preparedStatement.setLong(1, clientId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(LoggerConstants.SQL_EXCEPTION, e);
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
    }

    @Override
    public LocalDateTime retrieveLastDebitDate(long clientId) {
        LocalDateTime localDateTime = null;
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_LAST_DEBIT_DATE_SQL)) {
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                try {
                    localDateTime = resultSet.getTimestamp(DbConstants.DATE).toLocalDateTime();
                } catch (NullPointerException e) {
                    logger.error(LoggerConstants.NULL_POINTER_EXCEPTION, e);
                }
            }
        } catch (SQLException e) {
            logger.error(LoggerConstants.SQL_EXCEPTION, e);
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
        return localDateTime;
    }

    @Override
    public Map<Long, Integer> retrieveActiveClientsId() {
        Map<Long, Integer> clientIdAndTariffId = new HashMap<>();
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ACTIVE_CLIENTS_SQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long clientId = resultSet.getLong(DbConstants.USER_ID);
                Integer tariffId = resultSet.getInt(DbConstants.TARIFF_ID);
                clientIdAndTariffId.put(clientId, tariffId);
            }
        } catch (SQLException e) {
            logger.error(LoggerConstants.SQL_EXCEPTION, e);
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
        return clientIdAndTariffId;
    }


}
