package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import by.epam.learn.mudrahelau.model.TariffPlan;
import by.epam.learn.mudrahelau.util.DBUtils;
import com.google.common.annotations.VisibleForTesting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.learn.mudrahelau.constant.ParameterConstant.EMPTY_STRING;

public class TariffValidator {
    private static final String GET_TITLE_SQL = "SELECT title FROM tariff_plan WHERE title=?";
    private static final Logger logger = LogManager.getLogger(TariffValidator.class);
    private static final int TARIFF_SPEED_TO_COMPARE = 0;
    private static final BigDecimal TARIFF_PRICE_TO_COMPARE = new BigDecimal(0);
    private static final int COMPARATOR_VALUE = 0;

    public static boolean validateCreatingTariff(TariffPlan tariffPlan) {
        return validateTitle(tariffPlan) && validateTariffTitleForDuplicating(tariffPlan)
                && validateSpeed(tariffPlan) && validatePrice(tariffPlan);
    }

    public static boolean validateEditingTariff(TariffPlan tariffPlan) {
        return validateTitle(tariffPlan) && validateSpeed(tariffPlan) && validatePrice(tariffPlan);
    }


    private static boolean validateTariffTitleForDuplicating(TariffPlan tariffPlan) {
        boolean result = true;
        Connection connection = DBUtils.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_TITLE_SQL)) {
            preparedStatement.setString(1, tariffPlan.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result = false;
            }
        } catch (SQLException e) {
            logger.error(LoggerConstants.SQL_EXCEPTION, e);
        } finally {
            DBUtils.getInstance().releaseConnection(connection);
        }
        return result;
    }

    @VisibleForTesting
    static boolean validateTitle(TariffPlan tariffPlan) {

        boolean result = false;
        if (tariffPlan.getTitle() != null) {
            if (!tariffPlan.getTitle().trim().equals(EMPTY_STRING)) {
                String regx = "^[A-Za-zА-Яа-я0-9\\_\\!\\.\\-\\s]*$";
                Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE);
                Matcher matcher = pattern.matcher(tariffPlan.getTitle());
                result = matcher.find();
            }
        }
        return result;
    }


    private static boolean validateSpeed(TariffPlan tariffPlan) {
        return tariffPlan.getSpeed() > TARIFF_SPEED_TO_COMPARE;
    }

    private static boolean validatePrice(TariffPlan tariffPlan) {
        return tariffPlan.getPrice().compareTo(TARIFF_PRICE_TO_COMPARE) >= COMPARATOR_VALUE;
    }

}
