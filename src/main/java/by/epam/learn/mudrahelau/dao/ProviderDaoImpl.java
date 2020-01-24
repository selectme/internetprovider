package by.epam.learn.mudrahelau.dao;

import by.epam.learn.mudrahelau.model.Client;
import by.epam.learn.mudrahelau.model.Payment;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.model.User;
import by.epam.learn.mudrahelau.role.Role;
import by.epam.learn.mudrahelau.util.DBUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Viktar on 27.12.2019
 */
public class ProviderDaoImpl {
    private static final String GET_ALL_TARIFFS = "SELECT * from tariff_plan";
    private static final String GET_ALL_CLIENTS = "SELECT * from user";
    private static final int TARIFF_TITLE_COLUMN = 2;
    private static final int TARIFF_SPEED_COLUMN = 3;
    private static final int TARIFF_PRICE_COLUMN = 4;




}
