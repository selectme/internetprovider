package by.epam.learn.mudrahelau.command;

import by.epam.learn.mudrahelau.constant.PagesConstant;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Implementation of {@link ServletCommand}.
 * LogoutServletCommand is intended that {@link by.epam.learn.mudrahelau.model.User} can log out from his account.
 *
 * @see ServletCommand
 * @see CommandStorage
 */
public class LogoutServletCommand implements ServletCommand {
    /**
     * Constant name of the command.
     */
    private static final String COMMAND_NAME = "do_logout";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(PagesConstant.MAIN_PAGE);
        requestDispatcher.forward(request,response);
    }

    @Override
    public String getName() {
        return COMMAND_NAME;
    }
}
