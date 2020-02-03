package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.payment.PaymentType;
import by.epam.learn.mudrahelau.status.ClientStatus;
import by.epam.learn.mudrahelau.util.DBUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientDao {

    private static final String UPDATE_CLIENT_DATA_SQL = "UPDATE user SET login=?, password=?, name=?, surname=? where id=?";
    private static final String UPDATE_CLIENT_TARIFF_PLAN_SQL = "UPDATE user_tariffplan SET tariff_id=? where user_id=?";


    public void editClientByClient(Client client) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement updateClient = connection.prepareStatement("UPDATE user SET name=?, surname=? WHERE id=?")
        ) {
            updateClient.setString(1, client.getName());
            updateClient.setString(2, client.getSurname());
            updateClient.setLong(3, client.getId());
            updateClient.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
    }


    public void makePayment(Payment payment) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into payments(client_id, amount, type, date) " +
                "values (?,?,?,?)")) {
            preparedStatement.setLong(1, payment.getClientId());
            preparedStatement.setBigDecimal(2, payment.getAmount());
            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Minsk"));
            preparedStatement.setString(3, payment.getPaymentType().name());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(localDateTime));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.getInstance().releaseConnection(connection);
            }
        }
    }


    public List<Payment> retrievePayments(Long clientId) {
        List<Payment> payments = new ArrayList<>();
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM payments" +
                " WHERE client_id = ?")
        ) {
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BigDecimal amount = resultSet.getBigDecimal(3);
                PaymentType type = PaymentType.valueOf(resultSet.getString(4).toUpperCase());
                Timestamp timestamp = resultSet.getTimestamp(5);
                LocalDateTime date = timestamp.toLocalDateTime();
                Payment payment = new Payment(amount,type, date);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.getInstance().releaseConnection(connection);
            }
        }
        return payments;
    }

    public BigDecimal retrieveClientMoneyAmountByClientId(long clientId) {
        BigDecimal amount = new BigDecimal(0);
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT SUM(amount) FROM payments " +
                "WHERE client_id = ?")) {
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                amount = resultSet.getBigDecimal(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
        return amount;
    }

    public void changeClientStatus(long clientId, ClientStatus status) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET status=? " +
                "WHERE id=?")) {
            preparedStatement.setString(1, status.name());
            preparedStatement.setLong(2, clientId);
            preparedStatement.executeUpdate();
            if (status == ClientStatus.INACTIVE) {
                removeTariffPlanFromClient(clientId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
    }

    public void removeTariffPlanFromClient(long clientId) {
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user_tariffplan SET tariff_id=0 " +
                "WHERE user_id=?")) {
            preparedStatement.setLong(1, clientId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
    }

    public LocalDateTime retrieveLastDebitDate(long clientId) {
        LocalDateTime localDateTime = null;
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT date FROM payments " +
                "WHERE date=(SELECT MAX(date) FROM payments WHERE client_id=? AND type='DEBIT')")) {
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                try {
                    localDateTime = resultSet.getTimestamp(1).toLocalDateTime();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
        return localDateTime;
    }

    public Map<Long, Integer> retrieveActiveClientsId() {
        Map<Long, Integer> clientIdAndTariffId = new HashMap<>();
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_tariffplan " +
                "WHERE tariff_id > 0")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long clientId = resultSet.getLong(1);
                Integer tariffId = resultSet.getInt(2);
                clientIdAndTariffId.put(clientId, tariffId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
        return clientIdAndTariffId;
    }


}
