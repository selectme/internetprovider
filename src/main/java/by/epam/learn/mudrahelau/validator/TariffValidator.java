package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.model.TariffPlan;
import com.google.common.annotations.VisibleForTesting;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.epam.learn.mudrahelau.constant.ParameterConstant.EMPTY_STRING;

public class TariffValidator {

    public static boolean validateTariff(TariffPlan tariffPlan) {
        return validateTitle(tariffPlan) && validateSpeed(tariffPlan) && validatePrice(tariffPlan);
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
        return tariffPlan.getSpeed() > 0;
    }

    private static boolean validatePrice(TariffPlan tariffPlan) {
        return tariffPlan.getPrice().compareTo(new BigDecimal(0)) >= 0;
    }

}
