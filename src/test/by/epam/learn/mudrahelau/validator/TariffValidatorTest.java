package by.epam.learn.mudrahelau.validator;


import by.epam.learn.mudrahelau.model.TariffPlan;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;


public class TariffValidatorTest {



    @BeforeMethod
    public void setUp() {


    }

    @Test
    public void testValidateTitle() {

        TariffPlan testTariff = new TariffPlan();
        testTariff.setTitle("TestTariff");
        boolean isValidTitle = TariffValidator.validateTitle(testTariff);

        assertTrue(isValidTitle);
    }

    @Test
    public void testValidateTitleFalse() {

        TariffPlan testTariff = new TariffPlan();
        testTariff.setTitle("<tariff>");
        boolean isValidTitle = TariffValidator.validateTitle(testTariff);

        assertFalse(isValidTitle);
    }
}
