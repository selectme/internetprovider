package by.epam.learn.mudrahelau.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class DBUtils {

    private static DBUtils dbUtils;
    private static BlockingQueue<Connection> connectionPool = new ArrayBlockingQueue<>(10);
    private final static String URL = "jdbc:mysql://localhost:3306/providerdb?serverTimezone=Europe/Minsk&allowPublicKeyRetrieval=true&useSSL=false";
    private final static String LOGIN = "root";
    private final static String PASSWORD = "root";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static int NUMBER_OF_CONNECTIONS = 10;

    private DBUtils() {
    }

    public static DBUtils getInstance() {
        if (dbUtils == null) {
            dbUtils = new DBUtils();
        }
        return dbUtils;
    }

    {
        try {
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        createConnectionPool();
    }

    private void createConnectionPool() {
        for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {
            try {
                connectionPool.add(DriverManager.getConnection(URL, LOGIN, PASSWORD));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionPool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connectionPool.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
