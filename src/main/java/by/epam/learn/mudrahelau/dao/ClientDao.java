package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.status.ClientStatus;
import by.epam.learn.mudrahelau.util.DBUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {

    private static final String UPDATE_CLIENT_DATA_SQL = "UPDATE user SET login=?, password=?, name=?, surname=? where id=?";
    private static final String UPDATE_CLIENT_TARIFF_PLAN_SQL = "UPDATE user_tariffplan SET tariff_id=? where user_id=?";


    public void editClientByClient(Client client) {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement updateClient = connection.prepareStatement("UPDATE user SET name=?, surname=? WHERE id=?")
        ) {
            updateClient.setString(1, client.getName());
            updateClient.setString(2, client.getSurname());
            updateClient.setLong(3, client.getId());
            updateClient.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.releaseConnection(connection);
        }
    }


    public void makePayment(Payment payment) {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into payments(client_id, amount, date) " +
                "values (?,?,?)")) {
            preparedStatement.setLong(1, payment.getClientId());
            preparedStatement.setBigDecimal(2, payment.getAmount());
            LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Europe/Minsk"));
            preparedStatement.setTimestamp(3, Timestamp.valueOf(localDateTime));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.releaseConnection(connection);
            }
        }
    }

    public List<Payment> retrievePayments(Long clientId) {
        List<Payment> payments = new ArrayList<>();
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM payments" +
                " WHERE client_id = ?");
        ) {
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BigDecimal amount = resultSet.getBigDecimal(3);
                Timestamp timestamp = resultSet.getTimestamp(4);
                LocalDateTime date = timestamp.toLocalDateTime();
                Payment payment = new Payment(amount, date);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                DBUtils.releaseConnection(connection);
            }
        }
        return payments;
    }

    public BigDecimal retrieveClientMoneyAmountByClientId(long clientId) {
        BigDecimal amount = new BigDecimal(0);
        Connection connection = DBUtils.getConnection();
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
            DBUtils.releaseConnection(connection);
        }
        return amount;
    }

    public void changeClientStatus(long clientId, ClientStatus status) {
        Connection connection = DBUtils.getConnection();
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
            DBUtils.releaseConnection(connection);
        }
    }

    public void removeTariffPlanFromClient(long clientId) {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user_tariffplan SET tariff_id=0 " +
                "WHERE user_id=?")) {
            preparedStatement.setLong(1, clientId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.releaseConnection(connection);
        }
    }
}
