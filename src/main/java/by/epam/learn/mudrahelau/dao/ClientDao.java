package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
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

    public void editClient(Client client) {
        Connection connection = DBUtils.getConnection();
        try (
                PreparedStatement updatePersonalData = connection.prepareStatement("UPDATE user SET  name=?, surname=? where id=?");
        ) {
            updatePersonalData.setString(1, client.getName());
            updatePersonalData.setString(2, client.getSurname());
            updatePersonalData.setLong(3, client.getId());
            updatePersonalData.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
                DBUtils.releaseConnection(connection);
            }
    }


//    public void editClient(Client client) {
//        Connection connection = DBUtils.getDbConnection();
//        try (
//                PreparedStatement updatePersonalData = connection.prepareStatement(UPDATE_CLIENT_DATA_SQL);
//                PreparedStatement updateTariff = connection.prepareStatement(UPDATE_CLIENT_TARIFF_PLAN_SQL);
//        ) {
//            connection.setAutoCommit(false);
//
//            updatePersonalData.setString(1, client.getLogin());
//            updatePersonalData.setString(2, client.getPassword());
//            updatePersonalData.setString(3, client.getName());
//            updatePersonalData.setString(4, client.getSurname());
//            updatePersonalData.setLong(5, client.getId());
//            updatePersonalData.executeUpdate();
//
//            updateTariff.setInt(1, client.getTariffPlan().getId());
//            updateTariff.setLong(2, client.getId());
//            updateTariff.executeUpdate();
//
//            connection.commit();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.setAutoCommit(true);
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//                DBUtils.releaseConnection(connection);
//            }
//        }
//    }

    public void makePayment(Payment payment) {
        Connection connection = DBUtils.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT into payments(client_id, amount, date) " +
                "values (?,?,?)")) {
            preparedStatement.setLong(1, payment.getClient().getId());
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
}
