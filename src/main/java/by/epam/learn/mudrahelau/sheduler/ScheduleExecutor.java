package by.epam.learn.mudrahelau.sheduler;

import java.util.Timer;


public class ScheduleExecutor {

    private final static long CHECK_PERIOD = 60_000 * 5;
    private final static long DELAY_IN_MILLIS = 1000;

    public void execute() {

        Timer timer = new Timer();

        timer.schedule(new PaymentChecker(), DELAY_IN_MILLIS,CHECK_PERIOD);
    }
}
