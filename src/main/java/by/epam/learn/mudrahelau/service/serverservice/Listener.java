package by.epam.learn.mudrahelau.service.serverservice;

import by.epam.learn.mudrahelau.constant.LoggerConstants;
import by.epam.learn.mudrahelau.sheduler.ScheduleExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Viktar on 29.01.2020
 */
//todo название класса?
public class Listener implements ServletContextListener {
    private static final Logger logger = LogManager.getLogger(Listener.class);
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
