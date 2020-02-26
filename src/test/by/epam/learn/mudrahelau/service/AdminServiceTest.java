package by.epam.learn.mudrahelau.service;

import by.epam.learn.mudrahelau.dao.impl.AdminDaoDbImpl;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.service.impl.AdminServiceDbImpl;
import by.epam.learn.mudrahelau.util.DBUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.mockito.Mockito.*;

/**
 * @author Viktar on 26.02.2020
 */
public class AdminServiceTest {
    @Mock
    private AdminService adminService;
    @Mock
    private User user;


    @Before
    public void setUp() throws Exception {
        adminService = new AdminServiceDbImpl(new AdminDaoDbImpl());
        user = new User();
        user.setName("test");
        user.setSurname("user");

    }

    @Test
    public void createUser() throws SQLException {
        DBUtils dbUtils = mock(DBUtils.class);
        Connection connection = mock(Connection.class);
        PreparedStatement preparedStatement = mock(PreparedStatement.class);
        when(dbUtils.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(preparedStatement);
        when(preparedStatement.execute()).thenReturn(Boolean.TRUE);


    }
}