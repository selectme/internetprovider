package by.epam.learn.mudrahelau.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class DBUtils {
    public static Connection getDbConnection() {
        Connection connection = null;
        try {
            String url = "jdbc:mysql://localhost:3306/providerdb?serverTimezone=Europe/Minsk&allowPublicKeyRetrieval=true&useSSL=false";
            String login = "root";
            String password = "root";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, login, password);

        } catch (Exception e) {
            System.out.println("connection failed");
            e.printStackTrace();
        }
        return connection;
    }

    public static Statement getStatement() {
        Statement statement = null;
        try {
            statement = DBUtils.getDbConnection().createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statement;
    }
}
