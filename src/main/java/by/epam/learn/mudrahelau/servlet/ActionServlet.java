package by.epam.learn.mudrahelau.servlet;

import by.epam.learn.mudrahelau.command.CommandStorage;
import by.epam.learn.mudrahelau.command.ServletCommand;
import by.epam.learn.mudrahelau.constant.LoggerConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ActionServlet interacts with clients through a request-response principle.
 * Depending on the type of request, the method that processes the request will be determined.
 */
public class ActionServlet extends HttpServlet {
    /**
     * Parameter based on which it will be determined which command to execute .
     */
    private static final String ACTION = "action";

    /**
     * {@link Logger}
     */
    private static final Logger logger = LogManager.getLogger(ActionServlet.class);

    @Override
    public void init() {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = req.getParameter(ACTION);
            ServletCommand command = CommandStorage.getInstance().getCommandByName(action);
            command.execute(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(LoggerConstants.ACTION_SERVLET_EXCEPTION, e);
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String action = req.getParameter(ACTION);
            ServletCommand command = CommandStorage.getInstance().getCommandByName(action);
            command.execute(req, resp);
        } catch (ServletException | IOException e) {
            logger.error(LoggerConstants.ACTION_SERVLET_EXCEPTION, e);
        }
    }
}
