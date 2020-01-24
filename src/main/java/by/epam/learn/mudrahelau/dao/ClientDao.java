package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.util.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ClientDao {

    private static final String UPDATE_CLIENT_DATA_SQL = "UPDATE user SET login=?, password=?, name=?, surname=? where id=?";
    private static final String UPDATE_CLIENT_TARIFF_PLAN_SQL = "UPDATE user_tariffplan SET tariff_id=? where user_id=?";

    public void editClient(Client client) {
        try (Connection connection = DBUtils.getDbConnection();
             PreparedStatement updatePersonalData = connection.prepareStatement(UPDATE_CLIENT_DATA_SQL);
             PreparedStatement updateTariff = connection.prepareStatement(UPDATE_CLIENT_TARIFF_PLAN_SQL);
        ) {
            connection.setAutoCommit(false);

            updatePersonalData.setString(1, client.getLogin());
            updatePersonalData.setString(2, client.getPassword());
            updatePersonalData.setString(3, client.getName());
            updatePersonalData.setString(4, client.getSurname());
            updatePersonalData.setLong(5, client.getId());
            updatePersonalData.executeUpdate();

            updateTariff.setInt(1, client.getTariffPlan().getId());
            updateTariff.setLong(2, client.getId());
            updateTariff.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void makePayment(Payment payment) {
        try (PreparedStatement preparedStatement = DBUtils.getDbConnection().prepareStatement("INSERT into payments(client_id, amount, date) " +
                "values (?,?,?)")) {
            preparedStatement.setLong(1, payment.getClient().getId());
            preparedStatement.setDouble(2, payment.getAmount());
            preparedStatement.setDate(3, new java.sql.Date(payment.getDate().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Payment> retrievePayments(Client client) {
        List<Payment> payments = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = DBUtils.getDbConnection().prepareStatement("SELECT * FROM payments" +
                " WHERE client_id = ?")) {
            preparedStatement.setLong(1, client.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double amount = resultSet.getDouble(3);
                Date date = resultSet.getDate(4);
//                Calendar cal = new GregorianCalendar();
//                cal.setTime(date);
                Payment payment = new Payment(amount, date);
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }
}
