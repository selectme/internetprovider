package by.epam.learn.mudrahelau.sheduler;

import java.util.Timer;


public class ScheduleExecutor {

    private final static long CHECK_PERIOD = 60_000*5;

    public void execute() {
        Timer timer = new Timer();

        timer.schedule(new PaymentChecker(), 1000, CHECK_PERIOD);
    }
}
