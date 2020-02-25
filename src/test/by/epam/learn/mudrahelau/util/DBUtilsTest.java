package by.epam.learn.mudrahelau.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.testng.Assert.assertEquals;

/**
 * @author Viktar on 24.02.2020
 */
public class DBUtilsTest {

    private static BlockingQueue<Connection> connectionPool = new ArrayBlockingQueue<>(2);
    private final static String URL = "jdbc:mysql://localhost:3306/providerdb?serverTimezone=Europe/Minsk&allowPublicKeyRetrieval=true&useSSL=false";
    private final static String LOGIN = "root";
    private final static String PASSWORD = "root";
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final Logger logger = LogManager.getLogger(DBUtilsTest.class);



    @Test
    public void testGetConnection() throws InterruptedException {

        Connection connectionOneRetrievedFromPool = DBUtils.getInstance().getConnection();
        Connection connectionTwoRetrievedFromPool = DBUtils.getInstance().getConnection();

        int expectedConnectionPoolSizeAfterRetrieving = 8;
        int actualConnectionPoolSizeAfterRetrieving = DBUtils.getInstance().getConnectionPoolSize();

        assertEquals(actualConnectionPoolSizeAfterRetrieving, expectedConnectionPoolSizeAfterRetrieving);
        DBUtils.getInstance().releaseConnection(connectionOneRetrievedFromPool);
        DBUtils.getInstance().releaseConnection(connectionTwoRetrievedFromPool);
    }

    @Test
    public void testReleaseConnection() throws InterruptedException {
        Connection connectionOneRetrievedFromPool = DBUtils.getInstance().getConnection();
        Connection connectionTwoRetrievedFromPool = DBUtils.getInstance().getConnection();

        DBUtils.getInstance().releaseConnection(connectionOneRetrievedFromPool);
        DBUtils.getInstance().releaseConnection(connectionTwoRetrievedFromPool);

        int expectedConnectionPoolSizeAfterReleasing = 10;
        int actualConnectionPoolSizeAfterReleasing = DBUtils.getInstance().getConnectionPoolSize();

        assertEquals(actualConnectionPoolSizeAfterReleasing, expectedConnectionPoolSizeAfterReleasing);
    }


}