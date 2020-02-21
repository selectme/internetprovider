package by.epam.learn.mudrahelau.validator;

import by.epam.learn.mudrahelau.model.TariffPlan;

import java.math.BigDecimal;

/**
 * @author Viktar on 19.02.2020
 */
public class TariffValidator {

    public static boolean validateTariff(TariffPlan tariffPlan) {
        return validateTitle(tariffPlan) && validateSpeed(tariffPlan) && validatePrice(tariffPlan);
    }

    private static boolean validateTitle(TariffPlan tariffPlan) {
        return tariffPlan.getTitle() != null && !tariffPlan.getTitle().trim().equals("");
    }

    private static boolean validateSpeed(TariffPlan tariffPlan) {
        return tariffPlan.getSpeed() > 0;
    }

    private static boolean validatePrice(TariffPlan tariffPlan) {
        return tariffPlan.getPrice().compareTo(new BigDecimal(0)) >= 0;
    }

}
