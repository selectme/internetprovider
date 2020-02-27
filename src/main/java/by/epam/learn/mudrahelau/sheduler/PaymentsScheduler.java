package by.epam.learn.mudrahelau.sheduler;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class PaymentsScheduler implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(PaymentsScheduler.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info(LoggerConstants.SERVER_STARTED);
        ScheduleExecutor scheduleExecutor = new ScheduleExecutor();
        scheduleExecutor.execute();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
