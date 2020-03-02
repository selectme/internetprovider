package by.epam.learn.mudrahelau.scheduler;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * PaymentsSchedulerExecutor executes the tasks described int {@link PaymentsScheduler}.
 * The implementation of {@link ServletContextListener} allows to run {@link PaymentsScheduler} for repeated execution
 * at the application startup.
 */
public class PaymentsSchedulerExecutor implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(PaymentsSchedulerExecutor.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info(LoggerConstants.SERVER_STARTED);
        PaymentsScheduler paymentsScheduler = new PaymentsScheduler();
        paymentsScheduler.execute();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
