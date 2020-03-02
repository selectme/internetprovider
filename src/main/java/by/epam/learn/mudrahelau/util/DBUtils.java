package by.epam.learn.mudrahelau.util;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * DBUtils is used for creating a connection pool, getting and releasing connections from the connection pool.
 */
public class DBUtils {

    private static DBUtils dbUtils;
    /**
     * A Queue that additionally supports operations that wait for the queue to become non-empty when retrieving an element,
     * and wait for space to become available in the queue when storing an element.
     */
    private static BlockingQueue<Connection> connectionPool = new ArrayBlockingQueue<>(10);
    /**
     * Database URL.
     */
    private final static String URL = "jdbc:mysql://localhost:3306/providerdb?serverTimezone=Europe/Minsk&allowPublicKeyRetrieval=true&useSSL=false";
    /**
     * Login for connection to database.
     */
    private final static String LOGIN = "root";
    /**
     * Password for connection to database.
     */
    private final static String PASSWORD = "root";
    /**
     * Database driver.
     */
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    /**
     * number of {@link Connection} which will be created in the connection pool
     */
    private final static int NUMBER_OF_CONNECTIONS = 10;
    /**
     * {@link Logger}
     */
    private static final Logger logger = LogManager.getLogger(DBUtils.class);

    private DBUtils() {
    }

    /**
     * @return static instance.
     */
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

    /**
     * Creates {@link Connection} pool.
     */
    private void createConnectionPool() {
        for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {
            try {
                connectionPool.add(DriverManager.getConnection(URL, LOGIN, PASSWORD));
            } catch (SQLException e) {
                logger.error(LoggerConstants.SQL_EXCEPTION, e);
            }
        }
    }

    /**
     * Allow to get {@link Connection} from the connection pool.
     *
     * @return {@link Connection}
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionPool.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Releases {@link Connection} into the connection pool
     *
     * @param connection {@link Connection}
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connectionPool.put(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Allows to get the connection pool size
     *
     * @return the connection pool size
     */
    public int getConnectionPoolSize() {
        return connectionPool.size();
    }
}
