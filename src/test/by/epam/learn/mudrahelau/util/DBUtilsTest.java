package by.epam.learn.mudrahelau.util;

import org.testng.annotations.Test;

import java.sql.Connection;

import static org.testng.Assert.assertEquals;

/**
 * @author Viktar on 24.02.2020
 */
public class DBUtilsTest {

    @Test
    public void testGetConnection()   {

        Connection connectionOneRetrievedFromPool = DBUtils.getInstance().getConnection();
        Connection connectionTwoRetrievedFromPool = DBUtils.getInstance().getConnection();

        int expectedConnectionPoolSizeAfterRetrieving = 8;
        int actualConnectionPoolSizeAfterRetrieving = DBUtils.getInstance().getConnectionPoolSize();

        assertEquals(actualConnectionPoolSizeAfterRetrieving, expectedConnectionPoolSizeAfterRetrieving);
        DBUtils.getInstance().releaseConnection(connectionOneRetrievedFromPool);
        DBUtils.getInstance().releaseConnection(connectionTwoRetrievedFromPool);
    }

    @Test
    public void testReleaseConnection()   {
        Connection connectionOneRetrievedFromPool = DBUtils.getInstance().getConnection();
        Connection connectionTwoRetrievedFromPool = DBUtils.getInstance().getConnection();

        DBUtils.getInstance().releaseConnection(connectionOneRetrievedFromPool);
        DBUtils.getInstance().releaseConnection(connectionTwoRetrievedFromPool);

        int expectedConnectionPoolSizeAfterReleasing = 10;
        int actualConnectionPoolSizeAfterReleasing = DBUtils.getInstance().getConnectionPoolSize();

        assertEquals(actualConnectionPoolSizeAfterReleasing, expectedConnectionPoolSizeAfterReleasing);
    }


}