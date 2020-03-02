package by.epam.learn.mudrahelau.scheduler;

import java.util.Timer;

/**
 * PaymentsScheduler is used for setting the task and the period of executing.
 */
public class PaymentsScheduler {
    /**
     * The period in milliseconds after which the repeating task will be executed.
     */
    private final static long CHECK_PERIOD = 60_000 * 5;
    /**
     * Delay in milliseconds before task is to be executed.
     */
    private final static long DELAY_IN_MILLIS = 1000;

    public void execute() {

        Timer timer = new Timer();
        timer.schedule(new PaymentChecker(), DELAY_IN_MILLIS, CHECK_PERIOD);
    }
}
