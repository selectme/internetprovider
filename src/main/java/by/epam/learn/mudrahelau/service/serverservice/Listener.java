package by.epam.learn.mudrahelau.service.serverservice;

import by.epam.learn.mudrahelau.sheduler.ScheduleExecutor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Viktar on 29.01.2020
 */
//todo название класса?
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("server is working");
        ScheduleExecutor scheduleExecutor = new ScheduleExecutor();
        scheduleExecutor.execute();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
