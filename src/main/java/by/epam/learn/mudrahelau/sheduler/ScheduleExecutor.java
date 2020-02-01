package by.epam.learn.mudrahelau.sheduler;

import java.util.Timer;

/**
 * @author Viktar on 29.01.2020
 */
public class ScheduleExecutor {

    private PaymentChecker paymentChecker;

    public void execute() {
        Timer timer = new Timer();

        timer.schedule(new PaymentChecker(), 1000, 30000);
    }
}
